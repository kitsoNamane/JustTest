package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginResetPasswordUnitTest : LetshegoBaseUnitTest {
    @Test
    fun gotoPasswordReset() {
        val toLoginButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
        toLoginButton.click()
        println(AppiumAndroidConfig.driver.currentActivity().toString())
        val toPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/tv_reset_password"))
        toPasswordResetButton.click()
    }
}