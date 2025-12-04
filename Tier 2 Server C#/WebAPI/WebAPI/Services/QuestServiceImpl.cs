using ApiContracts.Quest;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using WebAPI.Services.Exceptions.User;
using WebAPI.Services.Util;
using CreateQuestRequest = ApiContracts.Quest.CreateQuestRequest;
using Enum = System.Enum;
using grpcQuestStatus = Data.QuestStatus;
using apiQuestStatus = ApiContracts.Quest.QuestStatus;

namespace WebAPI.Services;

public class QuestServiceImpl : IQuestService
{
    private readonly QuestService.QuestServiceClient _grpcClient;

    public QuestServiceImpl(QuestService.QuestServiceClient grpcClient)
    {
        _grpcClient = grpcClient;
    }

    //  CREATE 

    public async Task<QuestDto> CreateQuestAsync(CreateQuestRequest request)
    {
        if (string.IsNullOrWhiteSpace(request.Title))
            throw new ArgumentException("Title is required", nameof(request.Title));

        var status = request.Status == default
            ? apiQuestStatus.Backlog
            : request.Status;

        var grpcRequest = new Data.CreateQuestRequest
        {
            Title = request.Title,
            Status = Enum.Parse<grpcQuestStatus>(status.ToString()),
            CreatedBy = request.CreatedById
        };

        // I added the conditions so when we have null fields, they are not sent to gRPC

        if (!string.IsNullOrWhiteSpace(request.Description))
            grpcRequest.Description = request.Description;

        if (request.AssigneeId.HasValue)
            grpcRequest.AssigneeId = request.AssigneeId.Value;

        if (request.StartDate.HasValue)
            grpcRequest.StartDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(request.StartDate.Value, DateTimeKind.Utc));

        if (request.Deadline.HasValue)
            grpcRequest.Deadline = Timestamp.FromDateTime(
                DateTime.SpecifyKind(request.Deadline.Value, DateTimeKind.Utc));

        if (request.FinishedDate.HasValue)
            grpcRequest.FinishedDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(request.FinishedDate.Value, DateTimeKind.Utc));

        try
        {
            var grpcResponse = await _grpcClient.CreateQuestAsync(grpcRequest);
            return DtoMapper.QuestToDto(grpcResponse);
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC CreateQuest failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }


    //  GET ALL 

    public async Task<List<QuestDto>> GetAllQuestsAsync()
    {
        try
        {
            var grpcResponse = await _grpcClient.GetAllQuestsAsync(new Empty());
            var result = new List<QuestDto>();

            if (grpcResponse.Quests is null || grpcResponse.Quests.Count == 0)
                throw new InvalidDataException("No Users Found");
            
            foreach (var quest in grpcResponse.Quests)
            {
                result.Add(DtoMapper.QuestToDto(quest));
            }

            return result;
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC GetAllQuests failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    //  UPDATE 

    public async Task UpdateQuestAsync(long id, QuestDto quest)
    {

        var grpcRequest = new Data.UpdateQuestRequest
        {
            QuestId = id,
            Title = quest.Title,
            Status = Enum.Parse<grpcQuestStatus>(quest.Status.ToString())
        };
                
        // I added the conditions so when we have null fields, they are not sent to gRPC
    

        if (!string.IsNullOrWhiteSpace(quest.Description))
            grpcRequest.Description = quest.Description;

        if (quest.Assignee != null)
            grpcRequest.AssigneeId = quest.Assignee.Id;

        if (quest.StartDate.HasValue)
            grpcRequest.StartDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(quest.StartDate.Value, DateTimeKind.Utc));

        if (quest.Deadline.HasValue)
            grpcRequest.Deadline = Timestamp.FromDateTime(
                DateTime.SpecifyKind(quest.Deadline.Value, DateTimeKind.Utc));

        if (quest.FinishedDate.HasValue)
            grpcRequest.FinishedDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(quest.FinishedDate.Value, DateTimeKind.Utc));

        try
        {
            // We don't use the response body, We agreed on 202 Accepted
            await _grpcClient.UpdateQuestAsync(grpcRequest);
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new QuestWithThisIdDoesNotExist("Quest with this id does not exist ",ex);
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC UpdateQuest failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }

    //  DELETE 

    public async Task DeleteQuestAsync(long id)
    {
        try
        {
            await _grpcClient.DeleteQuestAsync(new IdRequest { Id = id });
        }
        catch (RpcException ex) when (ex.StatusCode == StatusCode.NotFound)
        {
            throw new QuestWithThisIdDoesNotExist("Quest with this id does not exist ",ex);
        }
        catch (RpcException ex)
        {
            throw new Exception($"gRPC DeleteQuest failed: {ex.StatusCode} - {ex.Status.Detail}", ex);
        }
    }
}
