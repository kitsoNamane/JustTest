package com.example.letshego

import com.example.config.AppiumAndroidConfig
import com.example.testdata.User
import io.appium.java_client.MobileElement
import io.cucumber.java8.En
import io.cucumber.java8.PendingException
import org.junit.Assert

class LetshegoLoginSteps : En {
    init {
        Given("I go to the Onboarding screen") {
            AppiumAndroidConfig.await(3)
            AppiumAndroidConfig.gotoLogin()
            // Test for some login related strings
            val loginText = AppiumAndroidConfig.findElementByIdOrNull(
                "com.letshego.dasdigital:id/lb_desc"
            )
            Assert.assertNotNull(loginText)
        }
        And("I input email and password") {
            AppiumAndroidConfig.login(User.email, User.password)
            //AppiumAndroidConfig.await(3)

        }
        When("I press the login button") {
            val firstLoginButton = AppiumAndroidConfig
                .findElementByIdOrNull("com.letshego.dasdigital:id/fab_proceed")
            val secondLoginButton = AppiumAndroidConfig
                .findElementByIdOrNull("com.letshego.dasdigital:id/fab_sign_in")

            // Test login process
            // only run if device connected to internet
            firstLoginButton?.click()
            secondLoginButton?.click()
        }
        Then("I should end up at the Dashboard screen") {
            AppiumAndroidConfig.await(3)
            val requiredActivityName = "dashboard"
            val isOnRequiredScreen = AppiumAndroidConfig.driver
                .currentActivity()
                .lowercase().contains(requiredActivityName)
           Assert.assertTrue(isOnRequiredScreen)
        }

    }
}