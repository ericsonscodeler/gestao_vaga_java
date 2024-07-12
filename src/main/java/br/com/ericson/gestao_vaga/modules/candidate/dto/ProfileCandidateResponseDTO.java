package br.com.ericson.gestao_vaga.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    private String description;
    private String username;
    private String name;
    private String email;
    private UUID id;
}
