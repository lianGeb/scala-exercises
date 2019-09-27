/*
 *  scala-exercises
 *
 *  Copyright 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
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
 *
 */

package org.scalaexercises.algebra.exercises

import cats.Functor
import org.scalaexercises.types.exercises._
import org.scalaexercises.types.exercises.ExerciseEvaluation.EvaluationRequest
import cats.implicits._

/** Exposes Exercise operations as a Free monadic algebra that may be combined with other Algebras via
 * Coproduct.
 */
trait ExerciseOpsAlgebra[F[_]] {

  def getLibraries: F[List[Library]]

  def getLibrary(libraryName: String): F[Option[Library]]

  def getSection(libraryName: String, sectionName: String): F[Option[Section]]

  def buildRuntimeInfo(evaluation: ExerciseEvaluation): F[EvaluationRequest]

}

abstract class ExerciseOps[F[_]: Functor] extends ExerciseOpsAlgebra[F] {
  override def getLibrary(libraryName: String): F[Option[Library]] =
    getLibraries.map(_.find(_.name == libraryName))
}
