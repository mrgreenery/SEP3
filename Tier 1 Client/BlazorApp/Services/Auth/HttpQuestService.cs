using System.Text.Json;
using ApiContracts;
using ApiContracts.Quest;

namespace BlazorApp.Services.Auth;

public class HttpQuestService : IQuestService
{
    
    private readonly HttpClient client;
    
    public HttpQuestService (HttpClient client)
    { 
        this.client = client;
    }
    //todo Change the CreateQuestResponse to QuestDTO
    
    public async Task<QuestDto> CreateQuestAsync(CreateQuestRequest request)
    {
        var respond = await client.PostAsJsonAsync("quests",  request);
        
        respond.EnsureSuccessStatusCode();
        
        return await respond.Content.ReadFromJsonAsync<QuestDto>(
                   new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
               ?? throw new Exception("Could not create quest");

    }
    
    // public async Task<QuestDto> GetQuestAsync(int id)
    // {
    //     var respond = await client.GetAsync($"quests/{id}");
    //     
    //     respond.EnsureSuccessStatusCode();
    //     
    //     return await respond.Content.ReadFromJsonAsync<QuestDto>(
    //                new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
    //            ?? throw new Exception("Could not get quest");
    // }
    //
    
    public Task UpdateQuestAsync(int id, UserDto request)
    {
        throw new NotImplementedException();
    }

    public Task DeleteQuestAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task<List<QuestDto>> GetAllQuestsAsync(string? nameContains = null)
    {
        throw new NotImplementedException();
    }
}