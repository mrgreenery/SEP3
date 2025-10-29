namespace WebAPI.ApiContracts;

public class CreateQuestRequest
{
    public required string Title { get; set; }
    public required string Description { get; set; }
    
}