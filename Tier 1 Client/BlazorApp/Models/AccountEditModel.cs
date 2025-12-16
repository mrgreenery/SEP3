using System.ComponentModel.DataAnnotations;

namespace BlazorApp.Models;

public class AccountEditModel
{
    [Required] public string DisplayName { get; set; } = "";
    [Required, EmailAddress] public string Email { get; set; } = "";
    public string? CurrentPassword { get; set; }
    public string? NewPassword { get; set; }
    public string? ConfirmNewPassword { get; set; }
}