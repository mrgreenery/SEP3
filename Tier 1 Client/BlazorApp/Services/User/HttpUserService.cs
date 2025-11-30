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
        var request = new UpdateDisplayNameRequest
        {
            Id = id,
            DisplayName = displayName
        };

        var response = await client.PutAsJsonAsync(
            "api/users/display-name", request);  
        
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions{ PropertyNameCaseInsensitive = true })
                          ?? throw new Exception("Could not update user");
        
        // Update Claims after successfully updating user 
        authProvider.UpdateClaims(updatedUser);
    }

    public async Task UpdateUserEmailAsync(int id, string email)
    {
        //create the request
        var request = new UpdateUserEmailRequest
        {
            Id = id,
            Email = email
        };

        //call the web api endpoint to update email
        var response = await client.PutAsJsonAsync(
            "api/users/email", request);

        
        //check if the api call was success or not
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to update email. Status: {response.StatusCode}");

        //deserialize the response into UserDto
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions { PropertyNameCaseInsensitive = true }) 
                          ?? throw new Exception("Could not parse updated user");
        
        //update the claims with new email
        authProvider.UpdateClaims(updatedUser);
    }

    public async Task UpdateUserPasswordAsync(int id, string password)
    {
        // ccreate the request
        var request = new UpdateUserPasswordRequest
        {
            Id = id,
            Password = password
        };

        // call the web api to update the password
        var response = await client.PutAsJsonAsync(
            "api/users/password", request);

        // check if the api call was success or not
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to update password. Status: {response.StatusCode}");

        // deserialize the response to userDto
        // (even tho nothing changed in dto bc we dont send the password xd)
        var updatedUser = await response.Content.ReadFromJsonAsync<UserDto>(
                              new JsonSerializerOptions { PropertyNameCaseInsensitive = true })
                          ?? throw new Exception("Could not parse updated user");

        // updating the claim buuuuuut in the end nothing changes for the claim..... so why do we keep this? 
        authProvider.UpdateClaims(updatedUser);
    }

    public async Task<List<UserDto>> GetAllUsersAsync()
    {
        // call api endpoint to get all users
        var response = await client.GetAsync("api/users");

        // check if response is succes or not
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to fetch users. Status: {response.StatusCode}");

        // deseralize the respond to List<UserDto>
        return await response.Content.ReadFromJsonAsync<List<UserDto>>(
                   new JsonSerializerOptions { PropertyNameCaseInsensitive = true })
               ?? new List<UserDto>();
    }
    
    public async Task DeleteUserAsync(int id)
    {
        // Call api to delete user
        var response = await client.DeleteAsync($"api/users/{id}");

        // If delete failed, throw an exception
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to delete user. Status: {response.StatusCode}");
        
    }
}