using WebAPI.ApiContracts; 

namespace WebAPI.Services;

public interface ITaskService
{
    Task<CreateTaskResponse> CreateTaskAsync(CreateTaskRequest request);
}
