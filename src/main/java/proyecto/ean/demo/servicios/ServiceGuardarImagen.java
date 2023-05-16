package proyecto.ean.demo.servicios;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import proyecto.ean.demo.configuracion.ManejoExcepcion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Component
public class ServiceGuardarImagen {

    private static final String ARCHIVO_INVALIDO= "El archivo debe ser una imagen";
    private static final String ERROR_GUARDADO = "Se presento un error al guardar la imagen";
    private static final String RUTA_GUARDADO = "C:\\familiapp\\FamilyApp-Front\\public";

    public String guardarImagen(MultipartFile adjunto, String reclusa) throws Exception {
        if (!validarFormatoImagen(adjunto)){
            throw new IllegalArgumentException(ARCHIVO_INVALIDO);
        }

        try {
            byte[] bytes = adjunto.getBytes();
            int random = numeroAleatorio();
            Path path = Paths.get(RUTA_GUARDADO + reclusa + "-" + random + adjunto.getOriginalFilename()); //nombre del archivo a guardar
            Files.write(path, bytes);
            return path.toString();
        } catch (IOException e){
            throw new IllegalArgumentException(ERROR_GUARDADO);
        }
    }

    private boolean validarFormatoImagen(MultipartFile adjunto){
        return Objects.requireNonNull(adjunto.getContentType()).startsWith("image/");
    }

    private int numeroAleatorio(){
        Random random = new Random();
        return random.nextInt(100);
    }
}
