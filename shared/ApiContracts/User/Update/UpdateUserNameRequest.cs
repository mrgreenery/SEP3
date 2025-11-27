namespace ApiContracts.User.Update;

public record UpdateUserNameRequest
{
    public long Id { get; set; }
    public required string DisplayName { get; set; }
};