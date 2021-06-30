package com.example.justtest

import com.example.config.AppiumAndroidConfig
import io.appium.java_client.MobileElement
import org.junit.Assert

object DashBoardWorkFlow {
    fun validateIfOnDashboard() {
        val requiredActivityName = "dashboard"
        val isOnRequiredScreen = AppiumAndroidConfig.driver
            .currentActivity()
            .lowercase().contains(requiredActivityName)
        Assert.assertTrue(isOnRequiredScreen)
    }

    fun checkIfEligibilityAlreadyCalculated() {
        val isEligibilityCalculated = AppiumAndroidConfig
            .findElementByIdOrNull("com.letshego.dasdigital:id/tv_secured") != null
        Assert.assertTrue(isEligibilityCalculated)
    }

    fun pressCheckEligibilityButton() {
        if(AppiumAndroidConfig.findElementByIdOrNull("com.letshego.dasdigital:id/tv_secured") != null) {
            AppiumAndroidConfig.driver
                .findElementById("com.letshego.dasdigital:id/tv_recalculate_eligibility")
                .click()
        } else {
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/tv_check_eligibility")
                .click()
        }
    }

    fun chooseEmploymentType() {
        AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/constraint_question_1")
            .click()

        AppiumAndroidConfig.await(1)

        val employmentTypeList =  listOf<MobileElement>(
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/radio_employed_ft"),
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/radio_employed_pt"),
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/radio_self_employed"),
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/radio_unemployed"),
            AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/radio_retired"),
        )

        // TODO: randomize selection
        employmentTypeList[1].click()
    }

    fun selectEmployer() {

    }

    fun inputMonthlySalary() {}

    fun pressCheckQualificationButton() {}

    fun checkLoanEligibilityStatus() {}
}