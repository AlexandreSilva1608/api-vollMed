package med.voll.api.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastoPaciente(

        @NotBlank
        String nome,

        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        DadosEndereco endereco) {}
