package mx.com.pandadevs.pibeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PibeApiApplication  {
	public static void main(String[] args) {
		SpringApplication.run(PibeApiApplication.class, args);
	}
}
