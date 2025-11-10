using WebAPI.ApiContracts;
using Data;
using Grpc.Net.Client;
using Google.Protobuf.WellKnownTypes;

namespace WebAPI.Services;

public class QuestService : IQuestService
{

    // Alex : Update this with another implementation (instead of DataService to be QuestService and then update the Service name)
    private readonly DataService.DataServiceClient _grpcClient;

    public QuestService()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");
        _grpcClient = new DataService.DataServiceClient(channel);
    }

  public async Task<CreateQuestResponse> CreateQuestAsync(WebAPI.ApiContracts.CreateQuestRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));

        // Create proto quest entity
        var questEntity = new QuestEntity
        {
            Title = request.Title,
            Description = request.Description,
            Status = 0
        };

        // Create gRPC request
      	var grpcRequest = new Data.CreateQuestRequest
        {
            Quest = questEntity
        };

        // Call gRPC service
        var grpcResponse = await _grpcClient.CreateQuestAsync(grpcRequest);

        // Map to API response
        var response = new CreateQuestResponse
        {
            Id = (int)grpcResponse.Id,
            Title = grpcResponse.Title,
            Description = grpcResponse.Description,
            CreatedAt = DateTime.UtcNow
        };

        return response;
    }
}