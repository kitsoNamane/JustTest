package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginUnitTest : LetshegoBaseUnitTest {
    @Test fun loginWorkflow() {
       // go to login screen
       // TODO: Test if successful
       val toLoginButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
       toLoginButton.click()
       println(AppiumAndroidConfig.driver.currentActivity().toString())

       // Test Email field
       val emailField: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/et_email_id"))
       emailField.click()
       AppiumAndroidConfig.driver.keyboard.pressKey(User.email)
       Assert.assertEquals(emailField.text, User.email)

       // Test Password Field
       val passwordField: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/et_password"))
       passwordField.click()
       AppiumAndroidConfig.driver.keyboard.pressKey(User.password)
       Assert.assertEquals(passwordField.text, User.password)

       // Hide the keyboard so we can reveal the login button to appium
       AppiumAndroidConfig.driver.hideKeyboard()
       val loginButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/fab_proceed"))


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
}