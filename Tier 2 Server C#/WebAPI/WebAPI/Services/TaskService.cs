using WebAPI.ApiContracts; 

namespace WebAPI.Services;

public class TaskService : ITaskService
{
   public Task<CreateTaskResponse> CreateTaskAsync(CreateTaskRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));

        //TODO: gRPC-call to Tier 3 
       
        //temporary implementation. Delete this after grcp is implemented
        var response = new CreateTaskResponse
        {
            Id = 1, 
            Title = request.Title,
            Description = request.Description,
            CreatedAt = DateTime.UtcNow
        };

        return Task.FromResult(response);
    }
}