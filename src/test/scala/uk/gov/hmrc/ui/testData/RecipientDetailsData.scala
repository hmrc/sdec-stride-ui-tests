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

object RecipientDetailsData {

  val recipientDetailsData: TableFor5[String, String, String, String, String] = Table(
    ("firstName", "lastName", "recipientEmail", "mobileNumber", "niNumber"),
    ("TestFirst", "TestLast", "Test1@example.com", "07730900982", "AA123456B"),
    ("!@£$%^", "TestLast", "Test2@example.com", "07720900982", "AA123456C"),
    ("TestFirst", "AASSDDFFGGHHJJKKLLTTYYUUIIOOPP", "Test3@example.com", "07710900982", "AA123456B"),
    ("TestFirst", "TestLast", "test.com", "07700800982", "AA123456B"),
    ("TestFirst", "TestLast", "hmrc@test.com", "1234", "AA123456B"),
    ("TestFirst", "TestLast", "test.com", "07700811982", "AA12345")
  )

  // Helper: returns rows where the given column exactly equals the value
  private def rowsWhere(column: Int, value: String): Seq[(String, String, String, String, String)] =
    recipientDetailsData.toSeq.collect {
      case row if row.productElement(column) == value =>
        (
          row.productElement(0).toString,
          row.productElement(1).toString,
          row.productElement(2).toString,
          row.productElement(3).toString,
          row.productElement(4).toString
        )
    }

  // Plain Seq so the spec can use .foreach { case (...) => ... }
  val usersWithCorrectLoginDetails: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "07730900982")

  val usersWithSpecialCharacter: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "07720900982")

  val usersWithBigLastName: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "07710900982")

  val usersWithWrongEmailAddress: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "07700800982")

  val usersWithWrongMobileNumber: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "1234")

  val usersWithWrongNINumber: Seq[(String, String, String, String, String)] =
    rowsWhere(3, "07700811982")

  // Matches on the NI number column (index 4), not the mobile number
  def getUsersWithNiNumber(niNumber: String): Seq[(String, String, String, String, String)] =
    rowsWhere(4, niNumber)

  // Reads every row of the table, in order
  def getAllRecipients: List[(String, String, String, String, String)] =
    recipientDetailsData.toList

}
