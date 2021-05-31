package com.example.justtest

import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.junit.Assert
import org.junit.Test
import org.openqa.selenium.By
import java.time.Duration


class LetshegoDashBoardUnitTest : LetshegoBaseUnitTest() {

    @Test fun navigationTab() {
        AppiumAndroidConfig.gotoLogin()
        AppiumAndroidConfig.login(User.email, User.password)
        val homeNavigationButton = AppiumAndroidConfig
            .driver
            .findElementById(
                    "com.letshego.dasdigital:id/navigation_dashboard"
            )
        val exploreNavigationButton = AppiumAndroidConfig
            .driver
            .findElementById(
                    "com.letshego.dasdigital:id/navigation_explore"
            )


        val profileNavigationButton = AppiumAndroidConfig
            .driver
            .findElementById(
                    "com.letshego.dasdigital:id/navigation_profile"
            )

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
        AppiumAndroidConfig.gotoLogin()
        AppiumAndroidConfig.login(User.email, User.password)

        val borrowAmmountField: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/et_amount_seekbar")

        val borrowAmmountScroller = AppiumAndroidConfig
            .driver
            .findElementByXPath(
            "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.SeekBar"
            )

        val checkEligibilityButton: MobileElement = AppiumAndroidConfig
            .driver.findElementById("com.letshego.dasdigital:id/tv_check_eligibility")

        // scroll down
        AppiumAndroidConfig.driver
            .findElementByAndroidUIAutomator(
                "UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(UiSelector().resourceId(\"ddddd\").instance(0))"
            )
    }
}