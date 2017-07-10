package com.wesstrong.Camunda3

import com.wesstrong.codegen.queryCaseDefMilestones
import com.wesstrong.codegen.queryProcessDefinitions
import com.wesstrong.codegen.showFormVariables
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication



@SpringBootApplication
@EnableProcessApplication //requires processes.xml in META-INF or won't find processes in resources
class Camunda3Application



fun main(args: Array<String>) {
    SpringApplication.run(Camunda3Application::class.java, *args)
    queryProcessDefinitions()
    queryCaseDefMilestones()
    showFormVariables()





}

