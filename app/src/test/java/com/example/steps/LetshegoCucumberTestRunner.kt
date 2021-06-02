package com.example.steps

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features/LetshegoLogin.feature",
        "classpath:features/LetshegoLoanEligibility.feature"],
    glue = ["com.example.steps"],
    plugin = ["pretty"])
class LetshegoCucumberTestRunner
