
using WebAPI.ApiContracts;
using Data;
using Grpc.Net.Client;

namespace WebAPI.Services;

public class UserServiceImpl : IUserService
{
    private readonly UserService.UserServiceClient _grpcClient;

    public UserServiceImpl()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");

        _grpcClient = new UserService.UserServiceClient(channel);
    }

    public async Task<CreateUserResponse> CreateUserAsync(ApiContracts.CreateUserRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));

        var userEntity = new UserEntity
        {
            Email        = request.Email,
            Password     = request.Password,
            DisplayName  = request.DisplayName
        };

        var grpcRequest = new Data.CreateUserRequest
        {
            User = userEntity
        };

        var grpcResponse = await _grpcClient.CreateUserAsync(grpcRequest);

        var response = new CreateUserResponse
        {
            Id          = grpcResponse.Id,
            Email       = grpcResponse.Email,
            DisplayName = grpcResponse.DisplayName,
            Password   = grpcResponse.Password
        };

        return response;
    }
}
