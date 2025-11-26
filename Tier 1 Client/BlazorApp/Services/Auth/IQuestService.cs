using ApiContracts;
using ApiContracts.Quest;

namespace BlazorApp.Services.Auth;

public interface IQuestService
{
    //todo Change the CreateQuestResponse to QuestDTO
    
    public Task<QuestDto> CreateQuestAsync(CreateQuestRequest request);
    public Task UpdateQuestAsync(int id, UserDto request);
    public Task DeleteQuestAsync(int id);
    // public Task<QuestDto> GetQuestAsync(int id);
    public Task<List<QuestDto>> GetAllQuestsAsync(string? nameContains = null);
}
