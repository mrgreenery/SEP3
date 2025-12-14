using BlazorApp.Components;
using BlazorApp.Services.Auth;
using BlazorApp.Services.Quest;
using BlazorApp.Services.User;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Server.ProtectedBrowserStorage;




var builder = WebApplication.CreateBuilder(args);

builder.Services.AddScoped(sp => new HttpClient
{
    BaseAddress = new Uri("https://localhost:7009")
});

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();
builder.Services.AddScoped<IUserService, HttpUserService>();
builder.Services.AddScoped<IQuestService, HttpQuestService>();
builder.Services.AddSingleton<QuestHubService>(); //adding signalR service
builder.Services.AddAuthorizationCore();
builder.Services.AddScoped<AuthProvider>();
builder.Services.AddScoped<AuthenticationStateProvider>(sp =>
    sp.GetRequiredService<AuthProvider>());


var app = builder.Build();

var hubService = app.Services.GetRequiredService<QuestHubService>(); //connect to hub on start-up
await hubService.ConnectAsync("https://localhost:7009/questhub");

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    app.UseHsts();
}
app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
// app.UseHttpsRedirection();

app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
