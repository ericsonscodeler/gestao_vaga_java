package br.com.ericson.gestao_vaga.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import br.com.ericson.gestao_vaga.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.ericson.gestao_vaga.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.ericson.gestao_vaga.modules.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withIssuer("java")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }
}
