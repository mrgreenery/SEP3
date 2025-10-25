namespace CLI.DTO;

public sealed record CreateTaskResponse(int? Id, string Title, string Description, DateTime? createdAt);