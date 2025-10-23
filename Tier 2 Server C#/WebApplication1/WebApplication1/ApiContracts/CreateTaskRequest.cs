namespace WebApplication1.Controllers;

public class CreateTaskRequest
{
    public string Title { get; set; }
    public string Description { get; set; }
    public long CreatedByUserId { get; set; }
}