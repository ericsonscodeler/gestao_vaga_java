package br.com.ericson.gestao_vaga.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor JavaScript")
    private String description;

    @Schema(example = "teste")
    private String username;

    @Schema(example = "João")
    private String name;

    @Schema(example = "joao@developer.com.br")
    private String email;
    private UUID id;
}
