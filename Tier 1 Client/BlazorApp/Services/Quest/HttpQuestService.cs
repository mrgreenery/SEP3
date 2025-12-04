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
    
    public async Task<QuestDto> CreateQuestAsync(CreateQuestRequest request)
    {
        var respond = await client.PostAsJsonAsync("api/quests", request);
        respond.EnsureSuccessStatusCode();
    
        return await respond.Content.ReadFromJsonAsync<QuestDto>(
                   new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
               ?? throw new Exception("Could not create quest");
    }

    public async Task UpdateQuestAsync(long id, QuestDto questDto)
    {
        // Calling the Tier 2: PUT /api/quests/{id}
        var response = await client.PutAsJsonAsync($"api/quests/{id}", questDto);

        // Tier 2 returns 202 on success,  anything else by the logic should be an error
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to update quest. Status: {response.StatusCode}");

    }

    public async Task DeleteQuestAsync(long id)
    {
        // call Web API endpoint to delete quest
        var response = await client.DeleteAsync($"api/quests/{id}");

        // Tier 2 returns 204 on success
        if (!response.IsSuccessStatusCode)
            throw new Exception($"Failed to delete quest. Status: {response.StatusCode}");
    }

    public async Task<List<QuestDto>> GetAllQuestsAsync()
    {
        //call API endpoint
        var response = await client.GetAsync("api/quests");
        
        response.EnsureSuccessStatusCode();
        
        //deserialize response to list
        return await response.Content.ReadFromJsonAsync<List<QuestDto>>(
            new JsonSerializerOptions{PropertyNameCaseInsensitive = true})
            ?? throw new Exception("Could not get quests");

    }
}