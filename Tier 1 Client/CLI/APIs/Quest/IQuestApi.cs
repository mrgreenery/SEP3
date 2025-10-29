namespace WebAPI.ApiContracts;

public interface IQuestApi
{
    Task<CreateQuestResponse> CreateQuest(CreateQuestRequest request, CancellationToken ct = default);
}