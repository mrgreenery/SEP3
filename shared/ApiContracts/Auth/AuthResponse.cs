using ApiContracts.User;

namespace ApiContracts.Auth;

public class AuthResponse
{
    public required UserDto User { get; init; }
    public required string Token { get; init; }
    public required DateTime ExpiresAt { get; init; }
}
