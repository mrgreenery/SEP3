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


    public async Task<UserDto> CreateUserAsync(CreateUserRequest request)
    {
    
        var respond = await client.PostAsJsonAsync("quests",  request);
        
        respond.EnsureSuccessStatusCode();
        
        return await respond.Content.ReadFromJsonAsync<UserDto>(
            new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
            ?? throw new Exception("Could not create user");
        
    }

    public Task UpdateUserNameAsync(int id, string displayName)
    {
        throw new NotImplementedException();
    }

    public Task UpdateUserEmailAsync(int id, string email)
    {
        throw new NotImplementedException();
    }

    public Task UpdateUserPasswordAsync(int id, string password)
    {
        throw new NotImplementedException();
    }

    // public  async Task<UserDto> GetUserAsync(int id)
    // {
    //     var respond = await client.GetAsync($"users/{id}");
    //     
    //     respond.EnsureSuccessStatusCode();
    //     
    //     return await respond.Content.ReadFromJsonAsync<UserDto>(
    //         new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
    //         ?? throw new Exception("Could not get user");
    // }

    public Task<List<UserDto>> GetAllUsersAsync()
    {
        throw new NotImplementedException();
    }


    public Task DeleteUserAsync(int id)
    {
        throw new NotImplementedException();
    }
    
    public Task UpdateUserAsync(int id, UserDto request)
    {
        throw new NotImplementedException();
    }
}