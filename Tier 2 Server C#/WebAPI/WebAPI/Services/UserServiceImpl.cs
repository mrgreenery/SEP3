
using ApiContracts;
using WebAPI.ApiContracts;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using Grpc.Net.Client;
using WebAPI.Services.Exceptions.User;

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


        var checkEmail =
            await _grpcClient.GetUserByEmailAsync(new EmailRequest
                { Email = email });
        if (checkEmail is not null)
            throw new UserWithThisEmailAlreadyExists();

        try
        {
            var checkEmailResponse =
                await _grpcClient.GetUserByEmailAsync(new EmailRequest
                    { Email = email });

            if (!string.IsNullOrWhiteSpace(checkEmailResponse.Email))
                throw new UserWithThisEmailAlreadyExists();
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            // No user is found, so it is safe to proceed with user creation
        }


        var userEntity = new UserEntity
        {
            Email = email,
            Password = password,
            DisplayName = displayName
        };

        try
        {
            var grpcRequest = new CreateUserRequest
            {
                User = userEntity
            };

            var grpcResponse =
                await _grpcClient.CreateUserAsync(
                    new CreateUserRequest { User = userEntity });

            var userDto = new UserDto()
            {
                Email = grpcResponse.Email,
                DisplayName = grpcResponse.DisplayName,
            };

            return userDto;
        }catch (RpcException ex)
        {
            if (ex.StatusCode == StatusCode.AlreadyExists)
                throw new UserWithThisEmailAlreadyExists();

            throw new Exception($"gRPC CreateUser failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    public async Task<UserDto> GetUserByEmailAsync(string email)
    {
        try
        {
            var grpcResponse = await _grpcClient.GetUserByEmailAsync(new EmailRequest{Email = email});	
				if (string.IsNullOrWhiteSpace(grpcResponse.Email) ||
    			string.IsNullOrWhiteSpace(grpcResponse.DisplayName))
                throw new UserWithThisEmailDoesNotExist();
        
            var userDto = new UserDto
            {
                Email = grpcResponse.Email,
                DisplayName = grpcResponse.DisplayName
            };

            return userDto;
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
                userDtoList.Add(new UserDto
                {
                    Email = user.Email,
                    DisplayName = user.DisplayName
                });
            }

            return userDtoList;
        } catch (RpcException ex) when (ex.StatusCode == StatusCode.Internal)
        {
            throw new Exception("Error fetching users");
        } 
    }

    public async Task<UserDto> CheckUserCredentialsAsync(string email, string password)
    {
        try
        {
            var grpcResponse =
                await _grpcClient.GetUserByEmailAsync(new EmailRequest
                    { Email = email });
           	if (string.IsNullOrWhiteSpace(grpcResponse.Email) ||
   			 string.IsNullOrWhiteSpace(grpcResponse.DisplayName))
                throw new UserWithThisEmailDoesNotExist();

            if (!grpcResponse.Password.Equals(password))
                throw new WrongPasswordException();

            var userDto = new UserDto
            {
                Email = grpcResponse.Email,
                DisplayName = grpcResponse.DisplayName
            };

            return userDto;
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
}
