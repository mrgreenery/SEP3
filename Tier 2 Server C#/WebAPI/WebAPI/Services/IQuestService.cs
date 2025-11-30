using ApiContracts.Quest;

namespace WebAPI.Services;

public interface IQuestService
{
     Task<QuestDto> CreateQuestAsync(CreateQuestRequest request);

    Task<QuestDto?> GetQuestByIdAsync(long id);

    Task<List<QuestDto>> GetAllQuestsAsync();

    Task UpdateQuestAsync(long id, QuestDto quest);

    Task DeleteQuestAsync(long id);
}
