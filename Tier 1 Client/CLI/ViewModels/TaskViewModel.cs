namespace WebAPI.ApiContracts;

public class TaskViewModel(ITaskApi taskApi)
{
    public async Task<CreateTaskResponse> CreateTask(string title,
        string description)
    {
        CreateTaskRequest request = new CreateTaskRequest(title, description)
        {
            Title = title,
            Description = description
        };
        return await taskApi.CreateTask(request);
    }
}