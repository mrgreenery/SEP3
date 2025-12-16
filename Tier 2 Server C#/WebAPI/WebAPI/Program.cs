using Data;
using WebAPI.Hubs;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using WebAPI.Services.Implementations;
using WebAPI.Services.Interfaces;
using WebAPI.Services.Util;

var builder = WebApplication.CreateBuilder(args); 

// --------------------
// Core services
// --------------------
builder.Services.AddControllers();
builder.Services.AddAuthorization();
//OpenAPI
builder.Services.AddOpenApi(); 
//SignalR
builder.Services.AddSignalR();


// --------------------
// JWT authentication
// --------------------
// Configure JWT options ("Remember me" token)
var jwtSection = builder.Configuration.GetSection("Jwt");
builder.Services.Configure<JwtOptions>(jwtSection);

var keyBytes = Encoding.UTF8.GetBytes(jwtSection["Key"]!);

builder.Services
    .AddAuthentication(options =>
    {
        options.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
        options.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
    })
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = true,
            ValidateIssuerSigningKey = true,
            ValidateLifetime = true,
            ValidIssuer = jwtSection["Issuer"],
            ValidAudience = jwtSection["Audience"],
            IssuerSigningKey = new SymmetricSecurityKey(keyBytes),
            ClockSkew = TimeSpan.Zero
        };
    });


// --------------------
// gRPC clients
// --------------------
AppContext.SetSwitch("System.Net.Http.SocketsHttpHandler.Http2UnencryptedSupport", true);

// Configure gRPC client
builder.Services.AddGrpcClient<UserService.UserServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090");
});

builder.Services.AddGrpcClient<QuestService.QuestServiceClient>(options =>
{
    options.Address = new Uri("http://localhost:9090");
});


// --------------------
// Application services
// --------------------
builder.Services.AddScoped<IQuestService, QuestServiceImpl>();
builder.Services.AddScoped<IUserService, UserServiceImpl>();
builder.Services.AddScoped<ITokenService, TokenService>();


// --------------------
// Pipeline
// --------------------
var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();  
    app.UseDeveloperExceptionPage(); //shows detailed errors
}

app.UseAuthentication();
app.UseAuthorization();

app.MapControllers();

//map hub endpoint for SignalR
app.MapHub<QuestHub>("/questhub");

app.Run();