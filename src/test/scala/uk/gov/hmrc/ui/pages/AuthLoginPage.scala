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
import uk.gov.hmrc.ui.conf.TestConfiguration
import uk.gov.hmrc.ui.driver.BrowserDriver

object AuthLoginPage extends BrowserDriver with BasePage {

  val url: String = TestConfiguration.url("sdec-stride-frontend")

  val pidLocator:                       By = By.id("pid")
  val givenNameLocator:                 By = By.id("usersGivenName")
  val surNameLocator:                   By = By.id("usersSurname")
  val emailAddressLocator:              By = By.id("emailAddress")
  val successAuthenticationRadioButton: By = By.id("success")
  val failureAuthenticationRadioButton: By = By.id("failure")
  val validSAMLSignatureRadioButton:    By = By.id("valid")
  val noneSAMLSignatureRadioButton:     By = By.id("off")
  val roleLocator:                      By = By.id("roles")
  val submitButtonLocator:              By = By.id("continue-button")

  def navigateToStrideAuthPage(): Unit = navigateTo(url)
  def enterStrideLoginDetails(pid: String, givenName: String, surName: String, email: String, roles: String): Unit = {
    sendKeys(pidLocator, pid)
    sendKeys(givenNameLocator, givenName)
    sendKeys(surNameLocator, surName)
    sendKeys(emailAddressLocator, email)
    sendKeys(roleLocator, roles)
  }
  def clickSubmitButton(): Unit = click(submitButtonLocator)

}
