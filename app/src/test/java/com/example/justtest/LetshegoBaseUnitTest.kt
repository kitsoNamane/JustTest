package com.example.justtest

import com.example.config.AppiumAndroidConfig
import org.junit.After
import org.junit.Before

/// You can only override classes and functions with the [open] keyword.
open class LetshegoBaseUnitTest {
    @Before
    open fun setup() {
        // wait for splash screen and transition to login screen
        // Screen Recording Does not work on emulators running Android API level < 27
        // AppiumAndroidConfig.driver.startRecordingScreen()
        // TODO: Enable show touches and slow down animations
        AppiumAndroidConfig.await(3)
    }

    @After
    open fun tearDown() {
        //AppiumAndroidConfig.driver.startRecordingScreen()
        //AppiumAndroidConfig.await(5)
        //Thread.sleep(5000)
        AppiumAndroidConfig.driver.resetApp()
    }
}
