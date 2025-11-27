namespace ApiContracts.User.Update;

public record UpdateUserEmailRequest
{
    public long Id { get; set; }
    public required string Email { get; set; }
};