package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should return null when the only registered doctor is not available on the date")
    void findAvailableDoctorBySpecialtyAndDateScenario1() {

        //given or arrange
        var localDateTime = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Dr. House", "house.doctor@vollmed.com", "123456", Specialty.CARDIOLOGY);
        var patient = registerPatient("John Doe", "john.doe@gmail.com", "06139772540");
        registerAppointment(doctor, patient, localDateTime);

        //when or act
        var availableDoctor = doctorRepository.findAvailableDoctorBySpecialtyAndDate(Specialty.CARDIOLOGY, localDateTime);

        //then or assert
        //assertThat(availableDoctor).isEmpty();
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return to the doctor when he is available on the date")
    void findAvailableDoctorBySpecialtyAndDateScenario2() {

        //given or arrange
        var localDateTime = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Doctor doctor = registerDoctor("Dr. House", "house.doctor@vollmed.com", "123456", Specialty.CARDIOLOGY);

        //when or act
        var availableDoctor = doctorRepository.findAvailableDoctorBySpecialtyAndDate(Specialty.CARDIOLOGY, localDateTime);

        //then or assert
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegistrationData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientRegistrationData patientData(String name, String email, String cpf) {
        return new PatientRegistrationData(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "xpto street",
                "1313",
                "00000000",
                "neighborhood",
                "Pernambuco",
                "PE",
                "55555-555"

        );
    }

}