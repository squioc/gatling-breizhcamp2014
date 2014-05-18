# Moteur Gatling

## Arborescence

    ¿¿¿ bin
    ¿¿¿ conf
    ¿¿¿ lib
    ¿¿¿ user-files
        ¿¿¿ data
        ¿   ¿¿¿ earlybirds_credentials.csv       ¿
        ¿   ¿¿¿ talks.csv                        |  données
        ¿   ¿¿¿ user_credentials.csv             ¿
        ¿¿¿ request-bodies
        ¿¿¿ simulations
            ¿¿¿ BreizhCamp
                ¿¿¿ BreizhCampSimulation.scala  - simulation
                ¿¿¿ EarlyBirds.scala            ¿
                ¿¿¿ QuietUsers.scala            | Scénarii
                ¿¿¿ Strollers.scala             ¿


## Execution de la simulation

````Shell
$ bin/gatling.sh
````
