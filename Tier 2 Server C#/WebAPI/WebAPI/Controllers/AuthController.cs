using ApiContracts;
using Microsoft.AspNetCore.Mvc;
using WebAPI.ApiContracts;
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
    public async Task<ActionResult<UserDto>> Register(
        [FromBody] RegisterRequest request)
    {
        try
        {
            _logger.LogInformation(
                $"Register request: email: {request.Email}, " +
                $"display name: {request.DisplayName}," +
                $" password: {request.Password}");

            var newUserDto =
                await _userService.CreateUserAsync(request.DisplayName,
                    request.Email, request.Password);

            _logger.LogInformation($"Success in creating new user: email: " +
                                   $"{newUserDto.Email}, " +
                                   $"display name: {newUserDto.DisplayName}");

            return newUserDto;
        }
        catch (UserWithThisEmailAlreadyExists)
        {
            _logger.LogError(
                $"Username with email {request.Email} already exists. Unable to create new user.");
            return StatusCode(401, "This email is already in use");
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error while registering user");
            return StatusCode(401, "Unable to register. Try again later.");
        }
    }

    [HttpPost("login")]
    public async Task<ActionResult<UserDto>> Login(
        [FromBody] LoginRequest request)
    {
        try
        {
            _logger.LogInformation($"Login request: email: {request.Email}," +
                                   $" password: {request.Password}");

            var userDto =
                await _userService.CheckUserCredentialsAsync(request.Email,
                    request.Password);

            _logger.LogInformation($"Success in signing user in: email: " +
                                   $"{userDto.Email}, " +
                                   $"display name: {userDto.DisplayName}");

            return userDto;
        }
        catch (UserWithThisEmailDoesNotExist)
        {
            _logger.LogError(
                $"No user with email: {request.Email} was found.");
            return StatusCode(401, "This email is not registered. Sign up first.");

        }
        catch (WrongPasswordException e)
        {
            _logger.LogError(
                $"Login request with email: {request.Email} did not match password.");
            return StatusCode(401, "Wrong email or password");
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error while registering user");
            return StatusCode(401, "Unable to login. Try again later.");
        }
    }
}