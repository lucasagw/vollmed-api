package med.voll.api.patient;

import med.voll.api.address.Address;

public record PatientDetailData(Long id, String name, String email, String phone, String cpf, Address address, boolean active) {

    public PatientDetailData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress(), patient.isActive());
    }
}
