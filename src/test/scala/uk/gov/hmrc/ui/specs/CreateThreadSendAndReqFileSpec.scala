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
import org.scalatest.prop.TableDrivenPropertyChecks
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CreateThreadPage, RecipientDetailsPage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests
import uk.gov.hmrc.ui.testData.StrideUserLoginData

class CreateThreadSendAndReqFileSpec extends BaseSpec with TableDrivenPropertyChecks {
  Feature("Internal User Journey - Create New Thread with Send and Request file Journey") {

    Scenario("The Stride User Navigates to Workspace Page for creating new thread", AcceptanceTests) {
      forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
        Given("Child Benefits User navigates to Stride Authentication Page with the credentials ")
        AuthLoginPage.navigateToStrideAuthPage()
        AuthLoginPage.enterStrideLoginDetails(pid, givenName, surName, email, roles)
        AuthLoginPage.clickSubmitButton()

        When("""the User views Workspace Navigation page a "Create thread" button is displayed""")
        CreateThreadPage.getWorkspaceHeading         shouldBe "Workspace"
        CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
        CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

        And("the User clicks on the create thread button it should navigate to the Recipient Details page")
        CreateThreadPage.clickCreateThreadButton()
        CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

        Then("the User clicks on continue button and navigates to next page")
        RecipientDetailsPage.clickContinueButton()

      }
    }

    Scenario("The user navigates to What do you want to do page  with Send & Request Files selected", AcceptanceTests) {

      forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
        Given("Child Benefits User navigates to Stride Authentication Page with the credentials ")
        AuthLoginPage.navigateToStrideAuthPage()
        AuthLoginPage.enterStrideLoginDetails(pid, givenName, surName, email, roles)
        AuthLoginPage.clickSubmitButton()

        And("the User views Workspace Navigation page a Create thread button is displayed")
        CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
        CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

        And("the button must be selectable")
        CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
        CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

        When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
        CreateThreadPage.clickCreateThreadButton()
        CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

        Then("the User clicks on continue button and navigates to What do you want page ")
        RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "hmrc@test.com", "07700900982", "AA123456B")
        RecipientDetailsPage.selectNoForExistingCase()
        RecipientDetailsPage.clickContinueButton()
        CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

        And("the user clicks on continue button but does not navigate to the next page")
        CreateThreadPage.clickSubmitButton()
      }
    }

  }

}
