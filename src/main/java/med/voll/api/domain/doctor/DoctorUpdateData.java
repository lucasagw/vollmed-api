package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import med.voll.api.domain.address.AddressData;

public record DoctorUpdateData(
        @NotNull
        @Positive
        Long id,
        String name,
        String phone,
        AddressData address) {
}
