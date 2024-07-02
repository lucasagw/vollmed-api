package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;


    public Address(AddressData address) {
        this.street = address.street();
        this.number = address.number();
        this.complement = address.complement();
        this.neighborhood = address.neighborhood();
        this.city = address.city();
        this.state = address.state();
        this.postalCode = address.postalCode();
    }

    public void updateData(AddressData dads) {

            if (dads.street() != null) {
                this.street = dads.street();
            }
            if (dads.number() != null) {
                this.number = dads.number();
            }
            if (dads.complement() != null) {
                this.complement = dads.complement();
            }
            if (dads.neighborhood() != null) {
                this.neighborhood = dads.neighborhood();
            }
            if (dads.city() != null) {
                this.city = dads.city();
            }
            if (dads.state() != null) {
                this.state = dads.state();
            }
            if (dads.postalCode() != null) {
                this.postalCode = dads.postalCode();
            }

    }
}

