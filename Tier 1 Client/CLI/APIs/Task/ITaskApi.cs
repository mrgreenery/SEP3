namespace WebAPI.ApiContracts;

public interface ITaskApi
{
    Task<CreateTaskResponse> CreateTask(CreateTaskRequest request, CancellationToken ct = default);
}