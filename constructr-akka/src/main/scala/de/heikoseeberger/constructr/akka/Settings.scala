/*
 * Copyright 2015 Heiko Seeberger
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

package de.heikoseeberger.constructr.akka

import akka.actor.{ Actor, ExtendedActorSystem, Extension, ExtensionKey }
import de.heikoseeberger.constructr.coordination.Coordination
import de.heikoseeberger.constructr.machine.ConstructrMachineSettings
import scala.concurrent.duration.FiniteDuration

object Settings extends ExtensionKey[Settings]

final class Settings private (system: ExtendedActorSystem) extends Extension with ConstructrMachineSettings {

  val joinTimeout: FiniteDuration = getDuration("constructr.join-timeout")

  override protected def config = system.settings.config
}

trait ActorSettings { this: Actor =>
  final val settings: Settings = Settings(context.system)
}
