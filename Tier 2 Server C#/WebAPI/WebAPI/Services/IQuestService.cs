using ApiContracts;

namespace WebAPI.Services;

public interface IQuestService
{
    Task<QuestDto> CreateQuestAsync(CreateQuestRequest request);
}
