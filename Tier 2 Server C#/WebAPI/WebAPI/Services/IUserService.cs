using ApiContracts;
using ApiContracts.User;
using Data;
using WebAPI.Entities;

namespace WebAPI.Services;


public interface IUserService
{
    Task<UserDto> CreateUserAsync(string displayName, string email, string password);

    // Task<UserDto> GetUserByEmailAsync(string email);

    Task<List<UserDto>> GetAllUsersAsync();

    Task<UserDto?> LoginUser(string email, string password);

    Task<UserDto> UpdateUserNameAsync(long id, string displayName,
        string email, string password);

    Task DeleteUserAsync(string email);
}
