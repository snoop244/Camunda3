package com.wesstrong.Camunda3


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.task.Task
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableProcessApplication
class Camunda3Application

fun main(args: Array<String>) {
    SpringApplication.run(Camunda3Application::class.java, *args)

  //  val mytask: Task =

  //  val taskid: int = mytask.id()
}
