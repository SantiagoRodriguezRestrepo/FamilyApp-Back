package proyecto.ean.demo;

import io.jsonwebtoken.JwtBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigure(){
		String[] urlConsumidoresApiPermitidosSeparados = "http://localhost:3000/;http://localhost:3001/;https://localhost:3000/;https://localhost:3001/".split(";");
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("familiapp/**").allowedOrigins(urlConsumidoresApiPermitidosSeparados).allowedMethods("GET", "POST", "DELETE");
			}
		};
	}
}
