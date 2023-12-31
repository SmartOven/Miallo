package ru.panteleevya.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.panteleevya.backend.props.PropsManager;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        PropsManager.getProps(System.getenv("oauth"), System.getenv("secret_id"));
        SpringApplication.run(BackendApplication.class, args);
    }

}
