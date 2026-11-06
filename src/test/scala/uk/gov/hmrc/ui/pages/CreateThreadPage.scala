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

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.{By, JavascriptExecutor, WebElement}
import uk.gov.hmrc.ui.driver.BrowserDriver

import scala.jdk.CollectionConverters.*

object CreateThreadPage extends BrowserDriver with BasePage {

  val createThreadButtonLocator: By = By.cssSelector("a.govuk-button[role=\"button\"]")
  val workspaceHeadingLocator:   By = By.cssSelector("h1.govuk-heading-xl")

  def getCreateThreadButton: WebElement =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getWorkspaceHeading: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(workspaceHeadingLocator)).getText.trim

  def isCreateThreadButtonDisplayed: Boolean =
    driver.findElements(createThreadButtonLocator).asScala.nonEmpty &&
      getCreateThreadButton.isDisplayed

  def isCreateThreadButtonEnabled: Boolean =
    getCreateThreadButton.isEnabled

  def getThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

}
