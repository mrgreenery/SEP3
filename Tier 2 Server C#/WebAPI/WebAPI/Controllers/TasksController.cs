using WebAPI.ApiContracts;
using WebAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class TasksController : ControllerBase
{

    private readonly ITaskService _taskService;

    public TasksController(ITaskService taskService)
    {
        _taskService = taskService;
    }
    
    [HttpPost]
    public async Task<ActionResult<CreateTaskResponse>> CreateTask([FromBody] CreateTaskRequest request)
    {
        try
        {
           var response = await _taskService.CreateTaskAsync(request);
            return Created($"/api/tasks/{response.Id}", response);
        }
        catch (Exception e)
        {
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<CreateTaskResponse>> GetTask(int id)
    {
        try
        {
            //and here as well pass this servive to the response
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