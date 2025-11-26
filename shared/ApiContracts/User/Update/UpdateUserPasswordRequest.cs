namespace ApiContracts.User.Update;

public record UpdateUserPasswordRequest
{
    public int Id { get; set; }
    public required string Password { get; set; }
};