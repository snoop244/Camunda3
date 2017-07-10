package com.wesstrong.Camunda3

/**
 * Created by SBN on 02/07/2017.
 */


import com.wesstrong.codegen.queryCaseDefMilestones
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MilestonesController {

    @RequestMapping("/milestones")
    fun milestones (model: Model) : String { //STEVE not sure if this should be string or collection of milestones
        model.addAttribute("milestones", queryCaseDefMilestones())
        return "milestones"
    }

}

