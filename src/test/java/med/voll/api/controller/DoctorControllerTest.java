package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorRegistrationData> doctorRegistrationDataJson;

    @Autowired
    private JacksonTester<DoctorDetailData> doctorDetailDataJson;

    @MockBean
    private DoctorRepository repository;


    @Test
    @DisplayName("Should return HTTP 400 status when information is invalid")
    @WithMockUser
    void register_scenario1() throws Exception {
        var response = mvc
                .perform(post("/doctors"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP 201 status when information is valid")
    @WithMockUser
    void register_scenario2() throws Exception {
        var registrationData = new DoctorRegistrationData(
                "Doctor",
                "doctor@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGY,
                addressData());

        when(repository.save(any())).thenReturn(new Doctor(registrationData));

        var response = mvc
                .perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(doctorRegistrationDataJson.write(registrationData).getJson()))
                .andReturn().getResponse();

        var detailData = new DoctorDetailData(
                null,
                registrationData.name(),
                registrationData.email(),
                registrationData.phone(),
                registrationData.crm(),
                registrationData.specialty(),
                new Address(registrationData.address()),
                true
        );

        var expectedJson = doctorDetailDataJson.write(detailData).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    private AddressData addressData() {
        return new AddressData(
                "xpto street",
                "13",
                "center",
                "neighborhood",
                "Santa Maria",
                "DF",
                "55555-555"
        );
    }

}