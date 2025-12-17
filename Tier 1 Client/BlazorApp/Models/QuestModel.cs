using System.ComponentModel.DataAnnotations;
using ApiContracts.Quest;
using ApiContracts.User;

namespace BlazorApp.Models;

public class QuestModel : IValidatableObject
{
    [Required][Editable(false)]
    [Display(Name = "Quest ID")]
    public long Id { get; set; }
    
    [Required(ErrorMessage = "Quest title cannot be empty")] 
    [Display(Name = "Title")]
    public string Title { get; set; } = "";
    
    [Display(Name = "Description")]
    [DataType(DataType.MultilineText)]
    public string? Description { get; set; } = "";
    
    [Display(Name = "Status")]
    public QuestStatus Status { get; set; } = QuestStatus.Backlog;

    [Required][Display(Name = "Created at:")] [Editable(false)]
    public DateTime CreatedDate { get; set; }

    [Required][Display(Name = "Created by:")][Editable(false)]
    public UserDto CreatedById { get; set; }
    
    [Display(Name = "Assignee")]
    public UserDto? Assignee { get; set; }
    
    [Display(Name = "Deadline")]
    public DateTime? Deadline {get; set; }
    
    [Display(Name = "Started Date")]
    public DateTime? StartDate { get; set; }
    
    [Display(Name = "Finished Date")]
    public DateTime? FinishedDate { get; set; }

    public IEnumerable<ValidationResult> Validate(ValidationContext validationContext)
    {
        //Deadline cannot be set in the past
        if (Deadline != null && Deadline.Value < DateTime.Now)
        {
            yield return new ValidationResult(
                "Deadline cannot be set in the past",
                new[] { nameof(Deadline) });
        }
        
        //StartDate required when Status is NOT Backlog or ToDo
        if (Status == QuestStatus.Backlog &&
            Status == QuestStatus.ToDo &&
            StartDate is not null)
        {
            yield return new ValidationResult(
                "Start Date cannot be set when quest status is: Backlog or ToDo",
                new[] { nameof(StartDate) });
        }

        if (Status != QuestStatus.Finished && FinishedDate is not null)
        {
            yield return new ValidationResult(
                "Finished Date cannot be set if Status is not Finished",
                new[] { nameof(FinishedDate) });
        }

        //FinishedDate cannot be filled if StartDate is missing
        if (FinishedDate is not null && StartDate is null)
        {
            yield return new ValidationResult(
                "Finished Date cannot be set without a Started Date.",
                new[] { nameof(FinishedDate), nameof(StartDate) });
        }
        
        //Start Date is after Finished Date
        if (StartDate is not null && FinishedDate is not null &&
            FinishedDate < StartDate)
        {
            yield return new ValidationResult(
                "Finished date cannot be earlier than start date.",
                new[] { nameof(FinishedDate) });
        }
    }
}