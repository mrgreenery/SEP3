using ApiContracts.Quest;
using Microsoft.AspNetCore.Mvc;
using WebAPI.Services;

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

    //  CREATE QUEST 
    // POST /api/quests
    [HttpPost]
    public async Task<ActionResult<QuestDto>> CreateQuest([FromBody] CreateQuestRequest request)
    {
        try
        {
            _logger.LogInformation("Creating quest: {Title}", request.Title);

            var created = await _questService.CreateQuestAsync(request);

            return CreatedAtAction(nameof(GetQuestById), new { id = created.Id }, created);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error creating quest");
            return StatusCode(500, new { error = e.Message });
        }
    }

    //  GET ALL QUESTS 
    // GET /api/quests
    [HttpGet]
    public async Task<ActionResult<List<QuestDto>>> GetAllQuests()
    {
        try
        {
            var quests = await _questService.GetAllQuestsAsync();
            return Ok(quests);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error getting all quests");
            return StatusCode(500, new { error = e.Message });
        }
    }


    //  UPDATE QUEST 
    // PUT /api/quests/{id}
    // Request: QuestDto (overwrite)
    // Response: 202 Accepted
    [HttpPut("{id:long}")]
    public async Task<IActionResult> UpdateQuest(long id, [FromBody] QuestDto quest)
    {
        try
        {
            await _questService.UpdateQuestAsync(id, quest);
            return Accepted(); // 202
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error updating quest {Id}", id);
            return StatusCode(500, new { error = e.Message });
        }
    }

    //  DELETE QUEST 
    // DELETE /api/quests/{id}
    // Response: 204 No Content
    [HttpDelete("{id:long}")]
    public async Task<IActionResult> DeleteQuest(long id)
    {
        try
        {
            await _questService.DeleteQuestAsync(id);
            return NoContent(); // 204
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error deleting quest {Id}", id);
            return StatusCode(500, new { error = e.Message });
        }
    }
}
