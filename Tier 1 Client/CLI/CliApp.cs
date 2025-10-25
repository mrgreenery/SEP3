using CLI.Views.Task;

namespace CLI;

public class CliApp
{
    static HttpClient client = new HttpClient();
    
    
    public void Start()
    {
        GetClient().GetAwaiter().GetResult();
        
        while (true)
        {
            Console.WriteLine("Welcome to SEP3 Team 1 Proof of Concept!");
            Console.WriteLine("1. Create task");
            Console.Write("Choose an option and press Enter: ");
            
            var choice = Console.ReadLine();
            switch (choice)
            {
                case "1":
                    TaskForm.CreateTask().GetAwaiter().GetResult();
                    break;
                default:
                    Console.WriteLine("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private async Task GetClient()
    {
        // Call asynchronous network methods in a try/catch block to handle exceptions.
        try
        {
            // TODO: change port 
            using HttpResponseMessage response = await client.GetAsync("http://localhost:1234/");
            response.EnsureSuccessStatusCode();
            string responseBody = await response.Content.ReadAsStringAsync();
            // Above three lines can be replaced with new helper method below
            // string responseBody = await client.GetStringAsync(uri);

            Console.WriteLine(responseBody);
        }
        catch (HttpRequestException e)
        {
            Console.WriteLine("\nException Caught!");
            Console.WriteLine("Message :{0} ", e.Message);
        }
        
    }
}