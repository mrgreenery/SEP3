using System.Text.Json;
using ApiContracts;

namespace BlazorApp.Services.Auth;



public class HttpUserService : IUserService
{
    
    private readonly HttpClient client;
    
    public HttpUserService (HttpClient client)
    { 
        this.client = client;
    }


    public async Task<UserDto> CreateUserAsync(RegisterRequest request)
    {
    
        var respond = await client.PostAsJsonAsync("quests",  request);
        
        respond.EnsureSuccessStatusCode();
        
        return await respond.Content.ReadFromJsonAsync<UserDto>(
            new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
            ?? throw new Exception("Could not create user");
        
    }
    
    public  async Task<UserDto> GetUserAsync(int id)
    {
        var respond = await client.GetAsync($"users/{id}");
        
        respond.EnsureSuccessStatusCode();
        
        return await respond.Content.ReadFromJsonAsync<UserDto>(
            new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
            ?? throw new Exception("Could not get user");
    }
    
    
    public Task DeleteUserAsync(int id)
    {
        throw new NotImplementedException();
    }
    
    public Task UpdateUserAsync(int id, UserDto request)
    {
        throw new NotImplementedException();
    }

    public Task<List<UserDto>> GetAllUsersAsync(string? nameContains = null)
    {
        throw new NotImplementedException();
    }
}