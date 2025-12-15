using BlazorApp.Components;
using BlazorApp.Services.Auth;
using BlazorApp.Services.Quest;
using BlazorApp.Services.User;
using Microsoft.AspNetCore.Components.Authorization;
using Microsoft.AspNetCore.Components.Server.ProtectedBrowserStorage;


using Syncfusion.Blazor;


var builder = WebApplication.CreateBuilder(args);

Syncfusion.Licensing.SyncfusionLicenseProvider.RegisterLicense("Ngo9BigBOggjHTQxAR8/V1JFaF5cXGRCf1FpRmJGdld5fUVHYVZUTXxaS00DNHVRdkdmWH1eeXRdR2ZeV0d0WEBWYEg=");


builder.Services.AddScoped(sp => new HttpClient
{
    BaseAddress = new Uri("https://localhost:7009")
});

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();
builder.Services.AddAuthorizationCore();
builder.Services.AddScoped<AuthenticationStateProvider, AuthProvider>();
builder.Services.AddScoped<IUserService, HttpUserService>();
builder.Services.AddScoped<IQuestService, HttpQuestService>();
builder.Services.AddSingleton<QuestHubService>(); //adding signalR service
builder.Services.AddAuthorizationCore();
builder.Services.AddScoped<AuthProvider>();
builder.Services.AddScoped<AuthenticationStateProvider>(sp =>
    sp.GetRequiredService<AuthProvider>());
builder.Services.AddScoped<QuestHubService>(); //adding signalR service
builder.Services.AddSyncfusionBlazor();
builder.Logging.SetMinimumLevel(LogLevel.Debug);
builder.Logging.AddConsole();


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
