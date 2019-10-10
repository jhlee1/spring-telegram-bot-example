package lee.joohan.springtelegrambotexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class SpringTelegrambotExampleApplication {

  public static void main(String[] args) {
    ApiContextInitializer.init();
    SpringApplication.run(SpringTelegrambotExampleApplication.class, args);
  }

}
