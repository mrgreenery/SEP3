using WebAPI.ApiContracts;

namespace WebAPI.Services;


public interface IUserService
{
    Task<CreateUserResponse> CreateUserAsync(ApiContracts.CreateUserRequest request);

}
