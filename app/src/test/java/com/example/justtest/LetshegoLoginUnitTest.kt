package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginUnitTest : LetshegoBaseUnitTest() {
    @Test fun loginWorkflow() {
       // go to login screen
       // TODO: Test if successful
       gotoLogin()

       // Test Email field
       val emailField: MobileElement = AppiumAndroidConfig
           .driver.findElement(By.id("com.letshego.dasdigital:id/et_email_id"))
       emailField.click()
       AppiumAndroidConfig.driver.keyboard.pressKey(User.email)
       Assert.assertEquals(emailField.text, User.email)

       // Test Password Field
       val passwordField: MobileElement = AppiumAndroidConfig
           .driver.findElement(By.id("com.letshego.dasdigital:id/et_password"))
       passwordField.click()
       AppiumAndroidConfig.driver.keyboard.pressKey(User.password)
       Assert.assertEquals(passwordField.text, User.password)

       // Hide the keyboard so we can reveal the login button to appium
       AppiumAndroidConfig.driver.hideKeyboard()
       val loginButton: MobileElement = AppiumAndroidConfig
           .driver.findElement(By.id("com.letshego.dasdigital:id/fab_proceed"))

        // only run if device connected to internet
        if(AppiumAndroidConfig.driver.connection.isWiFiEnabled) {
            loginButton.click()
            AppiumAndroidConfig.await(4)
        }
       // TODO: check if successful
       // Report: We have no way of cancelling login workflow if no internet connection
       // We can also check internet connectivity before going into the login screen. That way we
       // can provide our users with immediate feedback and request their action to remedy
       // internet connection if possible.
    }

    @Test fun cancelLogin() {
        gotoLogin()
        val cancelButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/close"))
        cancelButton.click()
    }

    fun goToPasswordReset() {
        gotoLogin()
        val toPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_reset_password"))
        toPasswordResetButton.click()
    }

    @Test fun resetPasswordWorkflow() {
        goToPasswordReset()
        println("whereami"+AppiumAndroidConfig.driver.currentActivity().toString())
        val emailPlaceholder: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/textinput_placeholder"))
        emailPlaceholder.click()
        val emailField: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/et_email_id"))
        emailField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(User.email)
        Assert.assertEquals(emailField.text, User.email)
        AppiumAndroidConfig.driver.hideKeyboard()
        val resetPasswordButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/fab_proceed"))
        val backButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_back"))
        resetPasswordButton.click()
    }

    @Test fun cancelPasswordReset() {
        goToPasswordReset()
        val cancelPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/close"))
        cancelPasswordResetButton.click()
    }

    fun gotoLogin() {
        val toLoginButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
        toLoginButton.click()
        println(AppiumAndroidConfig.driver.currentActivity().toString())
    }
}