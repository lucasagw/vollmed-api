package med.voll.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;


    @PostMapping
    @Transactional
    public void createDoctor(@RequestBody @Valid DoctorRegistrationData dads) {

        doctorRepository.save(new Doctor(dads));
    }

    @GetMapping
    public Page<DoctorListData> listDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        return doctorRepository.findAllByActiveTrue(pageable).
                map(DoctorListData::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid DoctorUpdateData dads) {

        var doctor = doctorRepository.getReferenceById(dads.id());

        doctor.updateData(dads);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable @Positive Long id) {

        var doctor = doctorRepository.getReferenceById(id);

        doctor.disable();
    }
}
