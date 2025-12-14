using System.Security.Claims;
using System.Text.Json;
using ApiContracts.User;
using ApiContracts.Auth;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.JSInterop;
using System.Threading.Tasks;

namespace BlazorApp.Services.Auth;

public class AuthProvider  : AuthenticationStateProvider
{
    private readonly HttpClient client;
    private readonly IJSRuntime jsRuntime;
    private ClaimsPrincipal currentClaimsPrincipal = new();

    private const string AuthStorageKey = "authState";

     public AuthProvider(HttpClient client, IJSRuntime jsRuntime)
    {
        this.client = client;
        this.jsRuntime = jsRuntime;
    }
    public override Task<AuthenticationState> GetAuthenticationStateAsync()
    {
        var principal = currentClaimsPrincipal ?? new ClaimsPrincipal();
        return Task.FromResult(new AuthenticationState(principal));
    }

    public async Task TryRestoreSessionAsync()
{
    string? json;

    try
    {
        json = await jsRuntime.InvokeAsync<string?>(
            "localStorage.getItem", AuthStorageKey);
    }
    catch
    {
        return;
    }

    if (string.IsNullOrWhiteSpace(json))
        return;

    var auth = JsonSerializer.Deserialize<AuthResponse>(
        json,
        new JsonSerializerOptions { PropertyNameCaseInsensitive = true });

    if (auth is null || auth.User is null)
        return;

    // Only enforce expire if ExpiresAt was actually set
    if (auth.ExpiresAt != default && auth.ExpiresAt <= DateTime.UtcNow)
    {
        await jsRuntime.InvokeVoidAsync("localStorage.removeItem", AuthStorageKey);
        return;
    }

    var user = auth.User;

    var claims = new List<Claim>
    {
        new("Id", user.Id.ToString()),
        new(ClaimTypes.Name, user.DisplayName),
        new(ClaimTypes.Email, user.Email),
        new("token", auth.Token)
    };

    var identity = new ClaimsIdentity(claims, "apiauth");
    currentClaimsPrincipal = new ClaimsPrincipal(identity);

    NotifyAuthenticationStateChanged(
        Task.FromResult(new AuthenticationState(currentClaimsPrincipal)));
}

    public async Task LoginUser(string email, string password, bool rememberMe)
    {

        LoginRequest request = new LoginRequest
        {
            Email = email,
            Password = password,
            RememberMe = rememberMe
        };

        HttpResponseMessage response = await client.PostAsJsonAsync(
            "auth/login", request);

        // Check if response message is successful
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        
        // If response is successful, add claims
        await AddClaims(content, rememberMe);
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
        await AddClaims(content,rememberMe: false);
    }

    public async Task Logout()
    {
        currentClaimsPrincipal = new(); 
        await jsRuntime.InvokeVoidAsync("localStorage.removeItem", AuthStorageKey);
        NotifyAuthenticationStateChanged(Task.FromResult(
            new AuthenticationState(currentClaimsPrincipal))
        );
    }

    private async Task AddClaims(string response, bool rememberMe)
    {
        AuthResponse auth =
        JsonSerializer.Deserialize<AuthResponse>(response,
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            })?? throw new Exception("Invalid auth response from server.");;


        UserDto authUser = auth.User;


            // Build claims
        List<Claim> claims = new List<Claim>()
        {
            new Claim("Id", authUser.Id.ToString()),
            new Claim(ClaimTypes.Name, authUser.DisplayName),
            new Claim(ClaimTypes.Email, authUser.Email),
            new Claim("token", auth.Token)
        };

        
        var identity = new ClaimsIdentity(claims, "apiauth");

        currentClaimsPrincipal = new ClaimsPrincipal(identity);

        if (rememberMe)
        {
            string json = JsonSerializer.Serialize(auth);
            await jsRuntime.InvokeVoidAsync("localStorage.setItem", AuthStorageKey, json);
        }

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

    public async Task UpdateClaimsAndPersistAsync(UserDto dto)
    {
        // rebuild claims (keep token!)
        var token = currentClaimsPrincipal.FindFirst("token")?.Value;

        var claims = new List<Claim>
        {
            new Claim("Id", dto.Id.ToString()),
            new Claim(ClaimTypes.Name, dto.DisplayName),
            new Claim(ClaimTypes.Email, dto.Email),
        };

        if (!string.IsNullOrWhiteSpace(token))
            claims.Add(new Claim("token", token));

        var identity = new ClaimsIdentity(claims, "apiauth");
        currentClaimsPrincipal = new ClaimsPrincipal(identity);

        // update localStorage if authState exists
        try
        {
            var json = await jsRuntime.InvokeAsync<string?>("localStorage.getItem", AuthStorageKey);
            if (!string.IsNullOrWhiteSpace(json))
            {
                var existing = JsonSerializer.Deserialize<AuthResponse>(
                    json, new JsonSerializerOptions { PropertyNameCaseInsensitive = true });

                if (existing != null)
                {
                    var updated = new AuthResponse
                    {
                        User = dto,
                        Token = existing.Token,
                        ExpiresAt = existing.ExpiresAt
                    };

                    var updatedJson = JsonSerializer.Serialize(updated);
                    await jsRuntime.InvokeVoidAsync("localStorage.setItem", AuthStorageKey, updatedJson);
                }
            }
        }
        catch { }


        NotifyAuthenticationStateChanged(
            Task.FromResult(new AuthenticationState(currentClaimsPrincipal)));
    }

}