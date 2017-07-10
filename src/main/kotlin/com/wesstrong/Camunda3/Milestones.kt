package com.wesstrong.Camunda3

/**
 * Created by SBN on 02/07/2017.
 */


import org.camunda.bpm.model.cmmn.instance.Milestone
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

//copied from https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/
//but... this looks like a way more comprehensive tutorial for Spring: http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html
@Controller
class MilestonesController {

    @RequestMapping("/milestones")
    fun milestones (model: Model) : String { //STEVE not sure if this should be string or collection of milestones
        model.addAttribute("milestones", queryCaseDefMilestones())
        return "milestones"
    }

}

