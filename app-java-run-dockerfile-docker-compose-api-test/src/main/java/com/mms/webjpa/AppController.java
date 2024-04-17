package com.mms.webjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/test")
    public String getMensaje(){
        System.out.println("Bienvenido a la aplicación!");
        return "Bienvenido a la aplicación!";
    }

}
