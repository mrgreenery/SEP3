using BlazorApp.Components;
using BlazorApp.Services.Auth;
using BlazorApp.Services.Quest;
using BlazorApp.Services.User;
using Microsoft.AspNetCore.Components.Authorization;
using Syncfusion.Blazor;

var builder = WebApplication.CreateBuilder(args);

// --------------------
// Third-party license
// --------------------
Syncfusion.Licensing.SyncfusionLicenseProvider.RegisterLicense("Ngo9BigBOggjHTQxAR8/V1JFaF5cXGRCf1FpRmJGdld5fUVHYVZUTXxaS00DNHVRdkdmWH1eeXRdR2ZeV0d0WEBWYEg=");


// --------------------
// HTTP / backend access
// --------------------
builder.Services.AddScoped(sp => new HttpClient
{
    BaseAddress = new Uri("https://localhost:7009")
});


// --------------------
// Blazor
// --------------------
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();
builder.Services.AddAuthorizationCore();


// --------------------
// Auth
// --------------------
builder.Services.AddScoped<AuthProvider>();
builder.Services.AddScoped<AuthenticationStateProvider>(sp =>
    sp.GetRequiredService<AuthProvider>()); 

// --------------------
// Application services
// --------------------
builder.Services.AddScoped<IUserService, HttpUserService>();
builder.Services.AddScoped<IQuestService, HttpQuestService>();
builder.Services.AddSingleton<QuestHubService>(); //adding signalR service


// --------------------
// UI library
// --------------------
builder.Services.AddSyncfusionBlazor();


// --------------------
// Logging
// --------------------
builder.Logging.SetMinimumLevel(LogLevel.Debug);
builder.Logging.AddConsole();


// --------------------
// Pipeline
// --------------------
var app = builder.Build();

//connect to SignalR on start-up
var hubService = app.Services.GetRequiredService<QuestHubService>(); 
await hubService.ConnectAsync("https://localhost:7009/questhub");

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    app.UseHsts();
}

app.UseStatusCodePagesWithReExecute("/not-found", 
    createScopeForStatusCodePages: true);


app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
