# Moteur Gatling

## Arborescence

    ��� bin
    ��� conf
    ��� lib
    ��� user-files
        ��� data                                   - donn�es utiles � la simulation
        ��� ��� earlybirds_credentials.csv
        ��� ��� talks.csv
        ��� ��� user_credentials.csv
        ��� request-bodies
        ��� simulations
            ��� BreizhCamp
                ��� BreizhCampSimulation.scala     - la simulation
                ��� EarlyBirds.scala               - sc�nario des EarlyBird
                ��� QuietUsers.scala               - s�nario des utilisateurs pos�s
                ��� Strollers.scala                - sc�nario des "pas-int�ress�"

## Execution de la simulation

````Shell
$ bin/gatling.sh
````
