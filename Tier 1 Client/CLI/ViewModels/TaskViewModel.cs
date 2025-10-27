namespace WebAPI.ApiContracts;

public class TaskViewModel
{
    public async Task<KanbanTaskDTO> CreateTask(string title,
        string description)
    {
        CreateTaskRequest request = new CreateTaskRequest(title, description);
        CreateTaskResponse response =
            taskApi.CreateTask(request).Result;
        return new KanbanTaskDTO(response.Id, response.Title, response.Description, response.createdAt);
    }
}