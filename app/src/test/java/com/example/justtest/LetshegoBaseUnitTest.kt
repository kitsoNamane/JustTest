package com.example.justtest

import org.junit.After
import org.junit.Before

interface LetshegoBaseUnitTest {
    @Before
    fun setup() {
       // wait for splash screen and transition to login screen
       AppiumAndroidConfig.await(3)
    }

    @After
    fun tearDown() {
      AppiumAndroidConfig.driver.resetApp()
    }
}