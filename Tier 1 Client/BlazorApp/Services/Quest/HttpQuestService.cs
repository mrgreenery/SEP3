using System.Text.Json;
using ApiContracts.Quest;
using BlazorApp.Services.Auth;

namespace BlazorApp.Services.Quest;

public class HttpQuestService : IQuestService
{
    
    private readonly HttpClient client;
    
    public HttpQuestService (HttpClient client)
    { 
        this.client = client;
    }
    
    public async Task<QuestDto> CreateQuestAsync(string title, 
        string? description, QuestStatus status, long createdById, 
        long? assigneeId, DateTime? startDate, DateTime? deadline, 
        DateTime? finishedDate)
    {
        // Build a request body
        CreateQuestRequest request = new CreateQuestRequest
        {
            Title = title,
            Description = description,
            Status = status,
            CreatedById = createdById,
            AssigneeId = assigneeId,
            StartDate = startDate,
            Deadline = deadline,
            FinishedDate = finishedDate
        };
        
        // Send a request and receive a response message
        var respond = await client.PostAsJsonAsync(
            "api/quests",  request);
        
        respond.EnsureSuccessStatusCode();
        
        //Change response message into a QuestDto and return it
        return await respond.Content.ReadFromJsonAsync<QuestDto>(
                   new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
               ?? throw new Exception("Could not create quest");
    }
    
    public async Task UpdateQuestAsync(int id, QuestDto questDto)
    {
        throw new NotImplementedException();
    }

    public async Task DeleteQuestAsync(int id)
    {
        throw new NotImplementedException();
    }

    public async Task<List<QuestDto>> GetAllQuestsAsync()
    {
        throw new NotImplementedException();
    }
}