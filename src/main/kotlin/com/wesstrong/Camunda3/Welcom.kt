package com.wesstrong.Camunda3

/**
 * Created by SBN on 02/07/2017.
 */


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

//copied from https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/
//but... this looks like a way more comprehensive tutorial for Spring: http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html
@Controller
class WelcomeController {

    // inject via application.properties
    @Value("\${welcome.message:test}")
    private val message = "Hello World"

    @RequestMapping("/welcome")
    fun welcome(model: MutableMap<String, Any>): String {
        model.put("message", this.message)
        return "welcome"
    }

}

