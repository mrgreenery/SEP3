namespace ApiContracts.User.Update;

public record UpdateDisplayNameRequest
{
    public long Id { get; set; }
    public required string DisplayName { get; set; }
};