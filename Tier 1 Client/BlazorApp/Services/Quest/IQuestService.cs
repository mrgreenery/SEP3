using ApiContracts;
using ApiContracts.Quest;

namespace BlazorApp.Services.Auth;

public interface IQuestService
{
    //todo Change the CreateQuestResponse to QuestDTO
    
    public Task<QuestDto> CreateQuestAsync(string title, 
        string? description, QuestStatus status, long createdById, 
        long? assigneeId, DateTime? startDate, DateTime? deadline, 
        DateTime? finishedDate);
    public Task UpdateQuestAsync(int id, QuestDto questDto);
    public Task DeleteQuestAsync(int id);
    public Task<List<QuestDto>> GetAllQuestsAsync();
}
