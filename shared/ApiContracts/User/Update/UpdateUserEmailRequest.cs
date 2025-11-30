using System.ComponentModel.DataAnnotations;

namespace ApiContracts.User.Update;

public record UpdateUserEmailRequest
{
    public long Id { get; set; }
    [EmailAddress (ErrorMessage = "Invalid email format")]
    public required string Email { get; set; }
};