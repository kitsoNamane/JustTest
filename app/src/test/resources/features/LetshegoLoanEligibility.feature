@Eligibility
Feature: Loan Eligibility
  Scenario: User Loan Eligibility
    Given I'm on the Dashboard screen
    And I have not calculated my loan eligibility
    Then I click check eligibility button
    And I choose employment type
    And I select name of employer
    And I input my monthly salary
    When I press check qualification button
    Then I should get my loan eligibility status