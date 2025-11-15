namespace WebAPI.ApiContracts;
public class CreateQuestResponse
{
    public int Id { get; set; }
    public required string Title { get; set; }
    public required string Description { get; set; }
    public DateTime CreatedAt { get; set; }
}