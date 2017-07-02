package com.wesstrong.Camunda3

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStoppedEvent;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.FormService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.ProcessEngines
import org.camunda.bpm.engine.repository.ProcessDefinition



@SpringBootApplication
@EnableProcessApplication //requires processes.xml in META-INF or won't find processes in resources
class Camunda3Application


fun queryProcessDefinitions(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val repositoryService: RepositoryService? = processEngine?.repositoryService  //usually first service to be called and used per: https://docs.camunda.org/manual/7.6/user-guide/process-engine/process-engine-api/
    val processDefinitions = repositoryService!!.createProcessDefinitionQuery()  //fluent query
            //.processDefinitionKey("invoice")
            .orderByProcessDefinitionVersion()
            .asc()
            .list()
    System.out.printf("from process definition query: {$processDefinitions")
    val processDefinition = repositoryService.getProcessDefinition("Process_1:1:3") //TODO returns processDefinitionEntity, figure out how to get variables
    System.out.printf("process definition for Process_1:1:3 {$processDefinition}")
    val processModel = repositoryService.getProcessModel("Process_1:1:3")
    System.out.printf("process model for Process_1:1:3 {$processModel}")
}

fun startProcessInstance(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val runtimeService: RuntimeService? = processEngine?.runtimeService  //docs say the RepositoryService deploys to the engine... I guess that is true if the processes aren't auto-deployed
    runtimeService?.createProcessInstanceById("Process_1:1:3") //need to create and then start process instance
    runtimeService?.startProcessInstanceById("Process_1:1:3")
}


fun showFormVariables(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val formService: FormService? = processEngine?.formService
    val formVariables = formService?.getTaskFormVariables("7")
    System.out.printf("form variables for task 7: {$formVariables}")

}

fun queryTasks(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val taskService: TaskService? = processEngine?.taskService
    taskService!!.setAssignee("7","demo") //works!!
    val tasks = taskService.createTaskQuery()  //fluent approach for querying tasks
            .taskAssignee("demo")
            .orderByDueDate().asc()
            .list()  //produces [Task{7]]
    System.out.printf("these are tasks for user demo: {$tasks}")

}

fun main(args: Array<String>) {
    SpringApplication.run(Camunda3Application::class.java, *args)
    queryProcessDefinitions()
    startProcessInstance()
    showFormVariables()
    queryTasks()




}

