using System;

namespace WebApplication1.Entities;

public class Task
{
    private long Id{ get; set; }
    private string? Title{ get; set; }
    private string? Description{ get; set; }
    private string? Status { get; set; }
    private DateTime CreatedAt { get; set; }
    private User? CreatedBy { get; set; }
    private ICollection<User>? Assignees { get; set; }
    private DateTime? StartDate { get; set; }
    private DateTime? EndDate { get; set; }

}
