package com.example.cucumber

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:features/BeerCans.feature"],
    glue = ["com.example.cucumber"],
    plugin = ["pretty"])
class RunCucumberTest
