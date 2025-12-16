namespace WebAPI.Services.Interfaces;

public interface ITokenService
{
    string GenerateToken(long userId, string email, string displayName, bool rememberMe);
}
