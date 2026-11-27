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
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CreateThreadPage, RecipientDetailsPage, ThreadDetailsPage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests
import uk.gov.hmrc.ui.testData.{RecipientDetailsData, StrideUserLoginData}

class CreateThreadReqOnlySpec extends BaseSpec with TableDrivenPropertyChecks {
  Feature("Internal User Journey - Create New Thread with Request only file Journey") {

    Scenario("CreateThreadReq-1:The Stride User Navigates to Workspace Page for creating new thread", AcceptanceTests) {
      forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
        Given("Child Benefits User navigates to Stride Authentication Page with the credentials ")
        AuthLoginPage.navigateToStrideAuthPage()
        AuthLoginPage.enterStrideLoginDetails(pid, givenName, surName, email, roles)
        AuthLoginPage.clickSubmitButton()

        When("""the User views Workspace Navigation page a "Create thread" button is displayed""")
        CreateThreadPage.getWorkspaceHeading         shouldBe "Workspace"
        CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
        CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

        Then("the User clicks on the create thread button it should navigate to the Recipient Details page")
        CreateThreadPage.clickCreateThreadButton()
        CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

      }
    }

    Scenario(
      "CreateThreadReq-2:The user navigates to What do you want to do page with Request Files selected with No Existing Case",
      AcceptanceTests
    ) {

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

        And("the User clicks on continue button and navigates to What do you want page ")
        RecipientDetailsData.usersWithCorrectLoginDetails foreach {
          case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
            RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)

            RecipientDetailsPage.selectNoForExistingCase()
            RecipientDetailsPage.clickContinueButton()
            CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

            And("the user clicks on continue button it should navigate to the next page")
            CreateThreadPage.clickReqFilesRadioButton()
            CreateThreadPage.clickSubmitButton()

            Then("the User adds a message to the external user within the character limit available")
            ThreadDetailsPage.getThreadDetailsPage shouldBe "Thread details"
            ThreadDetailsPage.enterMessage(
              "Lorem ipsum dolor sit amet, " +
                "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
                "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
                "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
                "dictum felis eu pede mollis pretium. Integer tincidunt. " +
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
                "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
                "Aliquam lorem ante, dapibus in, viverra quis, feugiat a "
            )

            ThreadDetailsPage.submitReplyDatePlus30Days()
            ThreadDetailsPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
            ThreadDetailsPage.clickThreadDetailsButton()

        }
      }
    }

    Scenario(
      "CreateThreadReq-3:The user navigates to What do you want to do page with Request Files selected with Yes Existing Case",
      AcceptanceTests
    ) {

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

        And("the User clicks on continue button and navigates to What do you want page ")
        RecipientDetailsData.usersWithCorrectLoginDetails foreach {
          case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
            RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)

            RecipientDetailsPage.selectYesForExistingCase()
            RecipientDetailsPage.enterCaseRefNo("KL897865432211")
            RecipientDetailsPage.clickContinueButton()
            CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

            And("the user clicks on continue button it should navigate to the next page")
            CreateThreadPage.clickReqFilesRadioButton()
            CreateThreadPage.clickSubmitButton()

            Then("the User adds a message to the external user within the character limit available")
            ThreadDetailsPage.getThreadDetailsPage shouldBe "Thread details"
            ThreadDetailsPage.enterMessage(
              "Lorem ipsum dolor sit amet, " +
                "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
                "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
                "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
                "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
                "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
                "dictum felis eu pede mollis pretium. Integer tincidunt. " +
                "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
                "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
                "Aliquam lorem ante, dapibus in, viverra quis, feugiat a "
            )

            ThreadDetailsPage.submitReplyDatePlus30Days()
            ThreadDetailsPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
            ThreadDetailsPage.clickThreadDetailsButton()

            And("the user navigates to check Your Answers Page")
            ThreadDetailsPage.getThreadDetailsPage shouldBe "Check your answers"
        }
      }
    }
  }
}
