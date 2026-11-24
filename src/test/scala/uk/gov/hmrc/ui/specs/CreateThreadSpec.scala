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
import uk.gov.hmrc.ui.pages.AuthLoginPage.navigateToPage
import uk.gov.hmrc.ui.pages.{CreateThreadPage, RecipientDetailsPage, StrideUserLoginPage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

class CreateThreadSpec extends BaseSpec {
  Feature("Internal User Journey - Create Thread page") {

    Scenario("Workspace page with Create Thread Button is visible and selectable", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

      When("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      Then("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

      And("the User clicks on continue button and navigates to next page")
      RecipientDetailsPage.clickContinueButton()

    }

    Scenario("What do you want to do page is visible with Send Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()

    }

    Scenario("What do you want to do page is visible with Request Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()
    }

    Scenario("What do you want to do page is visible with Send & Request Files selected", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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

      And("the user clicks on continue button but does not navigate to the next page")
      CreateThreadPage.clickSubmitButton()
    }

    Scenario("the user navigates to what do you want to do page and clicks the back link ", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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

      And("the user clicks on back Link button and navigates to recipient details page")
      CreateThreadPage.clickBackLink()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"
    }

    Scenario("User clicks Submit button in what do you want to do page without selecting options ", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

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
      RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "hmrc@test.com", "07700900982", "AA123456B")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()

      Then("the User clicks on submit button without selecting the options ")
      CreateThreadPage.clickSubmitButton()

      And("the user receives an error message")
      CreateThreadPage.getErrorResponseWhenNoRadioButtonClicked

    }

    Scenario("User navigates to Recipient details page and validates error messages", AcceptanceTests) {

      Given("Navigation to service page")
      navigateToPage(StrideUserLoginPage)
      StrideUserLoginPage.enterIdentityDetails("12345", "TestGivenName", "TestsurName", "t@example.com")
      StrideUserLoginPage.enterRole("SDEC_CHB_User")
      StrideUserLoginPage.clickSubmitButton()

      When("the User views Workspace Navigation page a Create thread button is displayed")
      CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
      CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

      Then("the User clicks on the create thread button and the User navigates to the Recipient details pag")
      CreateThreadPage.clickCreateThreadButton()
      CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

      And("the User provides incorrect first name, clicks on Continue button and receives respective error message")
      RecipientDetailsPage.enterRecipientDetails("!@£$%^", "TestLast", "hmrc@test.com", "07700900982", "AA123456B")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      RecipientDetailsPage.getFirstNameErrorMessage should include(
        "Name must only include letters a to z and punctuation, for example a hyphen or apostrophe"
      )

      And("the User provides incorrect last name, clicks on Continue button and receives respective error message")
      RecipientDetailsPage.enterRecipientDetails(
        "TestFirst",
        "AASSDDFFGGHHJJKKLLTTYYUUIIOOPP",
        "hmrc@test.com",
        "07700900982",
        "AA123456B"
      )
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      RecipientDetailsPage.getLastNameErrorMessage should include("Name must be between 2 and 20 characters")

      And("the User provides incorrect email, clicks on Continue button and receives respective error message")
      RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "test.com", "07700900982", "AA123456B")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      RecipientDetailsPage.getEmailErrorMessage should include(
        "Enter an email address in the correct format, like name@example.com"
      )

      And("the User provides incorrect mobile number, clicks on Continue button and receives respective error message")
      RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "hmrc@test.com", "1234", "AA123456B")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      RecipientDetailsPage.getMobileNumberErrorMessage should include(
        "Enter a phone number in the correct format such as 07700 900 982"
      )

      And(
        "the User provides incorrect national insurance number, clicks on Continue button and receives respective error message"
      )
      RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "hmrc@test.com", "07700900982", "AA12345")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      RecipientDetailsPage.getNINumberErrorMessage should include(
        "Enter a National Insurance number in the correct format"
      )

      And(
        "the User provides correct details, clicks on Continue button and navigates to next page"
      )
      RecipientDetailsPage.enterRecipientDetails("TestFirst", "TestLast", "hmrc@test.com", "07700900982", "AA123456B")
      RecipientDetailsPage.selectNoForExistingCase()
      RecipientDetailsPage.clickContinueButton()
      CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"
    }

  }
}
