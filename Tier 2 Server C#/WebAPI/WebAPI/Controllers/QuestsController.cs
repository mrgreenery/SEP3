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
    public async Task<IResult> CreateQuest([FromBody] CreateQuestRequest request)
    {
        try
        {
            _logger.LogInformation("Creating quest: {Title}", request.Title);

            var created = await _questService.CreateQuestAsync(request);

            return Results.Created($"/user/{created.Id}", created);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error creating quest");
            return Results.Problem(
                title: "Failed to create a Quest",
                statusCode: StatusCodes.Status500InternalServerError); // 500 internal server error
        }
    }

    //  GET ALL QUESTS 
    // GET /api/quests
    [HttpGet]
    public async Task<IResult> GetAllQuests()
    {
        try
        {
            var quests = await _questService.GetAllQuestsAsync();
            return Results.Ok(quests);
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error getting all quests");
            return Results.Problem(
                title: "Failed to get quests",
                statusCode: StatusCodes.Status500InternalServerError); // 500 internal server error
        }
    }


    //  UPDATE QUEST 
    // PUT /api/quests/{id}
    // Request: QuestDto (overwrite)
    // Response: 202 Accepted
    [HttpPut("{id:long}")]
    public async Task<IResult> UpdateQuest(long id, [FromBody] QuestDto quest)
    {
        try
        {
            await _questService.UpdateQuestAsync(id, quest);
            return Results.Accepted(); // 202
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error updating quest {Id}", id);
            return Results.Problem(
                title: "Internal Server Error",
                detail: e.Message,
                statusCode: StatusCodes.Status500InternalServerError
            );
        }
    }

    //  DELETE QUEST 
    // DELETE /api/quests/{id}
    // Response: 204 No Content
    [HttpDelete("{id:long}")]
    public async Task<IResult> DeleteQuest(long id)
    {
        try
        {
            await _questService.DeleteQuestAsync(id);
            return Results.NoContent(); // 204
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Error deleting quest {Id}", id);
            return Results.Problem(
                title: "Internal Server Error",
                detail: e.Message,
                statusCode: StatusCodes.Status500InternalServerError
            );
        }
    }
}
