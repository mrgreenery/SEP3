using Microsoft.AspNetCore.SignalR.Client;

namespace BlazorApp.Services.Quest;

public class QuestHubService : IAsyncDisposable
{
    private HubConnection? _hubConnection;
    
    public event Action? OnQuestChanged;  // Event for UI refreshes

    public async Task ConnectAsync(string hubUrl)
    {
        _hubConnection = new HubConnectionBuilder()
            .WithUrl(hubUrl)
            .WithAutomaticReconnect()  // Auto-reconnect when disconnect
            .Build();

        // Listeners for all quest events
        _hubConnection.On<object>("QuestCreated", _ => OnQuestChanged?.Invoke());
        _hubConnection.On<object>("QuestUpdated", _ => OnQuestChanged?.Invoke());
        _hubConnection.On<long>("QuestDeleted", _ => OnQuestChanged?.Invoke());

        await _hubConnection.StartAsync();
    }

    private async Task DisconnectAsync()
    {
        if (_hubConnection is not null)
        {
            await _hubConnection.StopAsync();
            await _hubConnection.DisposeAsync();
        }
    }

    public async ValueTask DisposeAsync()
    {
        await DisconnectAsync();
    }
}