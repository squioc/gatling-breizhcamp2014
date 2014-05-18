# Moteur Gatling

## Arborescence

    ¿¿¿ bin
    ¿¿¿ conf
    ¿¿¿ lib
    ¿¿¿ user-files
        ¿¿¿ data                                   - données utiles à la simulation
        ¿   ¿¿¿ earlybirds_credentials.csv
        ¿   ¿¿¿ talks.csv
        ¿   ¿¿¿ user_credentials.csv
        ¿¿¿ request-bodies
        ¿¿¿ simulations
            ¿¿¿ BreizhCamp
                ¿¿¿ BreizhCampSimulation.scala     - la simulation
                ¿¿¿ EarlyBirds.scala               - scénario des EarlyBird
                ¿¿¿ QuietUsers.scala               - sénario des utilisateurs posés
                ¿¿¿ Strollers.scala                - scénario des "pas-intéressé"

## Execution de la simulation

````Shell
$ bin/gatling.sh
````
