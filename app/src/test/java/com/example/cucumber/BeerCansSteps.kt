package com.example.cucumber

import io.cucumber.java8.En
import io.cucumber.java8.PendingException

class BeerCansSteps : En {
    init {
        Given(
            "I have {int} beer cans"
        ) { int1: Int? -> println("I have: $int1 beer cans")}
        Given(
            "I have drunk {int} beer cans"
        ) { int1: Int? -> println("I have drunk $int1 beer cans")}

        When("I go to my fridge"
        ) {
            println("I go to my fridge")
        }
        Then(
            "I should have {int} beer cans"
        ) { int1: Int? -> println("I should have $int1 beer cans")}

    }
}