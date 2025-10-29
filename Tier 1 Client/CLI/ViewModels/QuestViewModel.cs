namespace WebAPI.ApiContracts;

public class QuestViewModel(IQuestApi questApi)
{
    public async Quest<CreateQuestResponse> CreateQuest(string title,
        string description)
    {
        CreateQuestRequest request = new CreateQuestRequest(title, description)
        {
            Title = title,
            Description = description
        };
        return await questApi.CreateQuest(request);
    }
}