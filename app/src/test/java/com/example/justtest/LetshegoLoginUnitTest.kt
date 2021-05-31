package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By
import java.lang.Exception

class LetshegoLoginUnitTest : LetshegoBaseUnitTest() {
    @Test fun loginWithIncorrectEmailWorkflow() {
        // we expect [onboardingActivity] to be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        AppiumAndroidConfig.gotoLogin()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        AppiumAndroidConfig.login(User.wrongEmail, User.password)
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun loginWithIncorrectPasswordWorkflow() {
        // we expect [onboardingActivity] to be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        AppiumAndroidConfig.gotoLogin()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        AppiumAndroidConfig.login(User.email, User.wrongPassword)
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun loginWorkflow() {
        // we expect [onboardingActivity] to not be the same with
        // [AppiumAdroidConfig.driver.currentActivity()].
        AppiumAndroidConfig.gotoLogin()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        AppiumAndroidConfig.login(User.email, User.password)

        Assert.assertNotEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
        // Report: We have no way of cancelling login workflow if no internet connection
        // We can also check internet connectivity before going into the login screen. That way we
        // can provide our users with immediate feedback and request their action to remedy
        // internet connection if possible.
    }

    @Test fun cancelLogin() {
        AppiumAndroidConfig.gotoLogin()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        val cancelButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/close")
        cancelButton.click()
        AppiumAndroidConfig.await()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun resetPasswordWorkflow() {
        goToPasswordReset()
        // Test Email Field
        val emailPlaceholder: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/textinput_placeholder")
        emailPlaceholder.click()
        val emailField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/et_email_id")
        emailField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(User.email)
        Assert.assertEquals(emailField.text, User.email)

        // We need to hide the keyboard to review other views to appium driver.
        AppiumAndroidConfig.driver.hideKeyboard()


        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        // Test reset password workflow.
        val resetPasswordButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/fab_proceed")
        //val backButton: MobileElement = AppiumAndroidConfig
        //    .driver.findElement(By.id("com.letshego.dasdigital:id/tv_back")
        resetPasswordButton.click()

        AppiumAndroidConfig.await()
        // TODO: Come up with a strategy to test password reset workflow, refer below.
        // You only had 3 maximum resets, fail for now. We'll find out how to test for password
        // reset properly.
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
        // Report: There is no progress indicator when reset button is clicked, the back button
        // should also be implemented slightly differently, e.g no account, sign up.
        // It's the same case in the login view, the back button should be, "no account, sign up".
        // It should also be the same case in the sign up view, e.g "already signed up, log in".
    }

    @Test fun appLoginPersistance() {
        try {
           AppiumAndroidConfig.gotoLogin()
        } catch (e: Exception) {
            // Only go to login on first launch
        }
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        AppiumAndroidConfig.login(User.email, User.password)
        println("closing app...")
        AppiumAndroidConfig.closeApp()
        AppiumAndroidConfig.await()
        println("openning app...")
        AppiumAndroidConfig.startApp()
        AppiumAndroidConfig.await()
        Assert.assertNotEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }


    @Test fun cancelPasswordReset() {
        goToPasswordReset()
        val onboardingActivity = AppiumAndroidConfig.driver.currentActivity()
        val cancelPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/close")
        cancelPasswordResetButton.click()
        AppiumAndroidConfig.await()
        Assert.assertEquals(onboardingActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    private fun goToPasswordReset() {
        AppiumAndroidConfig.gotoLogin()
        val toPasswordResetButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/tv_reset_password")
        toPasswordResetButton.click()
    }
}