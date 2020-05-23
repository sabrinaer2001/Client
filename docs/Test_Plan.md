Testing Document and Specification: Test Reports

Version 3.0

Riccardo Montresor 23/05/2020

17918@studenti.marconiverona.edu.it

**Testing Document and Specification**

Test Reports

Chat\_Client Team 4

CS 451, WS 2019

| Bachir Karim | Valentin Toth | Montresor Riccardo |
| --- | --- | --- |
| Senapathige Sahas | Sabrina Er Quoioi |
 |

**Introduction**

This document outlines the outcome of completed system tests. Incidents, Defects and Changes that need to be made will be presented here formally. Although the ideas expressed here are separate entities, they will be combined into this one document.

**Incidents**

This section defines the incidents discovered while performing various tests on the system. This section will expand as more incidents are found. For each incident, the initial Incident ID (excluding Letter) determines the test case that it ties to. For example, Incident ID 5.4.6.4-B is an incident stemming from test case 5.4.6.4.

| Incident ID | 4.1.4.2-A |
| --- | --- |
| Description | Error detected is on the mange of packet 05 &quot;change of Alias |
| Originator | Sahas Senapathige -Tester |
| Discover Date | November 20, 2019 |
| Severity | Low |
| Steps Required to Produce Incident | 1. Once we sign in the chat 2. when we try to change our alias the chat shutdown |
| Responder | Bachir Karim -Developer |
| Current Status | Open |
| Cause | Incident was caused by the manage of the packet in fact when we try to changes the chat can&#39;t take our Id to modifyour alias |
| Resolution | TBD |
| Addressed Date | November 23,2019 |
| Creation Phase | Implementation |
| Detection Phase | Testing |
| Correction Time | TBD |

**Defects**

At this time, defects that were found were labeled as incidents. Many of what we consider defects were self made defects that were hard to find but easy to correct. Incidents were found that had the opportunity of turning into defects in the system had they not been able to be corrected.

**Summary**

The testing procedure to date has been conducted using manual system testing and concurrent unit testing. Features tested include but are not limited to:

- Ability for an individual to select a category and read questions/answers pertinent to that category

Test Case: 4.1.4.1, 4.1.4.2

- Ability for a user to login via username and password and submit a survey

Test Case: 4.1.4.2

- Ability to add/remove users of the system (back-end) as needed

Test case: 4.1.4.2, 4.1.4.2

Further system testing will be done as newly implemented features become available. These features include all the functionality of the Administration Page which entails adding and removing users. To date, one incident has been found and is currently undergoing corrective measures. This incident was determined by a defect from the code more properly on the communication between the chat and the server.

Page 4 of 4