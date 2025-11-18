namespace WebAPI.Services.Exceptions.User;

public class UserWithThisEmailAlreadyExists : Exception
{
    public UserWithThisEmailAlreadyExists()
    {
    }

    public UserWithThisEmailAlreadyExists(string message)
        : base(message)
    {
    }

    public UserWithThisEmailAlreadyExists(string message, Exception inner)
        : base(message, inner)
    {
    }
}