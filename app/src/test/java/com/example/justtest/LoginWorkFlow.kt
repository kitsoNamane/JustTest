package com.example.justtest

import com.example.config.AppiumAndroidConfig
import com.example.testdata.User
import org.junit.Assert

object LoginWorkFlow {

    fun goToProfileScreen() {
        val profileNavigationButton = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/navigation_profile")
        profileNavigationButton?.click()
        //AppiumAndroidConfig.await(3)

        val isOnprofileScreen = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/view_profile_btn") != null

        Assert.assertTrue(isOnprofileScreen)
    }

    fun presslogOutButton() {
        val logOutButton = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/tv_logout")
        logOutButton?.click()
        AppiumAndroidConfig.await(3)
    }

    fun confirmLogOut() {
        val confirmLogOutButton = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/fab_positive")
        confirmLogOutButton?.click()
    }

    fun validateLogOut() {
        AppiumAndroidConfig.await(5)
        val requiredActivityName = "onboarding"
        val isOnRequiredScreen = AppiumAndroidConfig.driver
            .currentActivity()
            .lowercase().contains(requiredActivityName)
        Assert.assertTrue(isOnRequiredScreen)
    }

    fun goToLoginScreen() {
        AppiumAndroidConfig.await(3)
        AppiumAndroidConfig.gotoLogin()
        // Test for some login related strings
        val loginText = AppiumAndroidConfig.waitToFindElementOrNull(
            "com.letshego.dasdigital:id/lb_desc"
        )
        Assert.assertNotNull(loginText)

    }

    fun inputEmailAndPassword() {
        AppiumAndroidConfig.login(User.email, User.password)
    }

    fun pressLoginButton() {
        val firstLoginButton = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/fab_proceed")
        val secondLoginButton = AppiumAndroidConfig
            .waitToFindElementOrNull("com.letshego.dasdigital:id/fab_sign_in")

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