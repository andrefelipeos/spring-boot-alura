package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.signature-key}")
	private String chaveDeAssinatura;

	@Value("${api.configuration.default-timezone}")
	private String timezone;

	public String gerarToken(Usuario usuario) {
		try {
		    var algoritmo = Algorithm.HMAC256(chaveDeAssinatura);
		    return JWT.create()
		        .withIssuer("API Voll.med")
		        .withSubject(usuario.getNomeDeUsuario())
		        .withExpiresAt(instanteDeExpiracao())
		        .sign(algoritmo);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar token JWT", exception);
		}
	}

	private Instant instanteDeExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(timezone));
	}

}