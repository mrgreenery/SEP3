using ApiContracts;
using ApiContracts.User;
using Microsoft.AspNetCore.Mvc;
using WebAPI.Services;
using WebAPI.Services.Exceptions.User;

namespace WebAPI.Controllers;


[ApiController]
[Route("auth")]
public class AuthController : ControllerBase
{
    private readonly IUserService _userService;
    private readonly ILogger<AuthController> _logger;

    public AuthController(IUserService userService,
        ILogger<AuthController> logger)
    {
        _userService = userService;
        _logger = logger;
    }
    
    [HttpPost("register")]
    public async Task<IResult> Register(
        [FromBody] CreateUserRequest request)
    {
        try
        {
            _logger.LogInformation(
                $"Create user request: email: {request.Email}, " +
                $"display name: {request.DisplayName},");

            var newUserDto =
                await _userService.CreateUserAsync(request.DisplayName,
                    request.Email, request.Password);

            _logger.LogInformation($"Success in creating new user: email: " +
                                   $"{newUserDto.Email}, " +
                                   $"display name: {newUserDto.DisplayName}");

            return Results.Ok(newUserDto);
        }
        catch (UserWithThisEmailAlreadyExists)
        {
            _logger.LogError(
                $"Username with email {request.Email} already exists. Unable to create new user.");
            return Results.BadRequest(new
            {
                error = "Email is already taken",
                field = "Email"
            });
        }
        catch (ArgumentNullException e)
        {
            _logger.LogError(
                $"Required fields in request were empty,");
            return Results.BadRequest(new
            {
                error = "Required fields are empty",
            });
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error while registering user");
            return Results.Problem(
        detail: "Unexpected error occurred while processing your request.",
        statusCode: StatusCodes.Status500InternalServerError,
        title: "Internal Server Error");
        }
    }

    [HttpPost("login")]
    public async Task<IResult> Login(
        [FromBody] LoginRequest request)
    {
        try
        {
            _logger.LogInformation($"Login request: email: {request.Email},"); 
            
            var userDto =
                await _userService.LoginUser(request.Email,
                    request.Password);

            if (userDto is null)
            {
                throw new WrongPasswordException();
            }

            _logger.LogInformation($"Success in signing user in: email: " +
                                   $"{userDto.Email}, " +
                                   $"display name: {userDto.DisplayName}");

            return Results.Ok(userDto);
        }
        catch (UserWithThisEmailDoesNotExist)
        {
            _logger.LogError(
                $"No user with email: {request.Email} was found.");
            return Results.NotFound($"User with id {request.Email} not found.");

        }
        catch (WrongPasswordException e)
        {
            _logger.LogError(
                $"Login request with email: {request.Email} did not match password.");
            return Results.Unauthorized();

        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error while registering user");
             return Results.Problem(
                    detail: "Unexpected error occurred while processing your request.",
                    statusCode: StatusCodes.Status500InternalServerError,
                    title: "Internal Server Error");
        }
    }
}