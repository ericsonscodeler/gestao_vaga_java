package br.com.ericson.gestao_vaga.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ericson.gestao_vaga.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.ericson.gestao_vaga.modules.candidate.useCases.AuthenticateCandidateUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

    @Autowired
    private AuthenticateCandidateUseCase authenticateCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateDTO) {
        try {
            var token = this.authenticateCandidateUseCase.execute(authCandidateDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
