namespace WebAPI.Services.Exceptions.User;

public class UserWithThisIdDoesNotExist : Exception
{
    public UserWithThisIdDoesNotExist()
    {
    }

    public UserWithThisIdDoesNotExist(string message)
        : base(message)
    {
    }

    public UserWithThisIdDoesNotExist(string message, Exception inner)
        : base(message, inner)
    {
    }
}