using ApiContracts.Quest;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Net.Client;
using CreateQuestRequest = ApiContracts.Quest.CreateQuestRequest;
using Enum = System.Enum;
using grpcQuestStatus = Data.QuestStatus;
using apiQuestStatus =  ApiContracts.Quest.QuestStatus;
    
namespace WebAPI.Services;

public class QuestServiceImpl : IQuestService
{

    private readonly QuestService.QuestServiceClient _grpcClient;

    public QuestServiceImpl()
    {
        var channel = GrpcChannel.ForAddress("http://localhost:9090");
        _grpcClient = new QuestService.QuestServiceClient(channel);
    }

  public async Task<QuestDto> CreateQuestAsync(CreateQuestRequest request)
    {
        if (request is null) throw new ArgumentNullException(nameof(request));
        if(request.Title is null) throw new ArgumentNullException(nameof(request.Title));
        if(request.CreatedById == 0) throw new ArgumentNullException(nameof(request.CreatedById));
        

        // Create gRPC request
      	var grpcRequest = new Data.CreateQuestRequest()
        {
            Title = request.Title,
            Description = request.Description ?? string.Empty ,
            Status =  Enum.Parse<grpcQuestStatus>(request.Status.ToString()),
            CreatedBy = request.CreatedById,
            AssigneeId = request.AssigneeId ?? 0,
            StartDate = request.StartDate.HasValue ? Timestamp.FromDateTime(DateTime.SpecifyKind(request.StartDate.Value, DateTimeKind.Utc)) : null,
            Deadline= request.Deadline.HasValue ? Timestamp.FromDateTime(DateTime.SpecifyKind(request.Deadline.Value, DateTimeKind.Utc)) : null, 
            FinishedDate = request.FinishedDate.HasValue ? Timestamp.FromDateTime(DateTime.SpecifyKind(request.FinishedDate.Value, DateTimeKind.Utc)) : null ,
        };

        // Call gRPC service
        var grpcResponse = await _grpcClient.CreateQuestAsync(grpcRequest);

        // Map to API response
        var response = ToDto(grpcResponse);

        return response;
    }

    private static QuestDto ToDto(QuestEntity quest)
    {
        return new QuestDto()
        {
            Id = quest.Id,
            Title = quest.Title,
            Description = quest.Description,
            Status = Enum.Parse<apiQuestStatus>(quest.Status.ToString()),
            CreatedBy = UserServiceImpl.ToDto(quest.CreatedBy),
            CreatedDate = quest.CreatedDate.ToDateTime(),
            Assignee = quest.Assignee is not null ? UserServiceImpl.ToDto(quest.Assignee) : null,
            StartDate =  quest.StartDate?.ToDateTime(),
            Deadline = quest.Deadline?.ToDateTime(),
            FinishedDate = quest.FinishedDate?.ToDateTime(),
        };
    }
  
  
}