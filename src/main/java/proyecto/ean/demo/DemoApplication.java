package proyecto.ean.demo;

import io.jsonwebtoken.JwtBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyecto.ean.demo.modelo.Usuario;
import proyecto.ean.demo.servicios.ServicioObtenerToken;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(DemoApplication.class, args);

	}

}
