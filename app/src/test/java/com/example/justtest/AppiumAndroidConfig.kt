package com.example.justtest

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.lang.Exception
import java.net.URL
import java.util.concurrent.TimeUnit

/// [AppiumAndroidConfig] here implements the Singleton Pattern.
object AppiumAndroidConfig {
    var driver: AndroidDriver<AndroidElement>
    val bundleId = "com.letshego.dasdigital"

    init {
        val url = URL("http://127.0.0.1:4723/wd/hub")
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("deviceName", "TestDevice")
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(CapabilityType.VERSION, "8.0")
        capabilities.setCapability("appPackage", bundleId)
        capabilities.setCapability("appActivity", "com.letshego.dasdigital.android.splash.SplashActivity")
        capabilities.setCapability("noReset", true)
        driver = AndroidDriver<AndroidElement>(url, capabilities)

        driver.let {
            it.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        }
    }

    fun gotoLogin() {
        val toLoginButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/tv_login")
        toLoginButton.click()
        await(3)
    }

    fun closeApp() {
       driver.closeApp()
    }

    fun startApp() {
        driver.activateApp(bundleId)
    }

    fun login(email: String, password: String, timeOut: Long = 10) {
        // Test Email field
        val emailField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/et_email_id")
        emailField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(email)
        Assert.assertEquals(emailField.text, email)

        // Test Password Field
        val passwordField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/et_password")
        passwordField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(password)
        Assert.assertEquals(passwordField.text, password)

        // Hide the keyboard so we can reveal the login button to appium
        AppiumAndroidConfig.driver.hideKeyboard()
        val loginButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/fab_proceed")

        // Test login process
        // only run if device connected to internet
        loginButton.click()
        await(timeOut)
    }


    // [await] gives me time for asynchronous event to finish loading
    fun await(timeOut: Long = 10) {
        driver.let {
            it.manage()?.timeouts()?.implicitlyWait(timeOut, TimeUnit.SECONDS)
        }
        try {
           driver.findElementById("com.letshego.dasdigital:id/testing_block")
        } catch (e: Exception) {
            println("I'm just waiting for the output")
        }
        // Reset back to default
        driver.let {
            it.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        }
    }
}