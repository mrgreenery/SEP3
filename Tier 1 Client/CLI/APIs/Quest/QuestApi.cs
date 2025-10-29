using System.Net.Http.Json;

namespace WebAPI.ApiContracts;

public class QuestApi(HttpClient client) : IQuestApi
{
    public async Task<CreateQuestResponse> CreateQuest(CreateQuestRequest request, CancellationToken ct = default)
    {
        using var response =
            await client.PostAsJsonAsync("api/quests", request, ct);
        if (response.IsSuccessStatusCode)
        {
            return await response.Content.ReadFromJsonAsync<CreateQuestResponse>(
                cancellationToken: ct);
        }

        throw new HttpRequestException($"CreateQuest failed: {(int)response.StatusCode}");
    }
}