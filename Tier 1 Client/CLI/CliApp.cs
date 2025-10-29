using CLI.Views.Task;
using WebAPI.ApiContracts;

namespace CLI;

public class CliApp
{
    private static readonly HttpClient Client =new HttpClient
    {
        BaseAddress = new Uri("http://localhost:5268")
    };
    private static readonly IQuestApi QuestApi = new QuestApi(Client);
    private static readonly QuestViewModel QuestViewModel = new(QuestApi);
    private static readonly QuestView QuestView = new(QuestViewModel);
    
    public void Start()
    {
        GetClient().GetAwaiter().GetResult();
        
        while (true)
        {
            Console.WriteLine("Welcome to SEP3 Team 1 Proof of Concept!");
            Console.WriteLine("1. Create quest");
            Console.Write("Choose an option and press Enter: ");
            
            var choice = Console.ReadLine();
            switch (choice)
            {
                case "1":
                    QuestView.CreateQuest().GetAwaiter().GetResult();
                    break;
                default:
                    Console.WriteLine("Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    private async Quest GetClient()
    {
        // Call asynchronous network methods in a try/catch block to handle exceptions.
        try
        {
            using HttpResponseMessage response = await Client.GetAsync("");
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            Console.WriteLine(responseBody);
        }
        catch (HttpRequestException e)
        {
            Console.WriteLine("\nException Caught!");
            Console.WriteLine("Message :{0} ", e.Message);
        }

    }
}