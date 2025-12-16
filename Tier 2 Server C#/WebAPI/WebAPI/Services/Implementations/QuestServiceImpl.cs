using ApiContracts.Quest;
using Data;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using WebAPI.Services.Exceptions.User;
using WebAPI.Services.Interfaces;
using WebAPI.Services.Util;
using Enum = System.Enum;
using grpcQuestStatus = Data.QuestStatus;
using apiQuestStatus = ApiContracts.Quest.QuestStatus;

namespace WebAPI.Services.Implementations;

public class QuestServiceImpl : IQuestService
{
    private readonly QuestService.QuestServiceClient _grpcClient;

    public QuestServiceImpl(QuestService.QuestServiceClient grpcClient)
    {
        _grpcClient = grpcClient;
    }

    //  CREATE 
    public async Task<QuestDto> CreateQuestAsync(string title, 
        string? description, apiQuestStatus apiStatus, long createdById, 
        long? assigneeId, DateTime? startDate, DateTime? deadline, 
        DateTime? finishedDate)
    {
        if (string.IsNullOrWhiteSpace(title))
            throw new ArgumentException("Title is required", nameof(title));
        
        var status = apiStatus == default
            ? apiQuestStatus.Backlog
            : apiStatus;

        var grpcRequest = new Data.CreateQuestRequest
        {
            Title = title,
            Status = Enum.Parse<grpcQuestStatus>(status.ToString()),
            CreatedBy = createdById
        };

        if (!string.IsNullOrWhiteSpace(description))
            grpcRequest.Description = description;

        if (assigneeId.HasValue)
            grpcRequest.AssigneeId = assigneeId.Value;

        if (startDate.HasValue)
            grpcRequest.StartDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(startDate.Value, DateTimeKind.Utc));

        if (deadline.HasValue)
            grpcRequest.Deadline = Timestamp.FromDateTime(
                DateTime.SpecifyKind(deadline.Value, DateTimeKind.Utc));

        if (finishedDate.HasValue)
            grpcRequest.FinishedDate = Timestamp.FromDateTime(
                DateTime.SpecifyKind(finishedDate.Value, DateTimeKind.Utc));

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
        var grpcRequest = new UpdateQuestRequest
        {
            QuestId = id,
            Title = quest.Title,
            Status = Enum.Parse<grpcQuestStatus>(quest.Status.ToString())
        };
        
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
