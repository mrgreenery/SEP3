using WebAPI.ApiContracts;
using WebAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class TasksController : ControllerBase
{
    private readonly ITaskService _taskService;
    private readonly ILogger<TasksController> _logger;

    public TasksController(ITaskService taskService, ILogger<TasksController> logger)
    {
        _taskService = taskService;
        _logger = logger;
    }
    
    [HttpPost]
    public async Task<ActionResult<CreateTaskResponse>> CreateTask([FromBody] CreateTaskRequest request)
    {
        try
        {
            _logger.LogInformation("Creating task: {Title}", request.Title);
            var response = await _taskService.CreateTaskAsync(request);
            _logger.LogInformation("Task created successfully with ID: {Id}", response.Id);
            return Created($"/api/tasks/{response.Id}", response);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error creating task");
            return StatusCode(500, new { error = e.Message, details = e.ToString() });
        }
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<CreateTaskResponse>> GetTask(int id)
    {
        try
        {
            return Ok(new CreateTaskResponse
            {
                Id = id,
                Title = "Dummy Task",
                Description = "Dummy Description",
                CreatedAt = DateTime.UtcNow
            });
        }
        catch (Exception e)
        {
            return StatusCode(500, e.Message);
        }
    }
}