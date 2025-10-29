using WebAPI.ApiContracts;

namespace CLI.Views.Quest;
using System.Threading.Quests;

public class QuestView(QuestViewModel questViewModel)
{
    public async Quest CreateQuest()
    {
        Console.WriteLine("--- Quest Creation ---");
        
        Console.WriteLine("Provide Quest title: ");
        var questTitle = Console.ReadLine();
        
        Console.WriteLine("Provide quest description: ");
        var questDescription = Console.ReadLine();

        CreateQuestResponse response = await questViewModel.CreateQuest(questTitle, questDescription);
        
        Console.WriteLine($"Quest created successfully:\nid: {response.Id},created at: {response.CreatedAt}, \ntitle:{response.Title},\ndescription: {response.Description}");

    }
}