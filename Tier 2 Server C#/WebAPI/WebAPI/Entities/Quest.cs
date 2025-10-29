using System;

namespace WebAPI.Entities;

public class Quest
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
