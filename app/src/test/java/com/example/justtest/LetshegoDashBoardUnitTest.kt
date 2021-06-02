package com.example.justtest

import com.example.config.AppiumAndroidConfig
import com.example.testdata.User
import io.appium.java_client.MobileElement
import org.junit.Test


class LetshegoDashBoardUnitTest : LetshegoBaseUnitTest() {
    @Test fun navigationTab() {
        AppiumAndroidConfig.gotoLogin()
        AppiumAndroidConfig.login(User.email, User.password)
        val homeNavigationButton = AppiumAndroidConfig.driver
            .findElementById("com.letshego.dasdigital:id/navigation_dashboard")

        val exploreNavigationButton = AppiumAndroidConfig.driver
            .findElementById("com.letshego.dasdigital:id/navigation_explore")

        val profileNavigationButton = AppiumAndroidConfig.driver
            .findElementById("com.letshego.dasdigital:id/navigation_profile")

        // got to explore
        exploreNavigationButton.click()
        AppiumAndroidConfig.await(3)
        // TODO: Test explore page

        // got to explore
        profileNavigationButton.click()
        AppiumAndroidConfig.await(3)
        // TODO: Test profile page

        // got to explore
        homeNavigationButton.click()
        AppiumAndroidConfig.await(3)
        // TODO: Test home page
    }

    @Test
    fun checkEligibility() {
        val getStartedButtonId = "tv_get_started"
        val infoCardId = "card_one"
        val recalculateEligibilityButton: MobileElement
        val checkEligibilityButton: MobileElement
        val borrowAmountField: MobileElement
        val borrowAmountScroller: MobileElement
        val checkQualificationButton: MobileElement?

        AppiumAndroidConfig.gotoLogin()
        AppiumAndroidConfig.login(User.email, User.password)

        if(AppiumAndroidConfig.findElementByIdOrNull("com.letshego.dasdigital:id/tv_secured") != null) {
            recalculateEligibilityButton = AppiumAndroidConfig.driver
                .findElementById("com.letshego.dasdigital:id/tv_recalculate_eligibility")
            recalculateEligibilityButton.click()
        } else {
            checkEligibilityButton = AppiumAndroidConfig
                .driver.findElementById("com.letshego.dasdigital:id/tv_check_eligibility")
            borrowAmountField = AppiumAndroidConfig
               .driver.findElementById("com.letshego.dasdigital:id/et_amount_seekbar")
            val borrowAmountScroller = AppiumAndroidConfig
                .driver
                .findElementByXPath(
                "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.SeekBar"
                )
            checkEligibilityButton.click()
        }

        val employmentTypeCard: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/constraint_question_1")

        val employerCard: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/constraint_question_2")

        val monthlySalaryCard: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/constraint_question_3")

         checkQualificationButton = AppiumAndroidConfig.findElementByIdOrNull("com.letshego.dasdigital:id/fab_check_eligibility")

        employmentTypeCard.click()
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

        // close the employment Type card
        //employmentTypeCard.click()

        employerCard.click()
        val employerField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/actvEmployer")
        employerField.click()
        employerField.clear()
        AppiumAndroidConfig.enterText("com.letshego.dasdigital:id/actvEmployer", "lets", false)

        AppiumAndroidConfig.touchAction(450, 720)
        AppiumAndroidConfig.driver.hideKeyboard()
        AppiumAndroidConfig.await(10)

        //println("employer: "+employerField.text)
        //AppiumAndroidConfig.await(5)

        monthlySalaryCard.click()
        //AppiumAndroidConfig.await(3)

        AppiumAndroidConfig.enterText("com.letshego.dasdigital:id/et_salary", "1000000")

        checkQualificationButton?.click()
        AppiumAndroidConfig.await(10)

        // scroll down
        //AppiumAndroidConfig.driver
        //    .findElementByAndroidUIAutomator(
        //        "UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(UiSelector().resourceId(\"$getStartedButtonId\").instance(0))"
        //    ).center

        //val getStartedButton: MobileElement = AppiumAndroidConfig
        //    .driver.findElementById("com.letshego.dasdigital:id/$getStartedButtonId")
        //AppiumAndroidConfig.await(1)

        //AppiumAndroidConfig.driver
        //    .findElementByAndroidUIAutomator(
        //        "UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(UiSelector().resourceId(\"$infoCardId\").instance(0))"
        //    )
        //val infoCard: MobileElement = AppiumAndroidConfig
        //    .driver.findElementById("com.letshego.dasdigital:id/$infoCardId")
    }
}