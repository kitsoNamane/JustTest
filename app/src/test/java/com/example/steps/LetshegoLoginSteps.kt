package com.example.steps

import android.util.Log
import com.example.config.AppiumAndroidConfig
import com.example.justtest.LoginWorkFlow
import com.example.testdata.User
import io.cucumber.java8.En
import org.junit.Assert

class LetshegoLoginSteps : En {
    init {
        Given("I'm on the Onboarding screen") {
            LoginWorkFlow.goToLoginScreen()
        }
        And("I input email and password") {
            LoginWorkFlow.inputEmailAndPassword()
        }
        When("I press the login button") {
            LoginWorkFlow.pressLoginButton()
        }
        Then("I should end up at the Dashboard screen") {
            LoginWorkFlow.validateLogin()
        }

        //! Logout user
        Given("I'm on the profile screen") {
            LoginWorkFlow.goToProfileScreen()
        }
        And("I press the logout button") {
            LoginWorkFlow.presslogOutButton()
        }
        And("I press confirm logout button") {
            LoginWorkFlow.confirmLogOut()
        }
        Then("I should end up at the Onboarding screen") {
            LoginWorkFlow.validateLogOut()
        }

        //! Invalid User Login
        Given("Invalid user is on the Onboarding screen") {
            AppiumAndroidConfig.await(3)
            AppiumAndroidConfig.gotoLogin()

            // Test for some login related strings
            val loginText = AppiumAndroidConfig.findElementByIdOrNull(
                "com.letshego.dasdigital:id/lb_desc"
            )
            Assert.assertNotNull(loginText)
        }
        And("Invalid user inputs invalid email and password") {
            AppiumAndroidConfig.login(User.wrongEmail, User.wrongPassword)
        }
        When("Invalid user presses the login button") {
            val firstLoginButton = AppiumAndroidConfig
                .findElementByIdOrNull("com.letshego.dasdigital:id/fab_proceed")
            val secondLoginButton = AppiumAndroidConfig
                .findElementByIdOrNull("com.letshego.dasdigital:id/fab_sign_in")

            // Test login process
            // only run if device connected to internet
            firstLoginButton?.click()
            secondLoginButton?.click()
        }
        Then("I should still be on Onboarding screen") {
            AppiumAndroidConfig.await(5)
            val requiredActivityName = "onboarding"
            val isOnRequiredScreen = AppiumAndroidConfig.driver
                .currentActivity()
                .lowercase().contains(requiredActivityName)
            Assert.assertTrue(isOnRequiredScreen)
        }


    }
}