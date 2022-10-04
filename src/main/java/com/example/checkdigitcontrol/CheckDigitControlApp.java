package com.example.checkdigitcontrol;

import com.example.checkdigitcontrol.config.AppConfig;
import com.example.checkdigitcontrol.services.CheckDigitControlService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * CheckDigitControlApp
 *
 * @author  Marcelo Gomes
 * @created 2022-10-01
 */

@SpringBootApplication
public class CheckDigitControlApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CheckDigitControlService checkDigitControlService = ctx.getBean(CheckDigitControlService.class);
        checkDigitControlService.execute();
    }

}
