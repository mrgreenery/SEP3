using ApiContracts;

namespace BlazorApp.Services.Auth;

public interface IUserService
{
    public Task<UserDto> CreateUserAsync(RegisterRequest request);
    public Task UpdateUserAsync(int id, UserDto request);
    public Task DeleteUserAsync(int id);
    public Task<UserDto> GetUserAsync(int id);
    public Task<List<UserDto>> GetAllUsersAsync(string? nameContains = null);
}
