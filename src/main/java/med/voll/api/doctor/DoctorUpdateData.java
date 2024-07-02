package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import med.voll.api.address.AddressData;

public record DoctorUpdateData(
        @NotNull
        @Positive
        Long id,
        String name,
        String phone,
        AddressData address) {
}
