package com.example.justtest

import com.example.config.AppiumAndroidConfig
import com.example.testdata.User
import org.junit.Assert

object LoginWorkFlow {

    fun goToLoginScreen() {
        AppiumAndroidConfig.await(3)
        AppiumAndroidConfig.gotoLogin()
        // Test for some login related strings
        val loginText = AppiumAndroidConfig.findElementByIdOrNull(
            "com.letshego.dasdigital:id/lb_desc"
        )
        Assert.assertNotNull(loginText)
    }

    fun inputEmailAndPassword() {
        AppiumAndroidConfig.login(User.email, User.password)
    }

    fun pressLoginButton() {
        val firstLoginButton = AppiumAndroidConfig
            .findElementByIdOrNull("com.letshego.dasdigital:id/fab_proceed")
        val secondLoginButton = AppiumAndroidConfig
            .findElementByIdOrNull("com.letshego.dasdigital:id/fab_sign_in")

        // Test login process
        // only run if device connected to internet
        firstLoginButton?.click()
        secondLoginButton?.click()
    }

    fun validateLogin() {
        AppiumAndroidConfig.await(10)
        val requiredActivityName = "dashboard"
        val isOnRequiredScreen = AppiumAndroidConfig.driver
            .currentActivity()
            .lowercase().contains(requiredActivityName)
        Assert.assertTrue(isOnRequiredScreen)
    }

    fun aggregateLoginSteps() {
        goToLoginScreen()
        inputEmailAndPassword()
        pressLoginButton()
        validateLogin()
    }
}