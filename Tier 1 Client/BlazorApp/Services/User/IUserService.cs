using ApiContracts;
using ApiContracts.User;

namespace BlazorApp.Services.Auth;

public interface IUserService
{
    public Task UpdateUserNameAsync(int id, string displayName);
    public Task UpdateUserEmailAsync(int id, string email);
    public Task UpdateUserPasswordAsync(int id, string password);
    public Task DeleteUserAsync(int id);
    public Task<List<UserDto>> GetAllUsersAsync();
}
