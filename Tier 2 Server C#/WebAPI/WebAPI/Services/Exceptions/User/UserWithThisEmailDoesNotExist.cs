namespace WebAPI.Services.Exceptions.User;

public class UserWithThisEmailDoesNotExist : Exception
{
    public UserWithThisEmailDoesNotExist()
    {
    }

    public UserWithThisEmailDoesNotExist(string message)
        : base(message)
    {
    }

    public UserWithThisEmailDoesNotExist(string message, Exception inner)
        : base(message, inner)
    {
    }
}