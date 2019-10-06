#encoding: utf-8

Feature: Hello World

  Scenario: Sample Endpoint
    When I hit the API
    Then hello world should return
