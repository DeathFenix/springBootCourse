package vendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("development")

@Development
public class MyConfig {
	
//	@Bean(name = "applicationName")
//	public String applicationName() {
//		return "SPRING APPLICATION";
//	}
	
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("Executando uma config de desenvolvimento");
		};
	}

}
