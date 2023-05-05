package proyecto.ean.demo.configuracion;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configBean {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Familiapp ")
                        .description("Carcel femenina familia")
                        .version("v0.0.1")
                        )
                .externalDocs(new ExternalDocumentation()
                        .description("Familiapp Wiki Documentation")
                        );
    }

}
