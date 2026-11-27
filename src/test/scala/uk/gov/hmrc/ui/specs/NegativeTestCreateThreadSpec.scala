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
import uk.gov.hmrc.ui.testData.{RecipientDetailsData, StrideUserLoginData}

class NegativeTestCreateThreadSpec extends BaseSpec with TableDrivenPropertyChecks {

  Feature("Internal User Journey - Create New Thread with Negative tests") {

    forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
      RecipientDetailsData.usersWithCorrectLoginDetails.foreach {
        case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>

          Scenario(
            s"User with roles clicks Submit button without selecting options for recipient ",
            AcceptanceTests
          ) {

            Given("Child Benefits User navigates to Stride Authentication Page with the credentials")
            AuthLoginPage.navigateToStrideAuthPage()
            AuthLoginPage.enterStrideLoginDetails(pid, givenName, surName, email, roles)
            AuthLoginPage.clickSubmitButton()

            And("the User views Workspace Navigation page a Create thread button is displayed")
            CreateThreadPage.getWorkspaceHeading           shouldBe "Workspace"
            CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

            And("the button must be selectable")
            CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
            CreateThreadPage.getCreateThreadButtonText   shouldBe "Create new thread"

            When("the User clicks on the create thread button and the User navigates to the Recipient details page")
            CreateThreadPage.clickCreateThreadButton()
            CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"

            And(s"the User enters recipient  and navigates to What do you want page")
            RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)
            RecipientDetailsPage.selectNoForExistingCase()
            RecipientDetailsPage.clickContinueButton()
            CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

            Then("the User clicks on submit button without selecting the options")
            CreateThreadPage.clickSubmitButton()

            And("the user receives an error message")
            CreateThreadPage.getErrorResponseWhenNoRadioButtonClicked shouldBe "Select what you need to do"
          }
      }

    }
  }

  Scenario("the user navigates to what do you want to do page and clicks the back link ", AcceptanceTests) {

    forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
      RecipientDetailsData.usersWithCorrectLoginDetails.foreach {
        case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
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

          RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)
          RecipientDetailsPage.selectNoForExistingCase()
          RecipientDetailsPage.clickContinueButton()
          CreateThreadPage.getWhatDoYouWantToDoHeading shouldBe "What do you want to do?"

          And("the user clicks on back Link button and navigates to recipient details page")
          CreateThreadPage.clickBackLink()
          CreateThreadPage.getRecipientDetailsHeadingText shouldBe "Recipient details"
      }
    }
  }

  Scenario("Recipient details form validation", AcceptanceTests) {

    forAll(StrideUserLoginData.strideUserLoginDetails) { (pid, givenName, surName, email, roles) =>
      Given("Child Benefits User navigates to Stride Authentication Page with the credentials ")
      AuthLoginPage.navigateToStrideAuthPage()
      AuthLoginPage.enterStrideLoginDetails(pid, givenName, surName, email, roles)
      AuthLoginPage.clickSubmitButton()

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
      RecipientDetailsData.usersWithSpecialCharacter foreach {
        case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
          RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)
          RecipientDetailsPage.selectNoForExistingCase()
          RecipientDetailsPage.clickContinueButton()
          RecipientDetailsPage.getFirstNameErrorMessage should include(
            "Name must only include letters a to z and punctuation, for example a hyphen or apostrophe"
          )

          And("the User provides incorrect last name, clicks on Continue button and receives respective error message")
          RecipientDetailsData.usersWithBigLastName.foreach {
            case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
              RecipientDetailsPage.enterRecipientDetails(firstName, lastName, recipientEmail, mobileNumber, niNumber)
              RecipientDetailsPage.selectNoForExistingCase()
              RecipientDetailsPage.clickContinueButton()
              RecipientDetailsPage.getLastNameErrorMessage should include("Name must be between 2 and 20 characters")

              And("the User provides incorrect email, clicks on Continue button and receives respective error message")
              RecipientDetailsData.usersWithWrongEmailAddress foreach {
                case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
                  RecipientDetailsPage.enterRecipientDetails(
                    firstName,
                    lastName,
                    recipientEmail,
                    mobileNumber,
                    niNumber
                  )
                  RecipientDetailsPage.selectNoForExistingCase()
                  RecipientDetailsPage.clickContinueButton()
                  RecipientDetailsPage.getEmailErrorMessage should include(
                    "Enter an email address in the correct format, like name@example.com"
                  )

                  And(
                    "the User provides incorrect mobile number, clicks on Continue button and receives respective error message"
                  )
                  RecipientDetailsData.usersWithWrongMobileNumber foreach {
                    case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
                      RecipientDetailsPage.enterRecipientDetails(
                        firstName,
                        lastName,
                        recipientEmail,
                        mobileNumber,
                        niNumber
                      )
                      RecipientDetailsPage.selectNoForExistingCase()
                      RecipientDetailsPage.clickContinueButton()
                      RecipientDetailsPage.getMobileNumberErrorMessage should include(
                        "Enter a phone number in the correct format such as 07700 900 982"
                      )

                      And(
                        "the User provides incorrect national insurance number, clicks on Continue button and receives respective error message"
                      )
                      RecipientDetailsData.usersWithWrongNINumber foreach {
                        case (firstName, lastName, recipientEmail, mobileNumber, niNumber) =>
                          RecipientDetailsPage.enterRecipientDetails(
                            firstName,
                            lastName,
                            recipientEmail,
                            mobileNumber,
                            niNumber
                          )
                          RecipientDetailsPage.selectNoForExistingCase()
                          RecipientDetailsPage.clickContinueButton()
                          RecipientDetailsPage.getNINumberErrorMessage should include(
                            "Enter a National Insurance number in the correct format"
                          )
                      }

                  }
              }
          }
      }
    }
  }
}
