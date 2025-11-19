using ApiContracts;
using Data;
using ApiCreateQuestRequest = ApiContracts.CreateQuestRequest;
using GrpcCreateQuestRequest = Data.CreateQuestRequest;
using Grpc.Net.Client;
using Google.Protobuf.WellKnownTypes;

namespace WebAPI.Services;

public class QuestServiceImpl : IQuestService
{

    private readonly QuestService.QuestServiceClient _grpcClient;

    public QuestServiceImpl()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");
        _grpcClient = new QuestService.QuestServiceClient(channel);
    }

  public async Task<CreateQuestResponse> CreateQuestAsync(ApiCreateQuestRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));

        // Create proto quest entity
        var questEntity = new QuestEntity
        {
            Title = request.Title,
            Description = request.Description,
            Status = "0"
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