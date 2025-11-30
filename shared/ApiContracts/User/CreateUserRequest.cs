using System.ComponentModel.DataAnnotations;

namespace ApiContracts.User;

public record CreateUserRequest
{
   [EmailAddress(ErrorMessage = "Invalid email format")]
    public required string Email { get; set; }
    public required string Password { get; set; }
    public required string DisplayName { get; set; }
}