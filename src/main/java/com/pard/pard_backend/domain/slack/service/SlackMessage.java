package com.pard.pard_backend.domain.slack.service;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Getter
@Slf4j
@RequiredArgsConstructor
@Component
public class SlackMessage {

    private final Slack slackClient = Slack.getInstance();

    @Value("${slack.bot.webhook-uri-talk}")
    private String webhookUrl;

//    @Value("${webhook-uri}")
//    private String webhookUrl;
    public void sendSlackMessage(String name, String part, String now) {
        try {
            slackClient.send(webhookUrl, payload(p -> p
                    .text("🎉 *Happy Birthday, " + name + "!* 🎉")
                    .attachments(
                            List.of(generateSlackAttachment(part,name, "#36a64f", now))
                    )
            ));
        } catch (IOException slackError) {
            log.debug("Slack 통신과의 예외 발생");
        }
    }

    // attachment 생성 메서드
    private Attachment generateSlackAttachment(String part,String name, String color, String requestTime) {
        String message = String.format("오늘은 %s의 %s님의 특별한 날입니다! 🎉", part, name);
        System.out.println(message);
        return Attachment.builder()
                .color(color)  // 왼쪽 띠의 색
                .title(requestTime + " - 🎂 생일 축하 🎂")
                .text(message)
                .footer("생일 축하 메시지 | Slack Bot")
                .footerIcon("https://image.flaticon.com/icons/png/512/888/888879.png")
                .ts(String.valueOf(System.currentTimeMillis() / 1000))
                .imageUrl("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExY2J4cDY4czAxaGFyb3p3YTA5bXFvZ3FoeXhvd2pod3FscmpxNmhjbCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/g5R9dok94mrIvplmZd/giphy.webp")
                .fields(List.of(
                        generateSlackField("생일 축하", name+ "님, *생일* 진심으로 축하합니다!\n*행복한 하루* 되세요~!!"),
                        generateSlackField("축하 메시지",
                                "⋆⸜⊹₊ \uD835\uDC07\uD835\uDC1A\uD835\uDC29\uD835\uDC29\uD835\uDC32 \uD835\uDC01\uD835\uDC22\uD835\uDC2B\uD835\uDC2D\uD835\uDC21\uD835\uDC1D\uD835\uDC1A\uD835\uDC32 ¨̮⑅*⸝⋆\n" +
                                        "( * ॑꒳ ॑*)ﾉ\"┌iiii┐ヾ(* ॑꒳ ॑* )")
                ))
                .build();
    }

    // Field 생성 메서드
    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(true)
                .build();
    }

//    public void sendSlackAlertErrorLog(String errMessage, HttpServletRequest request) {
//        try {
//
//            slackClient.send(webhookUrl, payload(p -> p
//                    .text("서버 에러 발생! 백엔드 측의 빠른 확인 요망")
//                    // attachment는 list 형태여야 합니다.
//                    .attachments(
//                            List.of(generateSlackAttachment(errMessage, request, "ff0000" ))
//                    )
//            ));
//        } catch (IOException slackError) {
//            // slack 통신 시 발생한 예외에서 Exception을 던져준다면 재귀적인 예외가 발생합니다.
//            // 따라서 로깅으로 처리하였고, 아이러빗 서버 에러는 아니므로 `error` 레벨보다 낮은 레벨로 설정했습니다.
//            log.debug("Slack 통신과의 예외 발생");
//        }
//    }
//
//    /**
//     * 클라이언트 에러 전송
//     */
//    public void sendSlackAlertWarnLog(String errMessage, HttpServletRequest request) {
//        try {
//
//            slackClient.send(webhookUrl, payload(p -> p
//                    .text("클라이언트 에러 발생! 프론트엔드 측의 빠른 확인 요망")
//                    // attachment는 list 형태여야 합니다.
//                    .attachments(
//                            List.of(generateSlackAttachment(errMessage, request,"ffff00" ))
//                    )
//            ));
//        } catch (IOException slackError) {
//            log.debug("Slack 통신과의 예외 발생");
//        }
//    }
//
//
//    // attachment 생성 메서드
//    private Attachment generateSlackAttachment(String errMessage, HttpServletRequest request, String color) {
//        String requestTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
//        String xffHeader = request.getHeader("X-FORWARDED-FOR");  // 프록시 서버일 경우 client IP는 여기에 담길 수 있습니다.
//        return Attachment.builder()
//                .color(color)  // 왼쪽 띠의 색
//                .title(requestTime + " 발생 에러 로그")
//                // Field도 List 형태로 담아주어야 합니다.
//                .fields(List.of(
//                                generateSlackField("Request IP", xffHeader == null ? request.getRemoteAddr() : xffHeader),
//                                generateSlackField("Request URL", request.getRequestURL() + " " + request.getMethod()),
//                                generateSlackField("Error Message", errMessage)
//                        )
//                )
//                .build();
//    }

}