using WebAPI.Services;
using Data;
using Grpc.Net.Client;
using WebAPI.Hubs;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

var builder = WebApplication.CreateBuilder(args); 

builder.Services.AddControllers();
builder.Services.AddOpenApi(); 


// Configure JWT options (Remember me token)
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

builder.Services.AddAuthorization();

// our token generator
builder.Services.AddScoped<ITokenService, TokenService>();

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

// authentication & authorization
app.UseAuthentication();
app.UseAuthorization();


app.MapGet("/", () => Results.Ok("Tier 2 is up"));

//map hub endpoint
app.MapHub<QuestHub>("/questhub"); 

app.Run();