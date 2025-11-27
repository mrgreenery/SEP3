namespace ApiContracts.User.Update;

public record UpdateUserPasswordRequest
{
    public long Id { get; set; }
    public required string Password { get; set; }
};