package com.example.config

import io.appium.java_client.MobileElement
import io.appium.java_client.PerformsTouchActions
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.touch.offset.PointOption
import org.junit.Assert
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
            it.manage()?.timeouts()?.implicitlyWait(5, TimeUnit.SECONDS)
        }
    }

    fun findElementByIdOrNull(elementId: String): MobileElement? {
        return try {
            driver.findElementById(elementId)
        } catch (e: Exception) {
            null
        }
    }

    fun gotoLogin() {
        //val toLoginButton: MobileElement = driver.findElementById("com.letshego.dasdigital:id/tv_login")
        //toLoginButton.click()
        val toLoginButton = findElementByIdOrNull("com.letshego.dasdigital:id/tv_login")
        toLoginButton?.click()
        await(5)
    }

    fun closeApp() {
       driver.closeApp()
    }

    fun startApp() {
        driver.activateApp(bundleId)
    }

    fun login(email: String, password: String, timeOut: Long = 5) {
        // Test Email field
        //val emailField: MobileElement = driver
        //    .findElementById("com.letshego.dasdigital:id/et_email_id")
        //emailField.click()
        val emailField = findElementByIdOrNull("com.letshego.dasdigital:id/et_email_id")

        // We want to skip assertion if emailField is null.
        emailField?.click()?.also {
            driver.keyboard.pressKey(email)
            Assert.assertEquals(emailField.text, email)
        }

        // Test Password Field
        val passwordField: MobileElement = driver
            .findElementById("com.letshego.dasdigital:id/et_password")
        passwordField.click()
        driver.keyboard.pressKey(password)
        Assert.assertEquals(passwordField.text, password)

        // Hide the keyboard so we can reveal the login button to appium
        driver.hideKeyboard()
        //val loginButton: MobileElement = driver
        //    .findElementById("com.letshego.dasdigital:id/fab_proceed")

        // Test login process
        // only run if device connected to internet
        //loginButton.click()
        //await(timeOut)
    }

    fun enterText(fieldId: String, text: String, dismissKeyboard: Boolean = true) {
        val field: MobileElement = driver.findElementById(fieldId)
        field.click()
        field.clear()
        driver.keyboard.pressKey(text)
        if (dismissKeyboard) {
            driver.hideKeyboard()
        }
    }

    fun touchAction(x: Int, y: Int) {
        PlatformTouchAction(driver)
            .tap(PointOption.point(x, y))
            .release()
            .perform()
    }


    // [await] gives me time for asynchronous event to finish loading
    fun await(timeOut: Long = 5) {
        driver.let {
            it.manage()?.timeouts()?.implicitlyWait(timeOut, TimeUnit.SECONDS)
        }
        try {
           driver.findElementById("com.letshego.dasdigital:id/testing_block")
        } catch (e: Exception) {
        }
        // Reset back to default
        driver.let {
            it.manage()?.timeouts()?.implicitlyWait(5, TimeUnit.SECONDS)
        }
   }

    class PlatformTouchAction(performsTouchActions: PerformsTouchActions) :
        TouchAction<PlatformTouchAction>(performsTouchActions)
}