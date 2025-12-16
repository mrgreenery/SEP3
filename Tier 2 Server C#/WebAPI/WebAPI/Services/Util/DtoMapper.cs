using ApiContracts.Quest;
using ApiContracts.User;
using Data;
using apiQuestStatus = ApiContracts.Quest.QuestStatus;

namespace WebAPI.Services.Util;

public static class DtoMapper
{

    // translates UserEntity object to UserDto
    public static UserDto UserToDto(UserEntity user)
    {
        return new UserDto
        {
            Id = user.Id,
            DisplayName = user.DisplayName,
            Email = user.Email,
        };
    }

    // translates QuestEntity object to QuestDto
    public static QuestDto QuestToDto(QuestEntity quest)
    {
        return new QuestDto
        {
            Id = quest.Id,
            Title = quest.Title,
            Description = quest.Description,
            Status = Enum.Parse<apiQuestStatus>(quest.Status.ToString()),
            CreatedBy = UserToDto(quest.CreatedBy),
            CreatedDate = quest.CreatedDate.ToDateTime(),
            Assignee = quest.Assignee is not null ? UserToDto(quest.Assignee) : null,
            StartDate = quest.StartDate?.ToDateTime(),
            Deadline = quest.Deadline?.ToDateTime(),
            FinishedDate = quest.FinishedDate?.ToDateTime(),
        };
    }
}
