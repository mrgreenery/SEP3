using System.Security.Claims;
using System.Text.Json;
using ApiContracts.User;
using Microsoft.AspNetCore.Components.Authorization;

namespace BlazorApp.Services.Auth;

public class AuthProvider (HttpClient client) : AuthenticationStateProvider
{
    private ClaimsPrincipal? _currentClaimsPrincipal;
    
    public override async Task<AuthenticationState> GetAuthenticationStateAsync()
    {
        return new AuthenticationState(_currentClaimsPrincipal ?? new());
    }

    public async Task<long?> GetUserIdAsync()
    {
        var authState = await GetAuthenticationStateAsync();
        var user = authState.User;
        if (user?.Identity?.IsAuthenticated != true)
            return null;

        var idClaim = user.FindFirst("Id");
        if (idClaim == null)
            return null;

        if (long.TryParse(idClaim.Value, out var id))
            return id;

        return null;
    }

    public async Task LoginUser(string email, string password)
    {
        // Build a login request
        LoginRequest request = new LoginRequest
        {
            Email = email,
            Password = password
        };
        
        // Send request and receive response message
        HttpResponseMessage response = await client.PostAsJsonAsync(
            "auth/login", request);

        // Check if response message is successful
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        
        // If response is successful, add claims
        await AddClaims(content);
    }

    public async Task CreateUser(string displayName, string email,
        string password)
    {
        // Build create user request
        CreateUserRequest request = new CreateUserRequest()
        {
            DisplayName = displayName,
            Email = email,
            Password = password
        };
        
        // Send request and receive response message
        HttpResponseMessage response = await client.PostAsJsonAsync(
            "auth/register", request);

        // Check if response message is successful
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        // If response is successful, add claims
        await AddClaims(content);
    }

    public void Logout()
    {
        _currentClaimsPrincipal = new(); 
        NotifyAuthenticationStateChanged(Task.FromResult(
            new AuthenticationState(_currentClaimsPrincipal))
        );
    }

    private async Task AddClaims(string response)
    {
        UserDto authUser =
            JsonSerializer.Deserialize<UserDto>(response,
                new JsonSerializerOptions
                {
                    PropertyNameCaseInsensitive = true
                })!;

        List<Claim> claims = new List<Claim>()
        {
            new Claim("Id", authUser.Id.ToString()),
            new Claim(ClaimTypes.Name, authUser.DisplayName),
            new Claim(ClaimTypes.Email, authUser.Email),
        };
        
        ClaimsIdentity identity = new ClaimsIdentity(claims, "apiauth");

        _currentClaimsPrincipal = new ClaimsPrincipal(identity);
        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(_currentClaimsPrincipal))
        );
    }

    public void UpdateClaims(UserDto dto)
    {
        List<Claim> claims = new List<Claim>()
        {
            new Claim("Id", dto.Id.ToString()),
            new Claim(ClaimTypes.Name, dto.DisplayName),
            new Claim(ClaimTypes.Email, dto.Email),
        };
        
        ClaimsIdentity identity = new ClaimsIdentity(claims, "apiauth");

        _currentClaimsPrincipal = new ClaimsPrincipal(identity);
        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(_currentClaimsPrincipal))
        );
    }
}