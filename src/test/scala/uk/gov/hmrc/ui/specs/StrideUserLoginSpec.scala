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

package uk.gov.hmrc.ui.specs

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.prop.Tables.Table
import uk.gov.hmrc.ui.pages.{InsufficientRolePage, StaticTitle, StaticUrl, StrideUserLoginPage, WorkspacePage}
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import uk.gov.hmrc.ui.pages.AuthLoginPage.{assertOnPage, navigateToPage}

class StrideUserLoginSpec extends BaseSpec {

  private def givenStrideUserEntersValidIdentityDetails(
    pid:          String,
    givenName:    String,
    surName:      String,
    emailAddress: String
  ): Unit = {
    Given("Stride user is on 'Stride Identity Provider Login Page'")
    navigateToPage(StrideUserLoginPage)
    assertOnPage(StrideUserLoginPage)

    And("Stride user enters valid identity details")
    StrideUserLoginPage.enterIdentityDetails(pid, givenName, surName, emailAddress)
  }

  Feature("Workspace access based on authenticated SDEC stride user's role") {

    val happyPathData = Table(
      ("scenario", "pid", "givenName", "surName", "emailAddress", "roles"),
      (
        "Success : Stride user successfully navigate to workspace with a valid role that starts with SDEC",
        "12345",
        "Laura",
        "Taylor",
        "l.taylor@gmail.com",
        "SDEC_CHB_Manager"
      )
    )

    forAll(happyPathData) { (scenario, pid, givenName, surName, emailAddress, roles) =>
      Scenario(scenario) {

        givenStrideUserEntersValidIdentityDetails(pid, givenName, surName, emailAddress)

        When("Stride user has a valid role starts with SDEC_")
        StrideUserLoginPage.enterRole(roles)

        Then("Stride user successfully navigates to workspace page")
        StrideUserLoginPage.clickSubmitButton()
        // println("Current URL is: " + BrowserDriver.instance.getCurrentUrl)
        assertOnPage(WorkspacePage)
      }
    }

    val negativePathData = Table(
      ("scenario", "pid", "givenName", "surName", "emailAddress", "roles"),
      (
        "Failure : Stride user navigates to error page with an invalid role",
        "12345",
        "Laura",
        "Taylor",
        "l.taylor@gmail.com",
        "INVALID_ROLE"
      )
    )
    forAll(negativePathData) { (scenario, pid, givenName, surName, emailAddress, roles) =>
      Scenario(scenario) {

        givenStrideUserEntersValidIdentityDetails(pid, givenName, surName, emailAddress)

        When("Stride user has a valid role but not starting with SDEC_")
        StrideUserLoginPage.enterRole(roles)

        Then("Stride user navigates to Insufficient Role page")
        StrideUserLoginPage.clickSubmitButton()
        assertOnPage(InsufficientRolePage)
      }
    }
  }
}
