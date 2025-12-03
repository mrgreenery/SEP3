using Microsoft.AspNetCore.SignalR;

namespace WebAPI.Hubs;

 /*
 hub is empty, because hub is just a routing endpoint. 
 We broadcast from controllers using IHubContext after database operations
 not from Hub methods that clients would invoke directly.
 */
public class QuestHub : Hub
{
    
}
