using ApiContracts;
using WebAPI.Services;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class QuestsController : ControllerBase
{
    private readonly IQuestService _questService;
    private readonly ILogger<QuestsController> _logger;

    public QuestsController(IQuestService questService, ILogger<QuestsController> logger)
    {
        _questService = questService;
        _logger = logger;
    }
    
    [HttpPost]
    public async Task<ActionResult<CreateQuestResponse>> CreateQuest([FromBody] CreateQuestRequest request)
    {
        try
        {
            _logger.LogInformation("Creating quest: {Title}", request.Title);
            var response = await _questService.CreateQuestAsync(request);
            _logger.LogInformation("Quest created successfully with ID: {Id}", response.Id);
            return Created($"/api/quests/{response.Id}", response);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error creating quest");
            return StatusCode(500, new { error = e.Message, details = e.ToString() });
        }
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<CreateQuestResponse>> GetQuest(int id)
    {
        try
        {
            return Ok(new CreateQuestResponse
            {
                Id = id,
                Title = "Dummy Quest",
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