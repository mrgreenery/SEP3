namespace WebApplication1.Controllers;

public class ITaskService
{
    Task<CreateTaskResponse> CreateTaskAsync(CreateTaskRequest request);

}
