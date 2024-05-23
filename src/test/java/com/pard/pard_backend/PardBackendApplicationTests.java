package com.pard.pard_backend;

import com.pard.pard_backend.domain.fcm.service.FCMService;
import com.pard.pard_backend.domain.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.domain.project.service.ProjectService;
import com.pard.pard_backend.domain.qr.dto.request.RequestQrDto;
import com.pard.pard_backend.domain.qr.dto.response.ResponseQrDto;
import com.pard.pard_backend.domain.qr.service.QRService;
import com.pard.pard_backend.domain.schedule.dto.response.ScheduleResponseDTO;
import com.pard.pard_backend.domain.schedule.entity.Schedule;
import com.pard.pard_backend.domain.schedule.repo.ScheduleRepo;
import com.pard.pard_backend.domain.schedule.service.ScheduleService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PardBackendApplicationTests {

    @Autowired
    private QRService qrService;

    @MockBean
    private OkHttpClient mockOkHttpClient;


    @MockBean
    private Call mockCall;

    @Autowired
    private ScheduleService scheduleService;

    @MockBean
    private ScheduleRepo scheduleRepo;


        @Test
        public void testLocalDateNow() {
            LocalDateTime expected = LocalDateTime.now();
            System.out.println(expected);
        }

}
