package med.voll.api.doctor;

import med.voll.api.address.Address;

public record DoctorDetailData(Long id, String name, String email, String phone, String crm, Specialty specialty, Address address, boolean active) {

    public DoctorDetailData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress(), doctor.isActive());
    }
}