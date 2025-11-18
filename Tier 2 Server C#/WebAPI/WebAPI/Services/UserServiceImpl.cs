
using ApiContracts;
using WebAPI.ApiContracts;
using Data;
using Google.Protobuf.WellKnownTypes;
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

    public async Task<UserDto> CreateUserAsync(string displayName, string email, string password)
    {
        if (displayName.IsWhiteSpace() || email.IsWhiteSpace() || password.IsWhiteSpace())
            throw new ArgumentNullException();

        var checkEmail = await _grpcClient.GetUserByEmailAsync(new EmailRequest{Email = email});
        if (checkEmail is not null)
            throw new UserWithThisEmailAlreadyExists();

        var userEntity = new UserEntity
        {
            Email        = email,
            Password     = password,
            DisplayName  = displayName
        };

        var grpcRequest = new CreateUserRequest
        {
            User = userEntity
        };

        var grpcResponse = await _grpcClient.CreateUserAsync(grpcRequest);

        var userDto = new UserDto()
        {
            Email       = grpcResponse.Email,
            DisplayName = grpcResponse.DisplayName,
        };

        return userDto;
    }

    public async Task<UserDto> GetUserByEmailAsync(string email)
    {
        var grpcResponse = await _grpcClient.GetUserByEmailAsync(new EmailRequest{Email = email});
        if (grpcResponse.Email.IsWhiteSpace() ||
            grpcResponse.DisplayName.IsWhiteSpace())
            throw new UserWithThisEmailDoesNotExist();
        
        var userDto = new UserDto
        {
            Email = grpcResponse.Email,
            DisplayName = grpcResponse.DisplayName
        };

        return userDto;
    }


    public async Task<List<UserDto>> GetAllUsersAsync()
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
    }

    public async Task<UserDto> CheckUserCredentialsAsync(string email, string password)
    {
        var grpcResponse = await _grpcClient.GetUserByEmailAsync(new EmailRequest{Email = email});
        if (grpcResponse.Email.IsWhiteSpace() ||
            grpcResponse.DisplayName.IsWhiteSpace())
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
}
