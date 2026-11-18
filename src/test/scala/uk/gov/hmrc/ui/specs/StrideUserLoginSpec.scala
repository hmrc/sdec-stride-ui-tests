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
import org.scalatest.prop.TableDrivenPropertyChecks.forAll
import org.scalatest.prop.Tables.Table
import uk.gov.hmrc.ui.pages.AuthLoginPage.{assertOnPage, navigateToPage}
import uk.gov.hmrc.ui.pages.*
class StrideUserLoginSpec extends BaseSpec {

  private val pid       = "12345"
  private val givenName = "TestGivenName"
  private val surName   = "TestSurNamer"
  private val email     = "test@example.com"

  override def beforeEach(): Unit = {
    super.beforeEach()
    navigateToPage(StrideUserLoginPage)
  }

  private def givenStrideUserEntersIdentityDetails(): Unit = {
    Given("Stride user is on 'Stride Identity Provider Login Page'")
    // assertOnPage(StrideUserLoginPage)

    And(s"Stride user enters valid identity details where name is $givenName $surName ($email)")
    StrideUserLoginPage.enterIdentityDetails(pid, givenName, surName, email)
  }

  Feature("Workspace access based on authenticated SDEC stride user's role") {

    val happyPathData = Table(
      ("scenario", "roles"),
      (
        s"Success : Stride user $givenName successfully navigates navigate to workspace with a single valid role that starts with SDEC_",
        "SDEC_CHB_Manager"
      ),
      (
        s"Success : Stride user $givenName successfully navigates navigate to workspace with a multiple valid role that starts with SDEC_",
        "SDEC_CHB_Manager,SDEC_VAT_User,SDEC_Auit_Senior"
      ),
      (
        s"Success : Stride user $givenName successfully navigates navigate to workspace with a VAT user role that starts with SDEC_",
        "sdec_vat_user"
      )
    )

    forAll(happyPathData) { (scenario, roles) =>
      Scenario(scenario) {
        givenStrideUserEntersIdentityDetails()

        When(s"Stride user has a valid role $roles starting with SDEC_")
        StrideUserLoginPage.enterRole(roles)

        Then(s"Stride user successfully navigates to workspace page")
        StrideUserLoginPage.clickSubmitButton()
        assertOnPage(WorkspacePage)
      }
    }

    val negativePathData = Table(
      ("scenario", "roles"),
      (
        s"Failure : Stride user $givenName navigates to Insufficient Role page due to role with hyphen delimiter",
        "CHB_Manager"
      ),
      (
        s"Failure : Stride user $givenName navigates to Insufficient Role page due to role exceeding expected segments",
        "SDEC_VAT_User_Manager"
      ),
      (
        s"Failure : Stride user $givenName navigates to Insufficient Role page due to invalid prefix",
        "sdec1_CHB_caseworker"
      ),
      (
        s"Failure : Stride user $givenName navigates to Insufficient Role page due to missing team code segment",
        "sdec_manager"
      )
    )

    forAll(negativePathData) { (scenario, roles) =>
      Scenario(scenario) {
        givenStrideUserEntersIdentityDetails()

        When(s"user $givenName has a valid role $roles not starting with SDEC_")
        StrideUserLoginPage.enterRole(roles)

        Then("Stride user navigates to Insufficient Role page")
        StrideUserLoginPage.clickSubmitButton()
        assertOnPage(InsufficientRolePage)
        InsufficientRolePage.verifyUnauthorisedMessage("You do not have a role defined for access to this service")
      }
    }
  }
}
