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
import uk.gov.hmrc.ui.driver.BrowserDriver

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ThreadDetailsPage extends BrowserDriver with BasePage {

  val threadDetailPageTitleLocator:    By = By.cssSelector("h1.govuk-heading-l")
  val addMessageDetailsLocator:        By = By.id("message")
  val clickSubmitButtonLocator:        By = By.cssSelector("button.govuk-button[type='submit']")
  val remainingCharactersLeftLocator:  By = By.cssSelector("#message ~ .govuk-character-count__status")
  val overTheLimitCharLinkLocator:     By = By.xpath("//a[@href='#message' and contains(text(), 'Message must be')]")
  val overTheLimitCharMessageLocator:  By = By.cssSelector("p#message-error.govuk-error-message")
  val dayInputLocator:                 By = By.id("responseDate-responseDate.day")
  val monthInputLocator:               By = By.id("responseDate-responseDate.month")
  val yearInputLocator:                By = By.id("responseDate-responseDate.year")
  val errormessageResponseDateLocator: By = By.id("responseDate-error")

  def enterMessage(addMessage: String):                          Unit   = sendKeys(addMessageDetailsLocator, addMessage)
  def getThreadDetailsPage:                                      String = getText(threadDetailPageTitleLocator)
  def clickThreadDetailsButton():                                Unit   = click(clickSubmitButtonLocator)
  def getRemainingCharacterCountDisplayed:                       String = getText(remainingCharactersLeftLocator)
  def submitReplyDate(day: String, month: String, year: String): Unit   = {
    sendKeys(dayInputLocator, day)
    sendKeys(monthInputLocator, month)
    sendKeys(yearInputLocator, year)
  }

  def submitReplyDatePlus30Days(): Unit = {
    val targetDate = LocalDate.now().plusDays(30)

    val day   = targetDate.format(DateTimeFormatter.ofPattern("dd"))
    val month = targetDate.format(DateTimeFormatter.ofPattern("MM"))
    val year  = targetDate.format(DateTimeFormatter.ofPattern("yyyy"))
    sendKeys(dayInputLocator, day)
    sendKeys(monthInputLocator, month)
    sendKeys(yearInputLocator, year)
    submitReplyDate(day, month, year)
  }
}
