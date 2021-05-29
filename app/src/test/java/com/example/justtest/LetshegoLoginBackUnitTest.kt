package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By

class LetshegoLoginBackUnitTest : LetshegoBaseUnitTest {
    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun goBack() {
       val toLoginButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/tv_login"))
       toLoginButton.click()
       println(AppiumAndroidConfig.driver.currentActivity().toString())
       val backButton: MobileElement = AppiumAndroidConfig
           .driver.findElement(By.id("com.letshego.dasdigital:id/tv_sign_up"))
       backButton.click()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }
}