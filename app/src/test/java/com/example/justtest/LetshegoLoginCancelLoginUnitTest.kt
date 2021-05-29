package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.After
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginCancelLoginUnitTest : LetshegoBaseUnitTest {
    @Test
    fun cancel() {
        val toLoginButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
        toLoginButton.click()
        println(AppiumAndroidConfig.driver.currentActivity().toString())
        val cancelButton: MobileElement = AppiumAndroidConfig
            .driver.findElement(By.id("com.letshego.dasdigital:id/close"))
        cancelButton.click()
    }

    @After
    override fun tearDown() {
        // don't do anything since this is always our starting point
    }
}