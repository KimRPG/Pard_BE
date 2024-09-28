//package com.pard.pard_backend.domain.slack.bot;
//
//import com.slack.api.bolt.App;
//import com.slack.api.bolt.servlet.SlackAppServlet;
//import jakarta.servlet.annotation.WebServlet;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@Configuration
//public class SlackBot {
//
////    @Bean
////    public App initSlackApp(){
////        App app = new App();
////        app.command("/hello",(req,ctx)->ctx.ack("Hi there!"));
////        return app;
////    }
////
////    @RestController("/slack/events")
////    public static class SlackAppController extends SlackAppServlet {
////        public SlackAppController(App app) {
////            super(app);
////        }
////    }
//}
//
