package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;


    @PostMapping
    @Transactional
    public ResponseEntity createDoctor(@RequestBody @Valid DoctorRegistrationData dads, UriComponentsBuilder uriBuilder) {

        var doctor = new Doctor(dads);

        doctorRepository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        var page = doctorRepository.findAllByActiveTrue(pageable).
                map(DoctorListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detailedDoctor(@PathVariable @Positive Long id) {

        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateData dads) {

        var doctor = doctorRepository.getReferenceById(dads.id());

        doctor.updateData(dads);

        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable @Positive Long id) {

        var doctor = doctorRepository.getReferenceById(id);

        doctor.disable();

        return ResponseEntity.noContent().build();
    }
}
