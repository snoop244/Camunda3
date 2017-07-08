package com.wesstrong.Camunda3

/**
 * Created by SBN on 02/07/2017.
 */
//REACTIVE OPTION
//TODO Using Execution Listener, trap activity start, check if form definition is in cache, if not, compose form elements
//TODO Consider very granular user task nodes, but wire them together into more dense forms, according to the default flow, until a gateway is hit (full segment)
//TODO possibly allowing dynamic elements to be inserted if the non-default flow is triggered.
//TODO consider creating a Thymeleaf dialect for these optional compositions: http://www.thymeleaf.org/doc/tutorials/3.0/extendingthymeleaf.html
//this gist shows Kotlin and Thymeleaf: https://gist.github.com/lynas/aebd2885e700bde2cdc20b3e976c8966
//TODO consider Camunda Reactor: https://github.com/camunda/camunda-bpm-reactor to trigger a form-builder service or, more specifically
//TODO https://github.com/camunda/camunda-bpm-reactor/tree/master/examples/bpmn-task-listener
//for an awesome description of REACTIVE PROGRAMMING, read this: https://gist.github.com/lynas/9cb834a74e057e53a40c0d31ca04f022

//VAADIN KOTLIN DSL OPTION
//Has nice bindings to repositories in VoK (uses JPA), so probably have to do code generation rather than render dynamic forms
//In Camunda Case, Use Milestone concept with naming convention with two aspects, "Object" and, where there is a meaningful state
// stopping point (usually a stage): "Object_State".  Example: The case overall would have "Claims" and the FNOL stage would have
// "Claims_FNOL".  Alternatively, Extension properties could be used to model data relationships.
//From Camunda, get form key and use it in Vaadin Navigator
//form example of the DSL (by the guy that does VoK, but not VoK): https://github.com/mvysny/karibu-dsl/blob/master/example-v8/src/main/kotlin/com/github/vok/karibudsl/example/form/FormView.kt
//A Camunda JSF version: https://github.com/camunda/camunda-bpm-examples/tree/master/bpmn-model-api/generate-jsf-form
//for the model api: https://github.com/camunda/camunda-bpm-examples/tree/master/bpmn-model-api/parse-bpmn
//Vaadin + Kotlin + SpringBoot scaffolding: code: https://github.com/phauer/blog-related/tree/master/kotlin-spring-boot-vaadin-scaffolding article: https://kotlin.link/articles/Kotlin-in-Practice-with-Spring-Boot-and-Vaadin.html

//CODE GENERATION
//Freemarker: here is a d-zone article on creating a webservice with Freemarker: https://dzone.com/articles/automated-webservice-code-generation-using-freemar
//A good-seeming book on Freemarker: https://www.safaribooksonline.com/library/view/instant-freemarker-starter/9781782163824/ch01s03.html#ch01lvl2sec07
//Another good Freemarker + Spring Boot tutorial: http://zetcode.com/articles/springbootfreemarker/
//Thymeleaf text generator: http://blog.codeleak.pl/2017/03/getting-started-with-thymeleaf-3-text.html
//Nicely structured Kotlin + Spring + Thymeleaf: article:https://turreta.com/spring-mvc-with-spring-boot-kotlin-and-thymeleaf/   code: https://github.com/Turreta/Spring-MVC-with-Spring-Boot-Kotlin-and-Thymeleaf
//Maybe use Kotlin directly or Kotlin + Gradle
//Here is a good DOM/XML parsing example: http://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
//!!KotlinPoet from Square generates Kotlin!! https://github.com/square/kotlinpoet

/*Proposed IDL for code generation
* Entity Creation and Persistence Wiring
*   (Scans this file, below, for Case Milestones, starting with Parent, creates data classes, persistence, ideally to Mongo AND to event stream)
*   (MANDATORY: Case must have at least one Milestone with the same name e.g. Claim case must have Claim milestone - called a Case Milestone)
*   (Case Milestone holds only tombstone data and, perhaps, listens for stage start and completion sentries so the CRUD interface can provide NBAs, etc.)
*   (MANDATORY: Case Stage must have at least one Milestone with the same name e.g. Claim_FNOL.  It has a reference to the claim's ID.)
*   (Includes form fields that might need to be persisted to multiple stores and stream topics)
*   (So... Milestones are data entities AND event stream and reactive topics)
*
*
* Form Creation
* Case: ParentCaseName (Sets parent entity; used for CRUD)
*   Form: CreateCaseForm (BPMN flow to set up the Parent Case)
*       FormField: FormKey (from Camunda form key)
*           FieldName: FieldType (Defaults to Milestone/Persistence to which the BPMN flow is wired via a sentry)
*               OtherEntity: EntityName (OPTIONAL - In addition to the wired milestone, can be also saved to another entity)
*           FieldName: FieldType (...and so on)
*
*
*
*
*
*
* */