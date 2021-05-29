package com.example.justtest

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.util.concurrent.TimeUnit

object AppiumAndroidConfig {
    var driver: AndroidDriver<AndroidElement>

    init {
        val url: URL = URL("http://127.0.0.1:4723/wd/hub")
        val capabilities: DesiredCapabilities = DesiredCapabilities()
        capabilities.setCapability("deviceName", "TestDevice")
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(CapabilityType.VERSION, "8.0")
        capabilities.setCapability("appPackage", "com.letshego.dasdigital")
        capabilities.setCapability("appActivity", "com.letshego.dasdigital.android.splash.SplashActivity")
        driver = AndroidDriver<AndroidElement>(url, capabilities)
    }

    fun await(timeDelay: Long = 3) {
        AppiumAndroidConfig.driver?.let {
            it.manage()?.timeouts()?.implicitlyWait(timeDelay, TimeUnit.SECONDS)
        }
    }
}