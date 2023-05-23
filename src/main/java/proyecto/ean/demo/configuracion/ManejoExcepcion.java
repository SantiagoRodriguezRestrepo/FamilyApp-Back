package proyecto.ean.demo.configuracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoExcepcion{

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ObjectNode> handleRuntimeException(RuntimeException e) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("Error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
}
