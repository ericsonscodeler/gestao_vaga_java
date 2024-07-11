package br.com.ericson.gestao_vaga.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDto {

    private String password;
    private String username;
}
