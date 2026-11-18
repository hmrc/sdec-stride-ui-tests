/*
 * Copyright 2023 HM Revenue & Customs
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

import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, Wait}
import org.openqa.selenium.{By, JavascriptExecutor, WebDriver, WebElement}
import org.scalatest.matchers.must.Matchers.mustBe
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.driver.BrowserDriver
import uk.gov.hmrc.ui.pages.StrideUserLoginPage.driver

import java.time.Duration
import scala.jdk.CollectionConverters.*

trait BasePage extends PageObject with BrowserDriver {

  override implicit def driver: WebDriver = Driver.instance

  def fluentWait: Wait[WebDriver] = new FluentWait[WebDriver](Driver.instance)
    .withTimeout(Duration.ofSeconds(5))
    .pollingEvery(Duration.ofSeconds(1))

  def navigateTo(url: String): Unit =
    Driver.instance.navigate().to(url)

  def getElement(locator: By): WebElement =
    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator))

  def isElementDisplayed(locator: By): Boolean =
    driver.findElements(locator).asScala.nonEmpty &&
      getElement(locator).isDisplayed

  def isElementEnabled(locator: By): Boolean =
    driver.findElements(locator).asScala.nonEmpty &&
      getElement(locator).isEnabled

  def assertLocatorPresent(locator: By): Unit = {
    val elements = Driver.instance.findElements(locator).asScala
    require(
      elements.nonEmpty,
      s"Expected element with locator [$locator] to be present, but none was found"
    )
  }

  def clear(locator: By): Unit = {
    val element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator))
    element.clear()
  }

  override def click(locator: By): Unit = {
    val element = fluentWait.until(ExpectedConditions.elementToBeClickable(locator))
    element.click()
  }

  /** Radio button interaction */

  def clickRadioButton(locator: By): Unit = {
    val radioElement = fluentWait.until(
      ExpectedConditions.presenceOfElementLocated(locator)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioElement)

    try
      radioElement.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", radioElement)
    }

  }

  def clickElement(locator: By): Unit =
    getElementIfClickable(locator).click()

  private def getElementIfClickable(locator: By): WebElement =
    fluentWait.until(ExpectedConditions.elementToBeClickable(locator))

  private def getElementIfVisible(locator: By): WebElement =
    fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator))

  def clearField(locator: By): Unit =
    getElementIfVisible(locator).clear()

  def assertOnPage(page: BasePage with StaticUrl with StaticTitle): Unit =
    assertOnPage(page, None)

  def enterText(locator: By, value: String)(implicit driver: WebDriver): Unit = {
    val element = driver.findElement(locator)
    element.clear()
    element.sendKeys(value)
  }

  private def assertOnPage(expectedPageUrl: String, expectedTitle: String): Unit = {
    assertUrl(expectedPageUrl)
    assertPageTitle(expectedTitle)
  }

  def assertUrl(url: String): Unit =
    fluentWait.until(ExpectedConditions.urlToBe(url))

  def assertPageTitle(expectedTitle: String): Unit =
    fluentWait.until(ExpectedConditions.titleIs(expectedTitle))

  def assertTextOnPage(locator: By, text: String): Unit =
    getElementIfVisible(locator).getText mustBe text

  def assertTextOnPage(element: WebElement, text: String): Unit =
    element.getText mustBe text

  private def assertOnPage(page: BasePage with StaticUrl with StaticTitle, titleOverride: Option[String]): Unit = {
    val expectedTitle = titleOverride.getOrElse(page.pageTitle)
    assertOnPage(page, expectedTitle)
  }

  def assertOnPage(page: BasePage with StaticUrl, expectedTitle: String): Unit =
    assertOnPage(page.pageUrl, expectedTitle)

  def navigateToPage(page: BasePage & StaticUrl): Unit =
    driver.manage().deleteAllCookies()
    Driver.instance.get(page.pageUrl)

}
