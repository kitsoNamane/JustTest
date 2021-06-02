@Login
Feature: Login
  Scenario: User Login
    Given I go to the Onboarding screen
    And I input email and password
    When I press the login button
    Then I should end up at the Dashboard screen
    #Examples:
    #  | email | password | activity |
    #  | kitsontest@yopmail.com | 122345678abc | Dashboard |