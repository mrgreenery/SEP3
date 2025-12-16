using ApiContracts.User;

namespace BlazorApp.Services.User;

public interface IUserService
{
    public Task UpdateUserNameAsync(long id, string displayName);
    public Task UpdateUserEmailAsync(long id, string email);
    public Task UpdateUserPasswordAsync(long id, string email, string currentPassword, string newPassword);
    public Task DeleteUserAsync(long id);
    public Task<List<UserDto>> GetAllUsersAsync();
}
