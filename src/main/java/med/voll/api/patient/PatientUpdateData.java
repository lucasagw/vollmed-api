package med.voll.api.patient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import med.voll.api.address.AddressData;

public record PatientUpdateData(
        @NotNull
        @Positive
        Long id,
        String name,
        String phone,
        AddressData address) {
}
