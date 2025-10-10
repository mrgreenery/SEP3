package com.db.sep3;

import com.db.sep3.DAO.TaskRepository;
import com.db.sep3.DAO.UserRepository;
import com.db.sep3.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main
{
  public static void main(String[] args)
  {
    SpringApplication.run(Main.class, args);
  }


  @Bean CommandLineRunner seedAndRead(UserRepository users,
      TaskRepository tasks)
  {
    return args -> {
      System.out.println("=== Users in DB ===");
      users.findAll().forEach(u ->
          System.out.printf("id=%s email=%s name=%s%n",
              u.getId(), u.getEmail(), u.getDisplayName()));

    };
  }
}