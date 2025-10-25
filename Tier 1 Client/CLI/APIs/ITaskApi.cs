using CLI.DTO;

namespace CLI.HttpClients;

public interface ITaskApi
{
    Task<CreateTaskResponse> CreateTask(CreateTaskRequest request, CancellationToken ct = default);
}