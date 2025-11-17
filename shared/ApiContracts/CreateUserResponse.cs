using System;

namespace ApiContracts;

public record CreateUserResponse
{
    public required long Id { get; set; }
    public required string Email { get; set; }
    public required string DisplayName { get; set; }
    public required string Password { get; set; }

}
