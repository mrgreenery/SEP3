using System;

namespace WebAPI.Services;
using WebAPI.ApiContracts;

public interface IUserService
{
    Task<CreateUserResponse> CreateUserAsync(ApiContracts.CreateUserRequest request);

}
