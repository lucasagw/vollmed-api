package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressData;
import org.hibernate.validator.constraints.br.CPF;

public record PatientRegistrationData(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        @CPF
        String cpf,

        @NotNull
        @Valid
        AddressData address) {
}
