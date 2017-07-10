package com.wesstrong.codegen

import org.camunda.bpm.engine.FormService
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngines
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.model.bpmn.Bpmn
import org.camunda.bpm.model.bpmn.BpmnModelInstance
import org.camunda.bpm.model.bpmn.instance.UserTask

/**
 * Created by Stephen on 2017-07-10.
 */


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


fun showFormVariables(){
    val processEngine: ProcessEngine? = ProcessEngines.getProcessEngine("default")
    val formService: FormService? = processEngine?.formService
    val formVariables = formService?.getTaskFormVariables("13")
    System.out.printf("form variables for task 13: {$formVariables}")

}