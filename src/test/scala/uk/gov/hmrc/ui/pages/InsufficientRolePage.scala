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

object InsufficientRolePage extends BrowserDriver with BasePage with StaticUrl with StaticTitle {

  override val pageUrl:   String = s"${TestConfiguration.url("sdec-stride-frontend")}/insufficient-roles"
  override val pageTitle: String =
    "Secure Document Exchange - sdec-stride-frontend - GOV.UK"

  private val errorMessageLocator: By = By.cssSelector("p.govuk-body")

  def verifyUnauthorisedMessage(expectedText: String): Unit = {
    assertTextOnPage(errorMessageLocator, expectedText)
  }

}
