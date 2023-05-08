package proyecto.ean.demo.servicios;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Component
public class ServiceGuardarImagen {

    private static final String ARCHIVO_INVALIDO= "El archivo debe ser una imagen";
    private static final String ERROR_GUARDADO = "Se presento un error al guardar la imagen";
    private static final String RUTA_GUARDADO = "D:\\familiapp\\registros\\";

    public String guardarImagen(MultipartFile adjunto, String reclusa){
        if (!validarFormatoImagen(adjunto)){
             throw new RuntimeException(ARCHIVO_INVALIDO);
        }

        try {
            byte[] bytes = adjunto.getBytes();
            int random = numeroAleatorio();
            Path path = Paths.get(RUTA_GUARDADO + reclusa + "-" + random + adjunto.getOriginalFilename());
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e){
            throw new RuntimeException(ERROR_GUARDADO + " " + e);
        }
    }

    private boolean validarFormatoImagen(MultipartFile adjunto){
        return adjunto.getContentType().startsWith("image/");
    }

    private int numeroAleatorio(){
        Random random = new Random();
        return random.nextInt(100000);
    }

}
