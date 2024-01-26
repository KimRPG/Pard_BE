package com.pard.pard_backend;

import com.pard.pard_backend.domain.project.dto.request.ProjectRequestDTO;
import com.pard.pard_backend.domain.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PardBackendApplicationTests {

    @Autowired
    private ProjectService projectService;

    @Test
    void test() {
        for (int i = 0; i < 90; i++) {
            Map<String, List<String>> toolMap = new HashMap<>();
            toolMap.put("Tool" + i, Arrays.asList("Value1_" + i, "Value2_" + i));
            ProjectRequestDTO.Create dto = ProjectRequestDTO.Create.builder()
                    .projectId((long) i) // 예시로 i를 사용
                    .teamName("TeamName" + i)
                    .generation("Generation" + i)
                    .tool(toolMap)
                    .build();

            this.projectService.createProject(dto);
        }
    }

}
