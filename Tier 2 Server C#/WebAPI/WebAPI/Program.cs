using WebAPI.Services;
using Data;
using Grpc.Net.Client;

var builder = WebApplication.CreateBuilder(args); 

builder.Services.AddControllers();
builder.Services.AddOpenApi(); 

// Configure gRPC client
builder.Services.AddGrpcClient<DataService.DataServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090");
});

builder.Services.AddScoped<IQuestService, QuestService>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();  
    app.UseDeveloperExceptionPage(); //shows detailed errors
}

app.MapControllers();

app.MapGet("/", () => Results.Ok("Tier 2 is up"));


app.Run();