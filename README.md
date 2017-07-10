# Camunda Case Webflux

Uses Camunda Model API to pull in CMMN definition.

##Entities
1. Create domain entity from a CMMN Milestone
2. Initialize a Mongo repository and Kafka Topic for each Milestone/Entity.  Parent Entity will likely be kept minimal and only hold data generated outside of the stages (for the most part)
2. IF Milestone name = name of case or name of stage, then a view is created for the parent entity item only.  Child entities are rendered in the parent's view, even if filtered.  
For example: "Claim" as the parent forms the view; "FNOL" has its own repository as an entity, but is viewed in the Claim view.

##Views
1. For 
