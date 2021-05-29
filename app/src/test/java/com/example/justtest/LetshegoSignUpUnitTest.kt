package com.example.justtest

import io.appium.java_client.MobileElement
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By

class LetshegoSignUpUnitTest : LetshegoBaseUnitTest {
    @Test fun registrationWorkflow() {
        val toSignupButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/fab_sign_up"))
        toSignupButton.click()
        //println(AppiumAndroidConfig.driver.currentUrl)
        println(AppiumAndroidConfig.driver.currentActivity().toString())

        // Test Verify Disabled
        val verifyButton: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/fab_verify"))
        Assert.assertEquals(verifyButton.isEnabled, false)

        // Test Phone Field
        val phoneField: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/et_phone_num"))
        phoneField.click()
        AppiumAndroidConfig.driver.keyboard.pressKey(User.phone)
        Assert.assertEquals(phoneField.text, User.formattedPhone)
        Assert.assertEquals(verifyButton.isEnabled, false)
        AppiumAndroidConfig.driver.hideKeyboard()

        // Test Existing Loan Field
        val existingLoanField: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/cb_existing_loan"))
        existingLoanField.click()
        Assert.assertEquals(existingLoanField.isEnabled, true)
        Assert.assertEquals(verifyButton.isEnabled, false)

        // Test Term & Condition Field
        val termsAndConditionsField: MobileElement = AppiumAndroidConfig.driver.findElement(By.id("com.letshego.dasdigital:id/cb_terms_condition"))
        termsAndConditionsField.click()
        Assert.assertEquals(termsAndConditionsField.isEnabled, true)
        Assert.assertEquals(verifyButton.isEnabled, true)

        verifyButton.click()
        AppiumAndroidConfig.await(3)
    }
}