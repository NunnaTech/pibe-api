package mx.com.pandadevs.pibeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "mx.com.pandadevs.pibeapi.models")
public class PibeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PibeApiApplication.class, args);
	}

}
