using ApiContracts.Quest;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;
using WebAPI.Hubs;
using WebAPI.Services;
using WebAPI.Services.Exceptions.User;

namespace WebAPI.Controllers;

[ApiController]
[Route("api/[controller]")]
public class QuestsController : ControllerBase
{
    private readonly IQuestService _questService;
    private readonly ILogger<QuestsController> _logger;
 	private readonly IHubContext<QuestHub> _hubContext;

    public QuestsController(
        IQuestService questService, 
        ILogger<QuestsController> logger, 
        IHubContext<QuestHub> hubContext)
    {
        _questService = questService;
        _logger = logger;
		_hubContext = hubContext;
    }

    //  CREATE QUEST 
    // POST /api/quests
    [HttpPost]
    public async Task<IResult> CreateQuest([FromBody] CreateQuestRequest request)
    {
            _logger.LogInformation("Creating quest: {Title}", request.Title);

         if (string.IsNullOrWhiteSpace(request.Title))
        {
            _logger.LogWarning("Create quest validation failed: title is empty");
            return Results.BadRequest("Title is required."); // 400
        }

        try
        {
            var created = await _questService.CreateQuestAsync(request);
            
            // SIGNALR broadcast
            await _hubContext.Clients.All.SendAsync("QuestCreated", created);
            _logger.LogInformation("SignalR broadcast: QuestCreated {Id}", created.Id);
            
            // 201 Created with Location header
            return Results.Created($"/api/quests/{created.Id}", created);
        }
        catch (ArgumentException ex)
        {
            _logger.LogWarning(ex, "Create quest validation error");
            return Results.BadRequest(ex.Message); // 400
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Create quest failed unexpectedly");
            return Results.Problem(
                title: "Failed to create quest",
                statusCode: StatusCodes.Status500InternalServerError
            ); // 500 only for unexpected stuff
        }
    }

    //  GET ALL QUESTS 
    // GET /api/quests
    [HttpGet]
    public async Task<IResult> GetAllQuests()
    {
        
       _logger.LogInformation("Get all quests requested");

        try
        {
            var quests = await _questService.GetAllQuestsAsync();
            return Results.Ok(quests); // 200
        }
        catch (InvalidDataException ex)
        {
            // service says "no users"
            _logger.LogWarning(ex, "No Quests found");
            return Results.NotFound("No quest found."); // 404
            
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Get all quests failed unexpectedly");
            return Results.Problem(
                title: "Failed to fetch quests",
                statusCode: StatusCodes.Status500InternalServerError
            ); // 500 only for unexpected stuff
        }
    }


    //  UPDATE QUEST 
    // PUT /api/quests/{id}
    // Request: QuestDto (overwrite)
    // Response: 202 Accepted
    [HttpPut("{id:long}")]
    public async Task<IResult> UpdateQuest(long id, [FromBody] QuestDto quest)
    {
         _logger.LogInformation("Update quest requested. Id={Id}", id);

        if (id <= 0)
        {
            _logger.LogWarning("Update quest validation failed: invalid id {Id}", id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }

        if (quest is null)
        {
            _logger.LogWarning("Update quest validation failed: body is null. Id={Id}", id);
            return Results.BadRequest("Quest payload is required."); // 400
        }

        try
        {
            await _questService.UpdateQuestAsync(id, quest);
            
            //signalR broadcast
            await _hubContext.Clients.All.SendAsync("QuestUpdated", quest);
            _logger.LogInformation("SignalR broadcast: QuestUpdated {Id}", id);
            
            return Results.StatusCode(StatusCodes.Status202Accepted); // 202
        }
        catch(QuestWithThisIdDoesNotExist)
        {
            _logger.LogWarning("Update quest failed, quest not found", id);
            return Results.NotFound($"Quest with id {id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Update quest failed unexpectedly. Id={Id}", id);
            return Results.Problem(
                title: "Failed to update quest",
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
         _logger.LogInformation("Delete quest requested. Id={Id}", id);

        if (id <= 0)
        {
            _logger.LogWarning("Delete quest validation failed: invalid id {Id}", id);
            return Results.BadRequest("Id must be greater than zero."); // 400
        }

        try
        {
            await _questService.DeleteQuestAsync(id);
            
            //singR broadcast
            await _hubContext.Clients.All.SendAsync("QuestDeleted", id);
            _logger.LogInformation("SignalR broadcast: QuestDeleted {Id}", id);
            
            return Results.NoContent(); // 204
        }
        catch(QuestWithThisIdDoesNotExist)
        {
            _logger.LogWarning("Delete quest failed, quest not found", id);
            return Results.NotFound($"Quest with id {id} not found."); // 404 not found
        }
        catch (Exception e)
        {
            _logger.LogError(e, "Delete quest failed unexpectedly. Id={Id}", id);
            return Results.Problem(
                title: "Failed to delete quest",
                statusCode: StatusCodes.Status500InternalServerError
            );
        }
    }
}
