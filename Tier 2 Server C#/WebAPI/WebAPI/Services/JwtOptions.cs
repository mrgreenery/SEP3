using System;

namespace WebAPI.Services;

public class JwtOptions
{
    public string Key { get; set; } = string.Empty;
    public string Issuer { get; set; } = string.Empty;
    public string Audience { get; set; } = string.Empty;
    public int ShortSessionMinutes { get; set; }
    public int RememberMeMinutes { get; set; }

}
