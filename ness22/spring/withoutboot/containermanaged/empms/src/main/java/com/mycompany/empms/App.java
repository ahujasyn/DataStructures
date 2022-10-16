package com.mycompany.empms;

import com.mycompany.empms.frontend.FrontEndClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfiguration.class);
        FrontEndClient frontEnd = context.getBean(FrontEndClient.class);
        frontEnd.render();
        context.close();
    }
}
