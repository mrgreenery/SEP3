using ApiContracts.User;

namespace WebAPI.Services.Interfaces;

public interface IUserService
{
    Task<UserDto> CreateUserAsync(string displayName, string email, string password);

    Task<List<UserDto>> GetAllUsersAsync();

    Task<UserDto?> LoginUser(string email, string password);

    Task<UserDto> UpdateDisplayNameAsync(long id, string displayName);

    Task<UserDto> UpdateUserEmailAsync(long id, string email);

    Task UpdateUserPasswordAsync(long id, string newPassword);

    Task DeleteUserAsync(long id);
}
