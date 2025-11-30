using System;
using ApiContracts.Quest;

namespace WebAPI.Entities;

public class Quest
{
    public long Id{ get; set; }
    public string? Title{ get; set; }
    public string? Description{ get; set; }
    public QuestStatus Status { get; set; }
    public DateTime CreatedDate { get; set; }
    public User CreatedBy { get; set; }
    public User? Assignee { get; set; }
    public DateTime? StartDate { get; set; }
    public DateTime? Deadline { get; set; }

}
