using ApiContracts.Quest;

namespace WebAPI.Services;

public interface IQuestService
{
    Task<QuestDto> CreateQuestAsync(CreateQuestRequest request);
    
    //TODO: add more methods   
}
