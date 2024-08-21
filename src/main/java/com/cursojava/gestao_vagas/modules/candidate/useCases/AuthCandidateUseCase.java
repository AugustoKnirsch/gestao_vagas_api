package com.cursojava.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cursojava.gestao_vagas.modules.candidate.CandidateRepository;
import com.cursojava.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.cursojava.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

import javax.naming.AuthenticationException;

@Service
public class AuthCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUserName(authCandidateRequestDTO.userName())
            .orElseThrow(() -> {
                throw new UsernameNotFoundException("UserName/password incorrect");
            });

        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();            
        }
        
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10)); 
        var token = JWT.create()
            .withIssuer("java_vagas")
            .withExpiresAt(expiresIn)
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("CANDIDATE"))
            .sign(algorithm);
        
        var authCandidateResponse = AuthCandidateResponseDTO.builder()
            .access_token(token)
            .expires_in(expiresIn.toEpochMilli())
            .build();  
            
        return authCandidateResponse;

    }
    
}
