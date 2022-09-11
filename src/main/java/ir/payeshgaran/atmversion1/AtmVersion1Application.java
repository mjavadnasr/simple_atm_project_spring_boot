package ir.payeshgaran.atmversion1;

import ir.payeshgaran.atmversion1.model.Account;
import ir.payeshgaran.atmversion1.service.impl.AccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtmVersion1Application {

    public static void main(String[] args) {
        SpringApplication.run(AtmVersion1Application.class);
    }

    @Bean
    CommandLineRunner run(AccountServiceImpl accountService) {
        return args -> {
            accountService.save(new Account("javad", "password"));
            accountService.save(new Account("reza", "password"));
            accountService.save(new Account("hasan", "password"));
        };
    }
}
