package proyecto.ean.demo.configuracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejoExcepcion{

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ObjectNode> handleIllegalArgumentException(IllegalArgumentException e) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("Error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }


}
