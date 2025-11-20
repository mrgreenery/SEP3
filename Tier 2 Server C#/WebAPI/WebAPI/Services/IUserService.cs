using ApiContracts;
using WebAPI.ApiContracts;

namespace WebAPI.Services;


public interface IUserService
{
    Task<UserDto> CreateUserAsync(string displayName, string email, string password);

    Task<UserDto> GetUserByEmailAsync(string email);

    Task<List<UserDto>> GetAllUsersAsync();

    Task<UserDto?> CheckUserCredentialsAsync(string email, string password);

    Task<UserDto> UpdateUserAsync(string oldEmail, string displayName,
        string newEmail, string password);

    Task DeleteUserAsync(string email);

}
