package com.example.cucumber

import io.cucumber.java8.En
import io.cucumber.java8.PendingException

class BeerCansSteps : En {
    init {
        Given(
            "I have {int} beer cans"
        ) { int1: Int? -> throw PendingException() }
        Given(
            "I have drunk {int} beer cans"
        ) { int1: Int? -> throw PendingException() }

        When("I go to my fridge"
        ) {
            throw PendingException()
        }
        Then(
            "I should have {int} beer cans"
        ) { int1: Int? -> throw PendingException() }

    }
}