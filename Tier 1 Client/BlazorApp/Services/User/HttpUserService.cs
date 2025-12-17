using ApiContracts.User;
using System.Text.Json;
using ApiContracts.User.Update;
using BlazorApp.Services.Auth;
using Microsoft.AspNetCore.Components.Authorization;

namespace BlazorApp.Services.User;

public class HttpUserService : IUserService
{
    
    private readonly HttpClient _client;
    private readonly AuthProvider _authProvider;
    
    public HttpUserService (HttpClient client, AuthenticationStateProvider authStateProvider)
    { 
        _client = client;
        _authProvider = (AuthProvider)authStateProvider;
    }

    public async Task UpdateUserNameAsync(long id, string displayName)
    {
        var request = new UpdateDisplayNameRequest
        {
            Id = id,
            DisplayName = displayName
        };

        var response = await _client.PutAsJsonAsync(
            "api/users/display-name", request);  
        
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions{ PropertyNameCaseInsensitive = true })
                          ?? throw new Exception("Could not update user");
        
        // Update Claims after successfully updating user 
        _authProvider.UpdateClaims(updatedUser);
    }

    public async Task UpdateUserEmailAsync(long id, string email)
    {
        //create the request
        var request = new UpdateUserEmailRequest
        {
            Id = id,
            Email = email
        };

        //call the web api endpoint to update email
        var response = await _client.PutAsJsonAsync(
            "api/users/email", request);
        
        //check if the api call was success or not
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to update email. Status: {response.StatusCode}");

        //deserialize the response into UserDto
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions { PropertyNameCaseInsensitive = true }) 
                          ?? throw new Exception("Could not parse updated user");
        
        //update the claims with new email
        _authProvider.UpdateClaims(updatedUser);
    }

   public async Task UpdateUserPasswordAsync(long id, string email, string currentPassword, string newPassword)
    {
        // create the request matching UpdateUserPasswordRequest
        var request = new UpdateUserPasswordRequest
        {
            Id = id,
            Email = email,
            CurrentPassword = currentPassword,
            NewPassword = newPassword
        };

            // call the web api to update the password
            var response = await _client.PutAsJsonAsync(
                "api/users/password", request);

            // check if the api call was success or not
        if (!response.IsSuccessStatusCode)
        {
            if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized)
                throw new Exception("Current password is incorrect.");

            if (response.StatusCode == System.Net.HttpStatusCode.BadRequest)
            {
                var msg = await response.Content.ReadAsStringAsync();
                throw new Exception(string.IsNullOrWhiteSpace(msg) ? "Invalid input." : msg);
            }

            var fallback = await response.Content.ReadAsStringAsync();
            throw new Exception(string.IsNullOrWhiteSpace(fallback)
                ? $"Failed to update password. Status: {(int)response.StatusCode}"
                : fallback);
        }
    }

    public async Task<List<UserDto>> GetAllUsersAsync()
    {
        // call api endpoint to get all users
        var response = await _client.GetAsync("api/users");

        // check if response is succes or not
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to fetch users. Status: {response.StatusCode}");

        // deseralize the respond to List<UserDto>
        return await response.Content.ReadFromJsonAsync<List<UserDto>>(
                   new JsonSerializerOptions { PropertyNameCaseInsensitive = true })
               ?? new List<UserDto>();
    }
    
    public async Task DeleteUserAsync(long id)
    {
        // Call api to delete user
        var response = await _client.DeleteAsync($"api/users/{id}");

        // If delete failed, throw an exception
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to delete user. Status: {response.StatusCode}");
    }
}