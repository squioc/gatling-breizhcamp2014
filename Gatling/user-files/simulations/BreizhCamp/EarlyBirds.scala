package breizhcamp

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import bootstrap._

object EarlyBirds {

    val userCredentials = csv("earlybirds_credentials.csv").queue

	val scn = scenario("EarlyBird")
	    .feed(userCredentials)
		.exec(
			http("Connexion au compte")
				.get("/auth/login")
				.basicAuth("${username}", "${password}")
				.check(status.is(200), jsonPath("$..token").saveAs("token")))
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
