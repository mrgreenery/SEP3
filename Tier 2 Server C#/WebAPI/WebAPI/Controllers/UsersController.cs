
using ApiContracts;
using WebAPI.ApiContracts;
using WebAPI.Services;
using Microsoft.AspNetCore.Mvc;

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
    
    // Delete user controller to be swapped with AuthController?

    [HttpPost]
    public async Task<ActionResult<RegisterRequest>> CreateUser(
        [FromBody] RegisterRequest request)
    {
        try
        {
            _logger.LogInformation("Creating user with email {Email}", request.Email);
    
            var response = await _userService.CreateUserAsync(request);
    
            _logger.LogInformation("User created successfully with ID {Id}", response.Id);
    
            return Created($"/api/users/{response.Id}", response);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error while creating user");
            return StatusCode(500, new { error = e.Message });
        }
    }
    
    [HttpGet("{id:long}")]
    public ActionResult<CreateUserResponse> GetUser(long id)
    {
        try
        {
            var response = new CreateUserResponse
            {
                Id = id,
                Email = "dummy@example.com",
                DisplayName = "Dummy User",
                Password = "password123"
            };
    
            return Ok(response);
        }
        catch (Exception e)
        {
            return StatusCode(500, e.Message);
        }
      
    }
}
