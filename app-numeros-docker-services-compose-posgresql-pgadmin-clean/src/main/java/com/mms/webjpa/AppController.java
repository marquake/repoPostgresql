package com.mms.webjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/test")
    public String getMensaje(){
        System.out.println("0 - XXXXXXXXXXXXXXXXXXXXXX");
        return "0 - Hola! XXXXXXXXXXXX";
    }

}
