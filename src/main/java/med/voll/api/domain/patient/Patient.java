package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private Address address;

    private boolean active;

    public Patient(PatientRegistrationData dads) {
        this.active = true;
        this.name = dads.name();
        this.email = dads.email();
        this.phone = dads.phone();
        this.address = new Address(dads.address());
    }

    public void updateData(PatientUpdateData dads) {

        if (dads.name() != null) {
            this.name = dads.name();
        }
        if (dads.phone() != null) {
            this.phone = dads.phone();
        }
        if (dads.address() != null) {
            this.address.updateData(dads.address());
        }
    }

    public void disable() {
        this.active = false;
    }
}
