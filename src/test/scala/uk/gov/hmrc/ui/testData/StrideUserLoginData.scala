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

package uk.gov.hmrc.ui.testData
import org.scalatest.prop.TableFor5
import org.scalatest.prop.Tables.Table

object StrideUserLoginData {

  val strideUserLoginDetails: TableFor5[String, String, String, String, String] = Table(
    ("pid", "givenName", "surName", "email", "roles"),
    ("12345", "TestGivenName", "TestsurName", "t@example.com", "SDEC_CHB_User")
  )

  val usersWithChildBenefitsManagerRole: TableFor5[String, String, String, String, String] =
    strideUserLoginDetails.filter { row =>
      val pid = row.productElement(0).toString
      pid.contains("12345")
    }

  def getUsersWithRole(roleName: String): TableFor5[String, String, String, String, String] =
    strideUserLoginDetails.filter { row =>
      val pid = row.productElement(0).toString
      pid.contains(pid)
    }
}
