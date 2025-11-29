
using ApiContracts.User;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using Grpc.Net.Client;
using WebAPI.Services.Exceptions.User;
using CreateUserRequest = Data.CreateUserRequest;
using DeleteUserRequest = Data.DeleteUserRequest;

namespace WebAPI.Services;

public class UserServiceImpl : IUserService
{
    private readonly UserService.UserServiceClient _grpcClient;

    public UserServiceImpl()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");

        _grpcClient = new UserService.UserServiceClient(channel);
    }

    public async Task<UserDto> CreateUserAsync(string displayName, string email,
        string password)
    {
        if (string.IsNullOrWhiteSpace(displayName) || string.IsNullOrWhiteSpace(email) ||
            string.IsNullOrWhiteSpace(password))
            throw new ArgumentNullException();
        
        try
        {
            var grpcResponse =
                await _grpcClient.CreateUserAsync(
                    new CreateUserRequest
                    {
                        DisplayName = displayName,
                        Email = email,
                        Password = password
                    });

            if (grpcResponse is not null)
            {
                return ToDto(grpcResponse);
            }
            throw new Exception("Error creating the user");
                
        }catch (RpcException ex)
        {
            if (ex.StatusCode == StatusCode.AlreadyExists)
                throw new UserWithThisEmailAlreadyExists();

            throw new Exception($"gRPC CreateUser failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }
    
    public async Task<List<UserDto>> GetAllUsersAsync()
    {
        try
        {
            var grpcResponse = await _grpcClient.GetAllUsersAsync(new Empty());
            if (grpcResponse is null)
                throw new InvalidDataException("No users found.");
            
            List<UserDto> userDtoList = new();
            foreach (UserEntity user in grpcResponse.Users)
            {
                userDtoList.Add(ToDto(user));
            }
            return userDtoList;
            
        } catch (RpcException ex) when (ex.StatusCode == StatusCode.Internal)
        {
            throw new Exception("Error fetching users");
        } 
    }

    public async Task<UserDto?> LoginUser(string email, string password)
    {
        try
        {
            var grpcResponse =
                await _grpcClient.LogUserAsync(
                    new LoginUserRequest()
                    {
                            Email = email,
                            Password = password
                    });
            
            return grpcResponse is not null ? ToDto(grpcResponse) : null;
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisEmailDoesNotExist();
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    public async Task<UserDto> UpdateUserNameAsync(long id, string displayName)
    {
        try
        {
            var grpcResponse =
                await _grpcClient.UpdateUserNameAsync(new UpdateUserNameRequest 
                {
                    UserId = id,
                    DisplayName = displayName,
                });

            //TODO: notify auth provider about claim change
            return ToDto(grpcResponse);
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisEmailDoesNotExist(); // todo: shouldnt this be id ?
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    public async Task<UserDto> UpdateUserEmailAsync(long id, string email)
    {
        try
        {
            var grpcResponse =
                await _grpcClient.UpdateUserEmailAsync(new UpdateUserEmailRequest 
                {
                    UserId = id,
                    Email = email
                });

            //TODO: notify auth provider about claim change
            return ToDto(grpcResponse);
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisEmailDoesNotExist();// todo same shouldnt this be id ?
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    public async Task<UserDto> UpdateUserPasswordAsync(long id, string password)
    {
        {
            try
            {
                var grpcResponse =
                    await _grpcClient.UpdateUserPasswordAsync(new UpdateUserPasswordRequest
                    {
                        UserId = id,
                        Password = password
                    });

                //TODO: notify auth provider about claim change
                return ToDto(grpcResponse);
            }
            catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
            {
                throw new UserWithThisEmailDoesNotExist(); // todo same shouldnt this be id ?
            }
            catch (RpcException ex)
            {
                throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
            }
        }
    }

    public async Task DeleteUserAsync(long id)
    {
        try
        {
            await _grpcClient.DeleteUserAsync(
                new DeleteUserRequest { UserId = id });
        } catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisEmailDoesNotExist();
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        };
    }

    public static UserDto ToDto(UserEntity user)
    {
        return new UserDto
        {
            Id = user.Id,
            DisplayName = user.DisplayName,
            Email = user.Email,
        };
    } 
}
