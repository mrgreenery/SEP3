namespace ApiContracts.User.Update;

public record UpdateUserNameRequest
{
    public int Id { get; set; }
    public required string DisplayName { get; set; }
};