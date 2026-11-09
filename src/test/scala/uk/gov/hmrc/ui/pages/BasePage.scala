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

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebElement}
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.selenium.component.PageObject
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.ui.driver.BrowserDriver

import java.time.Duration
import scala.jdk.CollectionConverters.*

trait BasePage extends PageObject with Eventually with Matchers with LazyLogging with BrowserDriver {

  implicit def w: WebDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30))

  logger.info {
    s"Instantiating Browser: ${sys.props.getOrElse("browser", "'browser' System property not set. This is required")}"
  }

  def navigateTo(url: String): Unit =
    Driver.instance.navigate().to(url)

  def getElement(locator: By): WebElement =
    w.until(ExpectedConditions.visibilityOfElementLocated(locator))

  def getTitleText(locator: By): String =
    getElement(locator).getText.trim

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
    val element = w.until(ExpectedConditions.elementToBeClickable(locator))
    element.clear()
  }

  override def click(locator: By): Unit = {
    val element = w.until(ExpectedConditions.elementToBeClickable(locator))
    element.click()
  }

}
