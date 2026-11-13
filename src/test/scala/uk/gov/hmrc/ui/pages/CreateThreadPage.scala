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
import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.ui.driver.BrowserDriver

object CreateThreadPage extends BrowserDriver with BasePage {

  val createThreadButtonLocator:            By = By.cssSelector("a.govuk-button[href*=\"create-thread\"]")
  val workspaceHeadingLocator:              By = By.cssSelector("h1.govuk-heading-xl")
  val recipientDetailsHeadingLocator:       By = By.cssSelector("#main-content h1")
  val continueButtonLocator:                By = By.cssSelector("#main-content a")
  val whatDoYouWantToDoHeadingLocator:      By = By.cssSelector("main#main-content h1.govuk-heading-l")
  val errorWhenNoRadioButtonClickedLocator: By = By.cssSelector(".govuk-error-summary a")
  val submitButtonLocator:                  By = By.cssSelector("button.govuk-button[type=\"submit\"]")
  val sendFilesLocator:                     By = By.id("threadType")
  val reqFilesLocator:                      By = By.id("threadType_1")
  val sendAndReqFilesLocator:               By = By.id("threadType_2")
  val backLinkLocator:                      By = By.cssSelector(".govuk-back-link")

  def getCreateThreadButton:                    WebElement = getElement(createThreadButtonLocator)
  def getWorkspaceHeading:                      String     = getText(workspaceHeadingLocator)
  def isCreateThreadButtonDisplayed:            Boolean    = isElementDisplayed(createThreadButtonLocator)
  def isCreateThreadButtonEnabled:              Boolean    = isElementEnabled(createThreadButtonLocator)
  def getCreateThreadButtonText:                String     = getText(createThreadButtonLocator)
  def clickCreateThreadButton():                Unit       = click(createThreadButtonLocator)
  def getRecipientDetailsHeadingText:           String     = getText(recipientDetailsHeadingLocator)
  def clickContinueButton():                    Unit       = click(continueButtonLocator)
  def clickSendFilesRadioButton():              Unit       = clickRadioButton(sendFilesLocator)
  def clickReqFilesRadioButton():               Unit       = clickRadioButton(reqFilesLocator)
  def clickSendAndReqFilesRadioButton():        Unit       = clickRadioButton(sendAndReqFilesLocator)
  def getWhatDoYouWantToDoHeading:              String     = getText(whatDoYouWantToDoHeadingLocator)
  def getErrorResponseWhenNoRadioButtonClicked: String     = getText(errorWhenNoRadioButtonClickedLocator)
  def clickSubmitButton():                      Unit       = click(submitButtonLocator)
  def clickBackLink():                          Unit       = click(backLinkLocator)
}
