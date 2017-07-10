package com.wesstrong.codegen

import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.ProcessEngines
import org.camunda.bpm.engine.RepositoryService
import org.camunda.bpm.model.cmmn.Cmmn
import org.camunda.bpm.model.cmmn.CmmnModelInstance
import org.camunda.bpm.model.cmmn.instance.Milestone

/**
 * Created by Stephen on 2017-07-10.
 */


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