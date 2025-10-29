namespace WebAPI.ApiContracts;

public class CreateQuestRequest(string title, string description)
{
    public required string Title { get; set; } = title;
    public required string Description { get; set; } = description;

}