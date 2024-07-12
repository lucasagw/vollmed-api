package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.validations.appointment.AppointmentSchedulingValidator;
import med.voll.api.domain.appointment.validations.cancellation.AppointmentCancellationValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> appointmentSchedulingValidators;

    @Autowired
    private List<AppointmentCancellationValidator> cancellationValidators;


    public AppointmentDetailsData scheduleAppointment(AppointmentSchedulingData data) {

        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new NoSuchElementException(String.format("Doctor with id %d not found", data.doctorId()));
        }

        if (!patientRepository.existsById(data.patientId())) {
            throw new NoSuchElementException(String.format("Patient with id %d not found", data.doctorId()));
        }

        appointmentSchedulingValidators.forEach(validator -> validator.validate(data));

        Doctor doctor = choiceDoctor(data);

        if (doctor == null) {
            throw new IllegalArgumentException("No doctor available for the date provided");
        }

        Patient patient = patientRepository.getReferenceById(data.patientId());

        Appointment appointment = new Appointment(doctor, patient, data.date());

        Appointment save = appointmentRepository.save(appointment);

        return new AppointmentDetailsData(save);
    }

    private Doctor choiceDoctor(AppointmentSchedulingData data) {

        if (data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.specialty() == null) {
            throw new IllegalArgumentException("Specialty is required when doctorId is not provided");
        }

        return doctorRepository.findAvailableDoctorBySpecialtyAndDate(data.specialty(), data.date())
                .orElseThrow(() -> new NoSuchElementException(String.format("Doctor with specialty %s not found", data.specialty())));
    }

    public void cancel(AppointmentCancellationData data) {
        if (!appointmentRepository.existsById(data.appointmentId())) {
            throw new NoSuchElementException("The appointment ID provided does not exist!");
        }

        cancellationValidators.forEach(validator -> validator.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        appointment.cancel(data.reason());
    }


}
