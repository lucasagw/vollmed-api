package med.voll.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;


    @PostMapping
    @Transactional
    public ResponseEntity createPatient(@RequestBody @Valid PatientRegistrationData dads, UriComponentsBuilder uriBuilder) {

        var patient = new Patient(dads);

        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> listPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        var page = patientRepository.findAllByActiveTrue(pageable).
                map(PatientListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailedPatient(@PathVariable @Positive Long id) {

        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid PatientUpdateData dads) {

        var patient = patientRepository.getReferenceById(dads.id());

        patient.updateData(dads);

        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable @Positive Long id) {

        var patient = patientRepository.getReferenceById(id);

        patient.disable();

        return ResponseEntity.noContent().build();
    }
}
