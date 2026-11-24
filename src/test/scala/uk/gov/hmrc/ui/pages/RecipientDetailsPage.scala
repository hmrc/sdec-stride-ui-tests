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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

object RecipientDetailsPage extends BasePage {

  val continueButtonLocator:      By = By.cssSelector("#main-content button")
  val firstNameTextBoxLocator:    By = By.id("firstName")
  val lastNameTextBoxLocator:     By = By.id("lastName")
  val emailTextBoxLocator:        By = By.id("email")
  val mobileNumberTextBoxLocator: By = By.id("phoneNumber")
  val niNumberTextBoxLocator:     By = By.id("nationalInsuranceNumber")
  val existingCaseYesLocator:     By = By.id("hasRelatedCase")
  val existingCaseNoLocator:      By = By.id("hasRelatedCase-no")
  val firstNameErrorLocator:      By = By.id("firstName-error")
  val lastNameErrorLocator:       By = By.id("lastName-error")
  val emailErrorLocator:          By = By.id("email-error")
  val mobileNumberErrorLocator:   By = By.id("phoneNumber-error")
  val niNumberErrorLocator:       By = By.id("nationalInsuranceNumber-error")

  def clickContinueButton(): Unit = click(continueButtonLocator)

  def addText(locator: By, value: String): Unit = {
    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTextBoxLocator))
    sendKeys(firstNameTextBoxLocator, value)
  }

  def enterRecipientDetails(
    firstName:    String,
    lastName:     String,
    email:        String,
    mobileNumber: String,
    niNumber:     String
  ): Unit = {
    enterText(firstNameTextBoxLocator, firstName)
    enterText(lastNameTextBoxLocator, lastName)
    enterText(emailTextBoxLocator, email)
    enterText(mobileNumberTextBoxLocator, mobileNumber)
    enterText(niNumberTextBoxLocator, niNumber)
  }

  def selectYesForExistingCase():  Unit   = selectRadioButton(existingCaseYesLocator)
  def selectNoForExistingCase():   Unit   = selectRadioButton(existingCaseNoLocator)
  def getFirstNameErrorMessage:    String = getText(firstNameErrorLocator)
  def getLastNameErrorMessage:     String = getText(lastNameErrorLocator)
  def getEmailErrorMessage:        String = getText(emailErrorLocator)
  def getMobileNumberErrorMessage: String = getText(mobileNumberErrorLocator)
  def getNINumberErrorMessage:     String = getText(niNumberErrorLocator)

}
