using ApiContracts;

namespace BlazorApp.Services.Auth;

public interface IUserService
{
    public Task<UserDto> CreateUserAsync(CreateUserRequest request);
    public Task UpdateUserNameAsync(int id, string displayName);
    public Task UpdateUserEmailAsync(int id, string email);
    public Task UpdateUserPasswordAsync(int id, string password);
    public Task DeleteUserAsync(int id);
    
    // public Task<UserDto> GetUserAsync(int id);
    public Task<List<UserDto>> GetAllUsersAsync();
}
