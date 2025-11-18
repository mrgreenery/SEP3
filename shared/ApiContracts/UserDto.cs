namespace ApiContracts;

// Returned when login/register request is successful
public record UserDto
{
    public required string Email { get; set; }
    public required string DisplayName { get; set; }
}