using System.Security.Claims;
using System.Text.Json;
using ApiContracts;
using Microsoft.AspNetCore.Components.Authorization;
using WebAPI.ApiContracts;

namespace BlazorApp.Services.Auth;

public class AuthProvider (HttpClient client) : AuthenticationStateProvider
{
    private ClaimsPrincipal currentClaimsPrincipal;
    
    public override async Task<AuthenticationState> GetAuthenticationStateAsync()
    {
        return new AuthenticationState(currentClaimsPrincipal ?? new());
    }

    public async Task Login(string email, string password)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync(
            "auth/login", new LoginRequest
            {
                Email = email,
                Password = password
            });

        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        await AddClaims(content);
    }

    public async Task Register(string displayName, string email,
        string password)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync(
            "auth/register", new RegisterRequest()
            {
                DisplayName = displayName,
                Email = email,
                Password = password
            });

        string content = await response.Content.ReadAsStringAsync();

        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

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
            new Claim(ClaimTypes.Name, authUser.DisplayName),
            new Claim(ClaimTypes.Email, authUser.Email),
            // new Claim(ClaimTypes.Id, authUser.Id)
        };
        
        ClaimsIdentity identity = new ClaimsIdentity(claims, "apiauth");

        currentClaimsPrincipal = new ClaimsPrincipal(identity);
        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal))
        );
    }

    
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