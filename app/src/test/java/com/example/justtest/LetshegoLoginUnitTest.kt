package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginUnitTest : LetshegoBaseUnitTest() {
    @Test fun loginWithIncorrectEmailWorkflow() {
        // we expect [onboardingActivity] to be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        inputEmailAndPassword(User.wrongEmail, User.password)
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun loginWithIncorrectPasswordWorkflow() {
        // we expect [onboardingActivity] to be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        inputEmailAndPassword(User.email, User.wrongPassword)
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun loginWorkflow() {
        // we expect [onboardingActivity] to not be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        inputEmailAndPassword(User.email, User.password)
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()

        Assert.assertNotEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
        // Report: We have no way of cancelling login workflow if no internet connection
        // We can also check internet connectivity before going into the login screen. That way we
        // can provide our users with immediate feedback and request their action to remedy
        // internet connection if possible.
    }

    @Test fun cancelLogin() {
        gotoLogin()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        val cancelButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/close"))
        cancelButton.click()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun resetPasswordWorkflow() {
        goToPasswordReset()
        // Test Email Field
        val emailPlaceholder: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/textinput_placeholder"))
        emailPlaceholder.click()
        val emailField: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/et_email_id"))
        emailField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(User.email)
        Assert.assertEquals(emailField.text, User.email)

        // We need to hide the keyboard to review other views to appium driver.
        AppiumAndroidConfig.driver.hideKeyboard()


        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        // Test reset password workflow.
        val resetPasswordButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/fab_proceed"))
        //val backButton: MobileElement = AppiumAndroidConfig
        //    .driver.findElement(By.id("com.letshego.dasdigital:id/tv_back"))
        resetPasswordButton.click()
        Assert.assertNotEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
        // Report: There is no progress indicator when reset button is clicked, the back button
        // should also be implemented slightly differently, e.g no account, sign up.
        // It's the same case in the login view, the back button should be, "no account, sign up".
        // It should also be the same case in the sign up view, e.g "already signed up, log in".
    }

    @Test fun cancelPasswordReset() {
        goToPasswordReset()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        val cancelPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/close"))
        cancelPasswordResetButton.click()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    private fun gotoLogin() {
        val toLoginButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
        toLoginButton.click()
        println(AppiumAndroidConfig.driver.currentActivity().toString())
    }

    private fun inputEmailAndPassword(email: String, password: String) {
        // go to login screen
        // TODO: Test if successful
        gotoLogin()

        // Test Email field
        val emailField: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/et_email_id"))
        emailField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(email)
        Assert.assertEquals(emailField.text, email)

        // Test Password Field
        val passwordField: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/et_password"))
        passwordField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(password)
        Assert.assertEquals(passwordField.text, password)

        // Hide the keyboard so we can reveal the login button to appium
        AppiumAndroidConfig.driver.hideKeyboard()
        val loginButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/fab_proceed"))

        // Test login process
        // only run if device connected to internet
        if(AppiumAndroidConfig.driver.connection.isWiFiEnabled) {
            loginButton.click()
            AppiumAndroidConfig.await(4)
        }
    }

    private fun goToPasswordReset() {
        gotoLogin()
        val toPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_reset_password"))
        toPasswordResetButton.click()
    }
}