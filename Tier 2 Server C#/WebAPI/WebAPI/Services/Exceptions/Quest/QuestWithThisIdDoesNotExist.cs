namespace WebAPI.Services.Exceptions.User;

public class QuestWithThisIdDoesNotExist : Exception
{
    public QuestWithThisIdDoesNotExist()
    {
    }

    public QuestWithThisIdDoesNotExist(string message)
        : base(message)
    {
    }

    public QuestWithThisIdDoesNotExist(string message, Exception inner)
        : base(message, inner)
    {
    }
}