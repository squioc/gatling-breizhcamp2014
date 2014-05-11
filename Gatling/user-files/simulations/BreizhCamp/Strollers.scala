package breizhcamp

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import bootstrap._

object Strollers {

    val schedule = csv("talks.csv").random

	val scn = scenario("Flaneurs")
	    .repeat(3) {
            feed(schedule)
            .exec(
                http("Lecture du descriptif d'une presentation")
                    .get("/talk/${talk_id}")
                    .check(status.is(200)))
		}
}
