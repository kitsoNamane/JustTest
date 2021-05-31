package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class LetshegoSignUpUnitTest : LetshegoBaseUnitTest() {
    private fun gotoSignUp() {
        val toSignupButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/fab_sign_up")
        toSignupButton.click()
    }
    
    @Test fun registrationWorkflow() {
        gotoSignUp()
        println(AppiumAndroidConfig.driver.currentActivity().toString())

        // Test Verify Disabled
        val verifyButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/fab_verify")
        Assert.assertEquals(verifyButton.isEnabled, false)

        val currentActivity = AppiumAndroidConfig.driver.currentActivity()
        // Test Phone Field
        val phoneField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/et_phone_num")
        phoneField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(User.phone)
        Assert.assertEquals(phoneField.text, User.phone)
        Assert.assertEquals(verifyButton.isEnabled, false)
        AppiumAndroidConfig.driver.hideKeyboard()

        // Test Existing Loan Field
        val existingLoanField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/cb_existing_loan")
        existingLoanField.click()
        Assert.assertEquals(existingLoanField.isEnabled, true)
        Assert.assertEquals(verifyButton.isEnabled, false)

        // Test Term & Condition Field
        val termsAndConditionsField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/cb_terms_condition")
        termsAndConditionsField.click()
        Assert.assertEquals(termsAndConditionsField.isEnabled, true)
        Assert.assertEquals(verifyButton.isEnabled, true)

        // Test verification process
        verifyButton.click()

        AppiumAndroidConfig.await()
        // TODO: Find another way to test since you'll still be on the Onboarding activity
        Assert.assertEquals(currentActivity, AppiumAndroidConfig.driver.currentActivity())
    }

    @Test fun cancelSignUp() {
        gotoSignUp()
        val currentActivity = AppiumAndroidConfig.driver.currentActivity()
        val cancelButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/imv_close")
        cancelButton.click()

        AppiumAndroidConfig.await()
        Assert.assertEquals(currentActivity, AppiumAndroidConfig.driver.currentActivity())
    }
}