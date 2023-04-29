package proyecto.ean.demo.servicios;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyecto.ean.demo.modelo.Usuario;
import proyecto.ean.demo.servicios.usuario.UsuarioService;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@Component
public class ServicioObtenerToken {

    @Autowired
    private UsuarioService usuarioService = new UsuarioService();

    private final String NO_AUTORIZADO = "Error de autenticacion";
    private final int EXPIRACION = 3600000;
    private final String ROL = "rol";
    private final String NOMBRE_USUARIO = "nombre";
    private final String APELLIDO_USUARIO = "apellido";

    public String obtenerToken(String idUsuario, String contrasena) throws Exception {
        Usuario usu = optenerUsuario(idUsuario);
        if(validarContrasena(contrasena, usu)){
            return generearToken(usu);
        }else {
            return "USUARIO O CONTRASEÑA ERRONEOS";
        }
    }

    private SecretKey generarClaveSecreta () throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance(SignatureAlgorithm.HS512.getJcaName());
        key.init(512);
        return key.generateKey();
    }

    private Usuario optenerUsuario(String idUsuario){
        Optional<Usuario> datosUsuario = usuarioService.listarId(idUsuario);
        return datosUsuario.get();
    }

    private boolean validarContrasena(String contrasena, Usuario usu){
        return BCrypt.checkpw(contrasena, usu.getContrasena());
    }

    private String generearToken(Usuario usuario) throws NoSuchAlgorithmException {
        JwtBuilder builder = Jwts.builder()
                .claim(ROL, usuario.getTipoUsuario())
                .claim(NOMBRE_USUARIO, usuario.getNombre())
                .claim(APELLIDO_USUARIO, usuario.getApellido())
                .setSubject(usuario.getId_usuario())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(SignatureAlgorithm.HS512, generarClaveSecreta());
        return builder.compact();
    }
}
