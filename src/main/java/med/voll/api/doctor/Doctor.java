package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorRegistrationData dads) {
        this.active = true;
        this.name = dads.name();
        this.email = dads.email();
        this.phone = dads.phone();
        this.crm = dads.crm();
        this.specialty = dads.specialty();
        this.address = new Address(dads.address());
    }

    public void updateData(DoctorUpdateData dads) {

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
