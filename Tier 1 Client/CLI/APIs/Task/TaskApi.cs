using System.Net.Http.Json;

namespace WebAPI.ApiContracts;

public class TaskApi(HttpClient client) : ITaskApi
{
    public async Task<CreateTaskResponse> CreateTask(CreateTaskRequest request, CancellationToken ct = default)
    {
        using var response =
            await client.PostAsJsonAsync("api/tasks", request, ct);
        if (response.IsSuccessStatusCode)
        {
            return await response.Content.ReadFromJsonAsync<CreateTaskResponse>(
                cancellationToken: ct);
        }

        throw new HttpRequestException($"CreateTask failed: {(int)response.StatusCode}");
    }
}