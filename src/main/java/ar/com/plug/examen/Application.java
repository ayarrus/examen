package ar.com.app.examen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("ar.com.app")
public class Application {

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
