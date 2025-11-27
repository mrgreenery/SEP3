using ApiContracts.User;
using System.Security.Claims;
using System.Text.Json;
using ApiContracts.User.Update;
using BlazorApp.Services.Auth;
using Microsoft.AspNetCore.Components.Authorization;

namespace BlazorApp.Services.User;

public class HttpUserService : IUserService
{
    
    private readonly HttpClient client;
    private readonly AuthProvider authProvider;
    
    public HttpUserService (HttpClient client, AuthenticationStateProvider authStateProvider)
    { 
        this.client = client;
        authProvider = (AuthProvider)authStateProvider;
    }

    public async Task UpdateUserNameAsync(int id, string displayName)
    {
        var request = new UpdateUserNameRequest
        {
            Id = id,
            DisplayName = displayName
        };

        var response = await client.PutAsJsonAsync(
            $"users/{id}/email", request);
        
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions{ PropertyNameCaseInsensitive = true })
                          ?? throw new Exception("Could not update user");
        
        // Update Claims after successfully updating user 
        authProvider.UpdateClaims(updatedUser);
    }

    public Task UpdateUserEmailAsync(int id, string email)
    {
        throw new NotImplementedException();
    }

    public Task UpdateUserPasswordAsync(int id, string password)
    {
        throw new NotImplementedException();
    }

    public Task<List<UserDto>> GetAllUsersAsync()
    {
        throw new NotImplementedException();
    }
    
    public Task DeleteUserAsync(int id)
    {
        throw new NotImplementedException();
    }
}