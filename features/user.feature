#encoding: utf-8

Feature: User

  Scenario: Register
    When I register using valid username and password
    Then I should be able to login with same username and password