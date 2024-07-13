package med.voll.api.controller;

import lombok.SneakyThrows;
import med.voll.api.domain.appointment.AppointmentDetailsData;
import med.voll.api.domain.appointment.AppointmentSchedulingData;
import med.voll.api.domain.appointment.AppointmentService;
import med.voll.api.domain.doctor.Specialty;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentSchedulingData> appointmentSchedulingDataJson;

    @Autowired
    private JacksonTester<AppointmentDetailsData> appointmentDetailsDataJson;

    @MockBean
    private AppointmentService appointmentService;


    @SneakyThrows
    @Test
    @WithMockUser
    @DisplayName("It should return the http code 400 when the information is invalid")
    void scheduleAppointment_scenario1() {

        MockHttpServletResponse response = mockMvc.perform(post("/appointments"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(400);
        //assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @SneakyThrows
    @Test
    @WithMockUser
    @DisplayName("Should return http code 200 when information is valid")
    void scheduleAppointment_scenario2() {

        var date = LocalDateTime.now().plusHours(1);
        var speciality = Specialty.CARDIOLOGY;

        AppointmentDetailsData appointmentDetailsData = new AppointmentDetailsData(null, 1l, 1l, date);
        when(appointmentService.scheduleAppointment(any())).thenReturn(appointmentDetailsData);

        MockHttpServletResponse response = mockMvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentSchedulingDataJson.write(
                                        new AppointmentSchedulingData(1l, 1l, date, speciality))
                                .getJson())

                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentDetailsDataJson.write(appointmentDetailsData).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}