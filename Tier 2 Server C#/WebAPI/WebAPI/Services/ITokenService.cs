using System;

namespace WebAPI.Services;

public interface ITokenService
{
    string GenerateToken(long userId, string email, string displayName, bool rememberMe);
}
