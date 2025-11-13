using System;

namespace WebAPI.ApiContracts;

public class CreateUserRequest
{
    public required string Email { get; set; }
    public required string Password { get; set; }
    public required string DisplayName { get; set; }

}
