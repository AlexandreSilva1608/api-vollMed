package med.voll.api.domain.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastoPaciente(

        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
        String cpf,

        DadosEndereco endereco) {}
