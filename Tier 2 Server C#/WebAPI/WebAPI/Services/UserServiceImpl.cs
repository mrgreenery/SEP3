
using ApiContracts.User;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using Grpc.Net.Client;
using WebAPI.Services.Exceptions.User;
using WebAPI.Services.Util;
using CreateUserRequest = Data.CreateUserRequest;
using DeleteUserRequest = Data.DeleteUserRequest;

namespace WebAPI.Services;

public class UserServiceImpl : IUserService
{
    private readonly UserService.UserServiceClient _grpcClient;

    public UserServiceImpl(UserService.UserServiceClient grpcClient)
    {
        _grpcClient = grpcClient;
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
                return DtoMapper.UserToDto(grpcResponse);
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
        //sends the request
        var grpcResponse = await _grpcClient.GetAllUsersAsync(new Empty());

        // if response is empty, return an empty list
        if (grpcResponse.Users is null || grpcResponse.Users.Count == 0)
            return new List<UserDto>();
        
        // if good then return the list in Dto
        return grpcResponse.Users
            .Select(DtoMapper.UserToDto)
            .ToList();
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
            
            return grpcResponse is not null ? DtoMapper.UserToDto(grpcResponse) : null;
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

    public async Task<UserDto> UpdateDisplayNameAsync(long id, string displayName)
    {
        try
        {
            var grpcResponse =
                await _grpcClient.UpdateUserNameAsync(new UpdateUserNameRequest 
                {
                    UserId = id,
                    DisplayName = displayName,
                });

            return DtoMapper.UserToDto(grpcResponse);
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisIdDoesNotExist();
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

            return DtoMapper.UserToDto(grpcResponse);
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new UserWithThisIdDoesNotExist();//
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    public async Task UpdateUserPasswordAsync(long id, string password)
    {
        {
            try
            {
                await _grpcClient.UpdateUserPasswordAsync(new UpdateUserPasswordRequest
                    {
                        UserId = id,
                        Password = password
                    });
            }
            catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
            {
                throw new UserWithThisIdDoesNotExist();
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
            throw new UserWithThisIdDoesNotExist();
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC call failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        };
    }
}
