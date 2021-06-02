package com.example.steps

import com.example.justtest.LoginWorkFlow
import io.cucumber.java8.En
import io.cucumber.java8.PendingException

class LetshegoLoanEligibilitySteps : En {
    init {
        Given("I log in successfully") {
            LoginWorkFlow.aggregateLoginSteps()
        }

        Given("I'm on the Dashboard screen") {
            throw PendingException()
        }
        And("I have not calculated my loan eligibility") {
            throw PendingException()
        }
        Then("I click check eligibility button") {
            throw PendingException()
        }
        And("I choose employment type") {
            throw PendingException()
        }
        And("I select name of employer") {
            throw PendingException()
        }
        And("I input my monthly salary") {
            throw PendingException()
        }
        When("I press check qualification button") {
            throw PendingException()
        }
        Then("I should get my loan eligibility status") {
            throw PendingException()
        }
    }
}