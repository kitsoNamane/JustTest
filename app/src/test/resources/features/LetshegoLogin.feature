Feature: Login
  Scenario: User Login
    Given I go to the Onboarding screen
    And I input email and password
    When I press the login button
    Then I should end up at the Dashboard screen

  Scenario: Invalid User Login
    Given Invalid user goes to the Onboarding screen
    And Invalid user inputs invalid email and password
    When Invalid user presses the login button
    Then I should still be on Onboarding screen
