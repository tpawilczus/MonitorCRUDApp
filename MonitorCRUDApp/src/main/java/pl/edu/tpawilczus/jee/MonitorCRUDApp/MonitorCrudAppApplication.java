package pl.edu.tpawilczus.jee.MonitorCRUDApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.tpawilczus.jee.MonitorCRUDApp.service.MonitorService;

@SpringBootApplication
public class MonitorCrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorCrudAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(@Autowired MonitorService monitorService) {
		return (args) -> monitorService.enterSomeData();
	}

}
