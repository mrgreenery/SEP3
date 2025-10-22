namespace CLI.Views.Task;
using System.Threading.Tasks;

public class TaskForm
{
    public static async Task CreateTask()
    {
        Console.WriteLine("--- Task Creation ---");
        
        Console.WriteLine("Provide task title: ");
        var taskTitle = Console.ReadLine();
        
        Console.WriteLine("Provide task description: ");
        var taskDescription = Console.ReadLine();
        
        //forward to request wrapper
    }
}