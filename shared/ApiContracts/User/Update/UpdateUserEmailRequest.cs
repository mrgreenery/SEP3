namespace ApiContracts.User.Update;

public record UpdateUserEmailRequest
{
    public int Id { get; set; }
    public required string Email { get; set; }
};