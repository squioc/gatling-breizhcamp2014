package breizhcamp

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import io.gatling.http.Headers.Names._
import scala.concurrent.duration._

class BreizhCampSimulation extends Simulation {

	val httpProtocol = http.baseURL("http://localhost:5000").disableFollowRedirect

	setUp(
            EarlyBirds.scn.inject(rampRate(10 usersPerSec) to (50 usersPerSec) during (5 seconds), rampRate(40 usersPerSec) to (3 usersPerSec) during (3 seconds), constantRate(1 usersPerSec) during (22 seconds)),
            QuietUsers.scn.inject(nothingFor(3 seconds), atOnce(100 users), constantRate(100 usersPerSec) during(25 seconds)),
            Strollers.scn.inject(nothingFor(3 seconds), constantRate(20 usersPerSec) during(25 seconds))
		)
		.protocols(httpProtocol)
}
