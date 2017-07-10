# Camunda Case Webflux

Uses Camunda Model API to pull in CMMN definition.

##Entities
1. Create domain entity for each CMMN Milestone.  The primary entities are the case-named entity and its children, designated with the "in" keyword.
2. Initialize a Mongo repository and Kafka (sub)Topic for each Milestone/Entity.  Parent Entity will likely be kept minimal and only hold data generated outside of the stages (for the most part)
3. Reserved word "in" results in an child entity whose records can be directly accessed from the parent view (e.g. "Claim in FNOL" suggests that the claim is in the FNOL stage)
4. Other entities that do not use "in" are shown as related items in core entity views (e.g. Staffing would be related to the Claim entity, not a child of it.)

##Views
###CRUD View
1. A View per non-child entity 
2. Primary entity (e.g. "Case") shows as top level 
3. Secondary entities (e.g. "Staffing" and "Messaging") are usually accessed as related items to the primary entity or via a "cross-cutting" primary view.
###Detail View
- Summary, Details and Messaging view tabs are always present
- Details show the last selected item from the Summary view
- Details allows drill-down and uses bread crumbs for navigation back up (or jumpt to Summary tab to open a new Details view)
- Recommended, available and open processes are just links in the Summary and Detail views
###Process View
- TBD

##Composing Views from CMMN File
1. First this TBD
2. Then this TBD


##Task Rendering Engine
- Tasks should be rendered dynamically.
- The Task Rendering Engine should create items at the Form Field level, not at the task level
- Forms can be rendered at multiple levels of resolution:
    - Form field at a time (e.g. for Conversational Flow)
    - Task at a time
    - All tasks in the Default flow until the next gateway 