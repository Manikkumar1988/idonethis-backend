#encoding: utf-8

Feature: Team

  Scenario: Add a member
    When I add a team member with mail id
    Then I should be able to see the added mail id in the full team list