package breizhcamp

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import bootstrap._

object QuietUsers {

    val userCredentials = csv("user_credentials.csv").queue
    val schedule = csv("talks.csv").random

	val scn = scenario("Utilisateurs posés")
	    .repeat(2) {
            feed(schedule)
            .exec(
                http("Lecture du descriptif d'une presentation")
                    .get("/talk/${talk_id}")
                    .check(status.is(200)))
		}
	    .feed(userCredentials)
		.exec(
			http("Connexion au compte")
				.get("/auth/login")
				.basicAuth("${username}", "${password}")
				.check(status.is(200), jsonPath("$..token").saveAs("token")))
        .feed(schedule)
        .exec(
            http("Lecture du descriptif d'une presentation")
                .get("/talk/${talk_id}")
                .check(status.is(200)))
		.exec(
			http("Achat du billet")
				.post("/order")
				.header("Authorization", "Bearer ${token}")
				.check(status.is(200)))
		.exec(
			http("Fermeture de la session")
				.get("/auth/logout")
				.check(status.is(200)))
}
