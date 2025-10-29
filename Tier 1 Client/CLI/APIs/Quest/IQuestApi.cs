namespace WebAPI.ApiContracts;

public interface IQuestApi
{
    Quest<CreateQuestResponse> CreateQuest(CreateQuestRequest request, CancellationToken ct = default);
}