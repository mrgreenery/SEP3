using CLI.DTO;
using CLI.ViewModels.Tasks;

namespace CLI.Views.Task;
using System.Threading.Tasks;

public class TaskForm(Tasks tasksVM)
{
    public async Task CreateTask()
    {
        Console.WriteLine("--- Task Creation ---");
        
        Console.WriteLine("Provide task title: ");
        var taskTitle = Console.ReadLine();
        
        Console.WriteLine("Provide task description: ");
        var taskDescription = Console.ReadLine();

        KanbanTaskDTO kanbanTaskDto = await tasksVM.CreateTask(taskTitle, taskDescription);
        Console.WriteLine($"Task created: {kanbanTaskDto}");
    }
}