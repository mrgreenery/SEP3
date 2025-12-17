using ApiContracts.Quest;
using apiQuestStatus = ApiContracts.Quest.QuestStatus;

namespace WebAPI.Services.Interfaces;

public interface IQuestService
{
     Task<QuestDto> CreateQuestAsync(string title, string? description,
         apiQuestStatus status, long createdById, long? assigneeId,
         DateTime? startDate, DateTime? deadline, DateTime? finishedDate);

    Task<List<QuestDto>> GetAllQuestsAsync();

    Task UpdateQuestAsync(long id, QuestDto quest);

    Task DeleteQuestAsync(long id);
}
