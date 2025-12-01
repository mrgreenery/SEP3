using ApiContracts;
using ApiContracts.User;

namespace BlazorApp.Services.Auth;

public interface IUserService
{
    public Task UpdateUserNameAsync(long id, string displayName);
    public Task UpdateUserEmailAsync(long id, string email);
    public Task UpdateUserPasswordAsync(long id, string password);
    public Task DeleteUserAsync(long id);
    public Task<List<UserDto>> GetAllUsersAsync();
}
