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
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CreateThreadPage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

class CreateThreadSpec extends BaseSpec {
  Feature("Internal User Journey - Create Thread page") {

    Scenario("Workspace page with Create Thread Button is visible and selectable", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      When("""the User views Workspace Navigation page a "Create thread" button is displayed""")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      Then("the User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.clickCreateThreadButton()

    }

    Scenario("Recipient details page is visible and selectable", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      When("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      Then("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      And("the User clicks on continue button and navigates to next page")
      CreateThreadPage.clickContinueButton()

    }

    Scenario("What do you want to do page is visible with Send Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      And("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      Then("the User clicks on continue button and navigates to What do you want page ")
      CreateThreadPage.clickContinueButton()
      CreateThreadPage.clickSendFilesRadioButton()

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()

    }

    Scenario("What do you want to do page is visible with Request Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      And("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      Then("the User clicks on continue button and navigates to What do you want page ")
      CreateThreadPage.clickContinueButton()
      CreateThreadPage.clickReqFilesRadioButton()

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()
    }

    Scenario("What do you want to do page is visible with Send & Request Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      And("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      Then("the User clicks on continue button and navigates to What do you want page ")
      CreateThreadPage.clickContinueButton()
      CreateThreadPage.clickSendAndReqFilesRadioButton()

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()
    }

    Scenario("the user navigates to what do you want to do page and clicks the back link ", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      And("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      Then("the User clicks on continue button and navigates to What do you want page ")
      CreateThreadPage.clickContinueButton()
      CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

      And("the user clicks on back Link button and navigates to recipient details page")
      CreateThreadPage.clickBackLink()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"
    }

    Scenario("User clicks Submit button in what do you want to do page without selecting options ", AcceptanceTests) {

      Given("Navigation to service page")
      AuthLoginPage.navigateToServicePage()

      And("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      When("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient Details"

      And("the User clicks on continue button and navigates to What do you want page ")
      CreateThreadPage.clickContinueButton()

      Then("the User clicks on submit button without selecting the options ")
      CreateThreadPage.clickSubmitButton()

      And("the user receives an error message")
      CreateThreadPage.getErrorResponseWhenNoRadioButtonClicked

    }

  }
}
