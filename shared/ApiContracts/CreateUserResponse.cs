using System;

namespace WebAPI.ApiContracts;

public class CreateUserResponse
{
    public required long Id { get; set; }
    public required string Email { get; set; }
    public required string DisplayName { get; set; }
    public required string Password { get; set; }

}
