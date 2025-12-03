using WebAPI.Services;
using Data;
using Grpc.Net.Client;
using WebAPI.Hubs;

var builder = WebApplication.CreateBuilder(args); 

builder.Services.AddControllers();
builder.Services.AddOpenApi(); 

// Configure gRPC client

AppContext.SetSwitch("System.Net.Http.SocketsHttpHandler.Http2UnencryptedSupport", true);

builder.Services.AddGrpcClient<UserService.UserServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090");
});

builder.Services.AddGrpcClient<QuestService.QuestServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090");

});

builder.Services.AddScoped<IQuestService, QuestServiceImpl>();
builder.Services.AddScoped<IUserService, UserServiceImpl>();
builder.Services.AddSignalR();

var app = builder.Build();

app.MapControllers();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();  
    app.UseDeveloperExceptionPage(); //shows detailed errors
}


app.MapGet("/", () => Results.Ok("Tier 2 is up"));

//map hub endpoint
app.MapHub<QuestHub>("/questhub"); 

app.Run();