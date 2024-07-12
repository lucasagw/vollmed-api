CREATE TABLE appointments (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              doctor_id BIGINT NOT NULL,
                              patient_id BIGINT NOT NULL,
                              date DATETIME NOT NULL,
                              CONSTRAINT fk_appointments_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id),
                              CONSTRAINT fk_appointments_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id)
);
