namespace WebAPI.ApiContracts;

public class CreateTaskRequest(string title, string description)
{
    public required string Title { get; set; } = title;
    public required string Description { get; set; } = description;

}