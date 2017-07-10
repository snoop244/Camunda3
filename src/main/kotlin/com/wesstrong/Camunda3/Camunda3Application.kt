package com.wesstrong.Camunda3

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.camunda.bpm.engine.HistoryService
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.history.HistoricProcessInstance
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStoppedEvent
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties
import org.camunda.bpm.engine.TaskService
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.engine.FormService
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.ProcessEngines
import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.instance.Documentation
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.ServiceTask
import org.camunda.bpm.model.bpmn.instance.UserTask
import org.camunda.bpm.model.cmmn.Cmmn
import org.camunda.bpm.model.cmmn.CmmnModelInstance
import org.camunda.bpm.model.cmmn.instance.Milestone
import org.camunda.bpm.model.xml.test.AbstractModelElementInstanceTest.modelInstance


@SpringBootApplication
@EnableProcessApplication //requires processes.xml in META-INF or won't find processes in resources
class Camunda3Application


//See: https://docs.camunda.org/manual/7.7/user-guide/process-engine/process-engine-concepts/
fun queryProcessDefinitions(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val repositoryService: RepositoryService? = processEngine?.repositoryService  //usually first service to be called and used per: https://docs.camunda.org/manual/7.6/user-guide/process-engine/process-engine-api/
    val processDefinitions = repositoryService!!.createProcessDefinitionQuery()  //fluent query
            //.processDefinitionKey("invoice")
            .orderByProcessDefinitionVersion()
            .asc()
            .list()
    System.out.printf("from process definition query: {$processDefinitions")
    val processDefinition = repositoryService.getProcessDefinition("Process_1:1:6") //TODO returns processDefinitionEntity, figure out how to get variables
    System.out.printf("process definition for Process_1:1:6 {$processDefinition}")
    val processModel = repositoryService.getProcessModel("Process_1:1:6")
    System.out.printf("process model for Process_1:1:3 {$processModel}")
    val modelInstance: BpmnModelInstance = Bpmn.readModelFromStream(processModel)
    System.out.printf("model from stream is: ${modelInstance}")
    findProcessElementByType(modelInstance)
}

fun findProcessElementByType(modelInstance: BpmnModelInstance){
    val userTasks = modelInstance.getModelElementsByType(UserTask::class.java)
    userTasks.forEach {
        System.out.printf("a user task named: ${it?.name} and ID: ${it?.id} with form key: ${it?.camundaFormKey} ")
    }
}


fun queryCaseDefMilestones() : Collection<Milestone>{
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val repositoryService: RepositoryService? = processEngine?.repositoryService  //usually first service to be called and used per: https://docs.camunda.org/manual/7.6/user-guide/process-engine/process-engine-api/
    val caseModel = repositoryService?.getCaseModel("Claim_Case_1:1:9")
    System.out.printf("process model for Claim_Case_1:1:9 {$caseModel}")
    val caseModelInstance: CmmnModelInstance = Cmmn.readModelFromStream(caseModel)
    System.out.printf("case model from stream is: $caseModelInstance")
    val milestones = caseModelInstance.getModelElementsByType(Milestone::class.java)
    milestones.forEach {
        System.out.printf("a milestone named: ${it?.name} and ID: ${it?.id} ")
    }
    return milestones

}

//TODO clean up above two functions

fun showFormVariables(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val formService: FormService? = processEngine?.formService
    val formVariables = formService?.getTaskFormVariables("13")
    System.out.printf("form variables for task 13: {$formVariables}")

}


fun main(args: Array<String>) {
    SpringApplication.run(Camunda3Application::class.java, *args)
    queryProcessDefinitions()
    queryCaseDefMilestones()
    showFormVariables()





}

