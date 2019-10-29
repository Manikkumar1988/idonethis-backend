#encoding: utf-8

Feature: User

  Scenario: Login
    When I login valid username and password
    Then I should be able to add a to_do item