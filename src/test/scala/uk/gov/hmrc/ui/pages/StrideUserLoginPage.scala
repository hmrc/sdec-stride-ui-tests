/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.{By, WebDriver}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.conf.TestConfiguration
import uk.gov.hmrc.ui.driver.BrowserDriver

object StrideUserLoginPage extends BrowserDriver with BasePage with StaticUrl with StaticTitle {

  override val pageTitle: String = "Stride IdP Login"
  override val pageUrl:   String = TestConfiguration.url("stride-idp-stub/auth-request")

  given WebDriver = Driver.instance

  val pidTextBox:                       By = By.xpath("//input[@id='pid']")
  val giveNameTextBox:                  By = By.xpath("//input[@id='usersGivenName']")
  val surNameTextBox:                   By = By.xpath("//input[@id='usersSurname']")
  val emailAddressTextBox:              By = By.xpath("//input[@id='emailAddress']")
  val successAuthenticationRadioButton: By = By.xpath("//input[@id='success'][@name='status']")
  val failureAuthenticationRadioButton: By = By.xpath("//input[@id='failure'][@name='status']")
  val validSAMLSignatureRadioButton:    By = By.xpath("//input[@id='valid'][@name='signature']")
  val noneSAMLSignatureRadioButton:     By = By.xpath("//input[@id='off'][@name='signature']")
  val roleTextButton:                   By = By.xpath("//textarea[@id='roles'][@name='roles']")
  val submitButton:                     By = By.xpath("//button[@type='submit'][@id='continue-button']")

  def clickSubmitButton(): Unit =
    clickElement(submitButton)

  def enterRole(role: String): Unit =
    clearField(roleTextButton)
    enterText(roleTextButton, role)

  def enterIdentityDetails(
    pid:       String,
    givenName: String,
    surName:   String,
    email:     String
  ): Unit = {
    clearField(pidTextBox)
    enterText(pidTextBox, pid)
    clearField(giveNameTextBox)
    enterText(giveNameTextBox, givenName)
    clearField(surNameTextBox)
    enterText(surNameTextBox, surName)
    clearField(emailAddressTextBox)
    enterText(emailAddressTextBox, email)
  }
}
