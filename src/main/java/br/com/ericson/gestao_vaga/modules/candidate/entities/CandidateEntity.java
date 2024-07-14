package br.com.ericson.gestao_vaga.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "João")
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "joaoteste")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "joao@teste.com.br")
    private String email;

    @NotBlank()
    @Length(min = 10, max = 100)
    @Schema(example = "1234567890", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED)
    private String password;

    @Schema(example = "Desenvolvedor React")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
