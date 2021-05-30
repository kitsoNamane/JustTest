package com.example.justtest

import org.junit.After
import org.junit.Before

open class LetshegoBaseUnitTest {
    @Before
    fun setup() {
       // wait for splash screen and transition to login screen
       // Screen Recording Does not work on emulators running Android API level < 27
       // AppiumAndroidConfig.driver.startRecordingScreen()
       // TODO: Enable show touches and slow down animations
       AppiumAndroidConfig.await(3)
    }

    @After
    fun tearDown() {
      //AppiumAndroidConfig.driver.startRecordingScreen()
      AppiumAndroidConfig.driver.resetApp()
    }
}