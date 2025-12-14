namespace ApiContracts.User.Update;

public record UpdateUserPasswordRequest
{
    public long Id { get; set; }
    public required string CurrentPassword { get; set; }
    public string Email { get; set; }
    public required string NewPassword { get; set; }
    

};