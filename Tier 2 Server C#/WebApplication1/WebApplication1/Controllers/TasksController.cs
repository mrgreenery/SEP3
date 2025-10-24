using WebApplication1.ApiContracts;
using Microsoft.AspNetCore.Mvc;

namespace WebApplication1.Controllers;

[ApiController]
[Route("api/[controller]")]
public class TasksController : ControllerBase
{

    //need some sort of service here, like private readonly ITaskService _taskService;
    //and then make constructor to instantiate this service
    
    [HttpPost]
    public async Task<ActionResult<CreateTaskResponse>> CreateTask([FromBody] CreateTaskRequest request)
    {
        try
        {
            //and then here to pass this service to the response
            CreateTaskResponse response = new CreateTaskResponse
            {
                Id = 1,
                Title = request.Title,
                Description = request.Description,
                CreatedAt = DateTime.UtcNow
            };

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