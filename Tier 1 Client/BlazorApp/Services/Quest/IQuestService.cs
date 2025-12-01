using ApiContracts;
using ApiContracts.Quest;

namespace BlazorApp.Services.Auth;

public interface IQuestService
{
    public Task<QuestDto> CreateQuestAsync(string title, 
        string? description, QuestStatus status, long createdById, 
        long? assigneeId, DateTime? startDate, DateTime? deadline, 
        DateTime? finishedDate);
    public Task UpdateQuestAsync(long id, QuestDto questDto);
    public Task DeleteQuestAsync(long id);
    public Task<List<QuestDto>> GetAllQuestsAsync();
}
