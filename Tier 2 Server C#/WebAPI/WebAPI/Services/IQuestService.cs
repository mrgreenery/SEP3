using WebAPI.ApiContracts; 

namespace WebAPI.Services;

public interface IQuestService
{
    Task<CreateQuestResponse> CreateQuestAsync(CreateQuestRequest request);
}
