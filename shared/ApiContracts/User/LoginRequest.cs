using System.ComponentModel.DataAnnotations;

namespace ApiContracts.User;

public class LoginRequest
{
    [EmailAddress(ErrorMessage = "Invalid email format")]
    public required string Email { get; set; }
    public required string Password { get; set; }

    public bool RememberMe { get; set; }
}