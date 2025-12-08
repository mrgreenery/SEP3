namespace BlazorApp.Models;

public class QuestModel
{
    public string Title { get; set; } = "";
    public string Description { get; set; } = "";
    public DateTime? Deadline { get; set; }
}