using CLI.Views.Task;

namespace CLI;

public class CliApp
{
    public void Start()
    {
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
}