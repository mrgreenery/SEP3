using WebAPI.ApiContracts;
using Data;
using Grpc.Net.Client;
using Google.Protobuf.WellKnownTypes;

namespace WebAPI.Services;

public class TaskService : ITaskService
{
    private readonly DataService.DataServiceClient _grpcClient;

    public TaskService()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");
        _grpcClient = new DataService.DataServiceClient(channel);
    }

  public async Task<CreateTaskResponse> CreateTaskAsync(WebAPI.ApiContracts.CreateTaskRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));

        // Create proto task entity
        var taskEntity = new TaskEntity
        {
            Title = request.Title,
            Description = request.Description,
            Status = 0
        };

        // Create gRPC request
      	var grpcRequest = new Data.CreateTaskRequest
        {
            Task = taskEntity
        };

        // Call gRPC service
        var grpcResponse = await _grpcClient.CreateTaskAsync(grpcRequest);

        // Map to API response
        var response = new CreateTaskResponse
        {
            Id = (int)grpcResponse.Id,
            Title = grpcResponse.Title,
            Description = grpcResponse.Description,
            CreatedAt = DateTime.UtcNow
        };

        return response;
    }
}