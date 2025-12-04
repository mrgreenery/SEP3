using ApiContracts;
using ApiContracts.Quest;

namespace BlazorApp.Services.Auth;

public interface IQuestService
{
    public Task<QuestDto> CreateQuestAsync(CreateQuestRequest request);
    public Task UpdateQuestAsync(long id, QuestDto questDto);
    public Task DeleteQuestAsync(long id);
    public Task<List<QuestDto>> GetAllQuestsAsync();
}
