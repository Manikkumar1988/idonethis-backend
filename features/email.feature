#encoding: utf-8

Feature: Email

  Scenario: Reminder
    When I request for send reminder
    Then reminder mail should be send to all members