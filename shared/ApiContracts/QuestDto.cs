namespace ApiContracts;

//todo Change the CreateQuestResponse to QuestDTO
public record QuestDto
{
    public int Id { get; set; }
    public required string Title { get; set; }
    public required string Description { get; set; }
    public DateTime CreatedAt { get; set; }
}