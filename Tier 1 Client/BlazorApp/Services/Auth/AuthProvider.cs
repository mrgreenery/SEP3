using System.Security.Claims;
using System.Text.Json;
using ApiContracts.User;
using Microsoft.AspNetCore.Components.Authorization;

namespace BlazorApp.Services.Auth;

public class AuthProvider (HttpClient client) : AuthenticationStateProvider
{
    private ClaimsPrincipal currentClaimsPrincipal;
    
    public override async Task<AuthenticationState> GetAuthenticationStateAsync()
    {
        return new AuthenticationState(currentClaimsPrincipal ?? new());
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
        currentClaimsPrincipal = new(); 
        NotifyAuthenticationStateChanged(Task.FromResult(
            new AuthenticationState(currentClaimsPrincipal))
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

        currentClaimsPrincipal = new ClaimsPrincipal(identity);
        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal))
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

        currentClaimsPrincipal = new ClaimsPrincipal(identity);
        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal))
        );
    }

    //TODO: remove later
    public void ForceLoginForTesting()
    {
        var claims = new List<Claim>
        {
            new Claim(ClaimTypes.Name, "TestUser"),
            new Claim(ClaimTypes.Email, "test@test.dev"),
            new Claim(ClaimTypes.Role, "User")
        };

        var identity = new ClaimsIdentity(claims, "FakeAuth");
        currentClaimsPrincipal = new ClaimsPrincipal(identity);

        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal))
        );
    }
    public void ForceLogoutForTesting()
    {
        currentClaimsPrincipal = new ClaimsPrincipal(new ClaimsIdentity());

        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal))
        );
    }
}