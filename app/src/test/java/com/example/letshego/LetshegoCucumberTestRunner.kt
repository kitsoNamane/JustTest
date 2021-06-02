package com.example.letshego

import com.example.config.AppiumAndroidConfig
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.After
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features/LetshegoLogin.feature",
        "classpath:features/LetshegoLoanEligibility.feaure"],
    glue = ["com.example.letshego"],
    plugin = ["pretty"])
class LetshegoCucumberTestRunner {
}