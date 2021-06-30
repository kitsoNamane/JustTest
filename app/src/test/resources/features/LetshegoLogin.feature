<<<<<<< HEAD
@Login
=======
>>>>>>> hotfix
Feature: Login
  Scenario: User Login
    Given I'm on the Onboarding screen
    And I input email and password
    When I press the login button
    Then I should end up at the Dashboard screen

  Scenario: User Log Out
    Given I'm on the profile screen
    And I press the logout button
    And I press confirm logout button
    Then I should end up at the Onboarding screen

  Scenario: Invalid User Login
    Given Invalid user is on the Onboarding screen
    And Invalid user inputs invalid email and password
    When Invalid user presses the login button
    Then I should still be on Onboarding screen
