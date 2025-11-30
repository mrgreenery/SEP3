using System.ComponentModel.DataAnnotations;

namespace ApiContracts.User;

// Returned when login/register request is successful
public record UserDto
{
    public required long Id { get; set; }
    
    [EmailAddress(ErrorMessage = "Invalid email format")] 
    public required string Email { get; set; }
    public required string DisplayName { get; set; }
}