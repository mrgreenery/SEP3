using WebAPI.ApiContracts;

namespace CLI.Views.Task;
using System.Threading.Tasks;

public class TaskView(TaskViewModel taskViewModel)
{
    public async Task CreateTask()
    {
        Console.WriteLine("--- Task Creation ---");
        
        Console.WriteLine("Provide task title: ");
        var taskTitle = Console.ReadLine();
        
        Console.WriteLine("Provide task description: ");
        var taskDescription = Console.ReadLine();

        CreateTaskResponse response = await taskViewModel.CreateTask(taskTitle, taskDescription);
        
        Console.WriteLine($"Task created successfully:\nid: {response.Id},created at: {response.CreatedAt}, \ntitle:{response.Title},\ndescription: {response.Description}");

    }
}