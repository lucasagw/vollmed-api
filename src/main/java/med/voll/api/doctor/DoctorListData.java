package med.voll.api.doctor;

public record DoctorListData(
        Long id,
        String name,
        String crm,
        String email,
        Specialty specialty) {

    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getEmail(), doctor.getSpecialty());
    }
}
