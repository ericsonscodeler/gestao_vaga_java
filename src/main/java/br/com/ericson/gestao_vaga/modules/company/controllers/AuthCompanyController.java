package br.com.ericson.gestao_vaga.modules.company.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ericson.gestao_vaga.modules.company.dto.AuthCompanyDto;
import br.com.ericson.gestao_vaga.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @RequestMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDto authCompanyDto) {

        try {
            var result = this.authCompanyUseCase.execute(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
