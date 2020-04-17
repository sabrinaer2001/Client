# Test Case Specification

# For

# Team 4

April 14, 2020

Prepared by:

Er raqioui Sabrina

# **Table of Contents**

**1 Introduction 4**

**2 Test Cases: iOS Application 4**

**3 Test Cases: Send message 6**

Revision History

| **Version** | **Date** | **Name** | **Description** |
| --- | --- | --- | --- |
| 1 | 18/10/19 | Team 4 | Starting with interface part |
| 2 | 25/10/19 | Team 4 | Updated test case numbers to eliminate codex errors. |
| 3 | 04/12/19 | Team 4 | Updated test case numbers to update the interface. |

# 1Introduction

This document provides the test cases to be carried out for the Messaging Application. Each item to be tested is represented by an individual test case. Each case details the input and expected outputs.

# 2Test Cases: iOS Application

| Test ID | 2.1 |
| --- | --- |
| Title | Correct Login |
| Feature | Login to use the application |
| Objective | Create an user that can use the app |
| Setup | IOS device or simulator has Roo Balance application ready to run. |
| Test Data | Login informationUser name= untente1 |
| Test Actions | 1. Start the application2. opening the interface3. Enter with an unique name |
| Expected Results | System displays show you the interface to use to communicate ith other users |

| Test ID | 2.2 |
| --- | --- |
| Title | Unique name not checked |
| Feature | Name must be unique otherwise you could speak on behalf of another user |
| Objective | Give a minium of security |
| Setup | Application ready to run. |
| Test Data | Login informationUser name= untente1 |
| Test Actions | 1. Start the applicatio2. Opening the interface2. Enter with an unique name3. Check with the use of a list if the name has already been used |
| Expected Results | System displays error message with option to try again if the name are used |

# 3Test Cases: Send Message

| Test ID | 3.1 |
| --- | --- |
| Title | Send message |
| Feature | Send message on a chat group |
| Objective | Send message on a chat group correctly |
| Setup | Sthrough the interface I have to send a message to the chat group |
| Test Data | &quot;ciao&quot; - message want to send |
| Test Actions | 1. Run the application2. Enter correct login and write a message3. Press send |
| Expected Results | The message is sent is that it is actually sent than can see it from other users |

| Test ID | 3.2 |
| --- | --- |
| Title | Send message |
| Feature | Send message on a chat group |
| Objective | Send message on a chat group correctly |
| Setup | Sthrough the interface I have to send a message to the chat group |
| Test Data | &quot;ciao&quot; - message want to send |
| Test Actions | 1. Run the application2. Enter correct login and write a message3. Press send |
| Expected Results | The message is sent is that it is actually sent than can see it from other users, the problem was an incorrect package creation wich consequently could not be sent |