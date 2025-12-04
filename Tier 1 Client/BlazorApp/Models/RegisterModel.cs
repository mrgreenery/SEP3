using System.ComponentModel.DataAnnotations;

namespace BlazorApp.Models;

public class RegisterModel : IValidatableObject
{
    [Required, EmailAddress]
    public string Email { get; set; }

    [Required, MinLength(8)]
    public string Password { get; set; }

    [Required]
    public string FullName { get; set; }
    
    [Required]
    public string RepeatPassword { get; set; }
    
    public IEnumerable<ValidationResult> Validate(ValidationContext context)
    {
        if (!string.IsNullOrEmpty(Password) && !string.IsNullOrEmpty(RepeatPassword))
        {
            if (Password != RepeatPassword)
            {
                yield return new ValidationResult(
                    "Passwords do not match",
                    new[] { nameof(RepeatPassword) }
                );
            }
        }
    }
}