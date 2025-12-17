namespace ApiContracts.Quest;

public record CreateQuestRequest
{
    public required string Title { get; set; }
    public string? Description { get; set; }
    public required QuestStatus Status { get; set; }
    public required long CreatedById { get; set; }
    public long? AssigneeId { get; set; }
    public DateTime? Deadline { get; set; }
    public DateTime? StartDate { get; set; }
    public DateTime? FinishedDate { get; set; }
}