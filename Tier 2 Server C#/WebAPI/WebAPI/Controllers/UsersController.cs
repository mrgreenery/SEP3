
using ApiContracts.User.Update;
using Grpc.Core;
using WebAPI.Services;
using Microsoft.AspNetCore.Mvc;
using WebAPI.Services.Exceptions.User;

namespace WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class UsersController : ControllerBase
{
    private readonly IUserService _userService;
    private readonly ILogger<UsersController> _logger;

    public UsersController(IUserService userService, ILogger<UsersController> logger)
    {
        _userService = userService;
        _logger = logger;
    }
    
    // GET api/users
    [HttpGet]
    public async Task<IResult> GetAll()
    {
        _logger.LogInformation("GET all users requested");

        
        try
        {
            //send request to service to get all users
            var users = await _userService.GetAllUsersAsync();
            _logger.LogInformation("GET all users succeeded. Count={Count}", users.Count);
            return Results.Ok(users); // status code 200
        }
        catch (InvalidDataException ex)
        {
            // service says "no users"
            _logger.LogWarning(ex, "No users found");
            return Results.NotFound("No users found."); // 404
            
        }
        catch (RpcException ex)
        {
            // gRPC failure from Tier 3
            _logger.LogError(ex, "gRPC failure in GetAllUsers");
            return Results.Problem(
                title: "Database communication failed",
                statusCode: StatusCodes.Status500InternalServerError
            );
        }
        catch (Exception ex)
        {
            // unexpected
            _logger.LogError(ex, "GET all users failed");
            return Results.Problem(
                title: "Failed to retrieve users",
                statusCode: StatusCodes.Status500InternalServerError
            );
        }
    }
    
    // PUT api/users/display-name
    [HttpPut("display-name")]
    public async Task<IResult> UpdateDisplayName([FromBody] UpdateDisplayNameRequest request)
    {
        _logger.LogInformation(
            "Update display name requested. UserId={UserId}, NewDisplayName={DisplayName}",
            request.Id, request.DisplayName);

        
        //check if its empty (maybe the request didnt came from tier1)
        //if we need to impllement tokens than delete this
        if (string.IsNullOrWhiteSpace(request.DisplayName))
            return Results.BadRequest("DisplayName cannot be empty."); //400 bad request

        if (request.Id <= 0)
        {
            _logger.LogWarning("Update User validation failed: invalid id {Id}", request.Id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }
        
        try
        {
            //send request to service to update DisplayName
            var dto = await _userService.UpdateUserNameAsync(request.Id, request.DisplayName);
            _logger.LogInformation("Update display name succeeded. UserId={UserId}", dto.Id);
            return Results.Ok(dto); //200 OK
        }
        catch (UserWithThisIdDoesNotExist)
        {
            return Results.NotFound($"User with id {request.Id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Update display name failed. UserId={UserId}", request.Id);
            return Results.Problem(
                title: "Failed to update display name",
                statusCode: StatusCodes.Status500InternalServerError); // 500 internal server error
        }
    }
    
    // PUT api/users/email
    [HttpPut("email")]
    public async Task<IResult> UpdateEmail([FromBody] UpdateUserEmailRequest request)
    {
        _logger.LogInformation(
            "Update email requested. UserId={UserId}, NewEmail={Email}",
            request.Id, request.Email);

        //check if its empty (maybe the request didnt came from tier1)
        // //if we need to impllement tokens than delete this
        if (string.IsNullOrWhiteSpace(request.Email))
        {
            _logger.LogWarning("Update email failed validation. UserId={UserId}", request.Id);
            return Results.BadRequest("Email cannot be empty."); // 400 Bad Request
        }
        
        if (request.Id <= 0)
        {
            _logger.LogWarning("Update User validation failed: invalid id {Id}", request.Id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }

        //send request to service to update email
        try
        {
            var dto = await _userService.UpdateUserEmailAsync(request.Id, request.Email);
            _logger.LogInformation("Update email succeeded. UserId={UserId}", dto.Id);
            return Results.Ok(dto); // 200 OK
        }
        catch (UserWithThisIdDoesNotExist)
        {
            _logger.LogWarning("Update email failed, user not found. UserId={UserId}", request.Id);
            return Results.NotFound($"User with id {request.Id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Update email failed unexpectedly. UserId={UserId}", request.Id);
            return Results.Problem(
                title: "Failed to update email",
                statusCode: StatusCodes.Status500InternalServerError
            ); // 500 internal server error
        }
    }
    
    
    // PUT api/users/password
    [HttpPut("password")]
    public async Task<IResult> UpdatePassword([FromBody] UpdateUserPasswordRequest request)
    {
        
        
        _logger.LogInformation("Update password requested. UserId={UserId}", request.Id);

        
        
        //check if its empty (maybe the request didnt came from tier1)
        // //if we need to impllement tokens than delete this
        if (string.IsNullOrWhiteSpace(request.Password))
        {
            _logger.LogWarning("Update password failed validation. UserId={UserId}", request.Id);
            return Results.BadRequest("Password cannot be empty."); // 400 Bad Request
        }
        
        if (request.Id <= 0)
        {
            _logger.LogWarning("Update User validation failed: invalid id {Id}", request.Id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }

        try
        {
            //send request to service to update password
            var dto = await _userService.UpdateUserPasswordAsync(request.Id, request.Password);
            _logger.LogInformation("Update password succeeded. UserId={UserId}", dto.Id);
            return Results.Ok(dto); // 200 OK
        }
        catch (UserWithThisIdDoesNotExist)
        {
            _logger.LogWarning("Update password failed, user not found. UserId={UserId}", request.Id);
            return Results.NotFound($"User with id {request.Id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Update password failed unexpectedly. UserId={UserId}", request.Id);
            return Results.Problem(
                title: "Failed to update password",
                statusCode: StatusCodes.Status500InternalServerError
            ); // 500 internal server error
        }
    }
    
    
    // DELETE api/users/{id}
    [HttpDelete("{id:long}")]
    public async Task<IResult> Delete(long id)
    {
        _logger.LogInformation("Delete user requested. UserId={UserId}", id);

        if (id <= 0)
        {
            _logger.LogWarning("Delete User validation failed: invalid id {Id}", id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }
        
        try
        {
            //send request to service to delete user
            await _userService.DeleteUserAsync(id);
            _logger.LogInformation("Delete user succeeded. UserId={UserId}", id);
            return Results.NoContent(); // 204 No Content
        }
        catch (UserWithThisIdDoesNotExist)
        {
            _logger.LogWarning("Delete user failed, user not found. UserId={UserId}", id);
            return Results.NotFound($"User with id {id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Delete user failed unexpectedly. UserId={UserId}", id);
            return Results.Problem(
                title: "Failed to delete user",
                statusCode: StatusCodes.Status500InternalServerError
            ); // 500 internal server error
        }
    }

}

