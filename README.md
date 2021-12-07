# Producer/Consumer pattern app
This is a REST API to replicate the Producer/Consumer pattern.

##  Description
This app is based on SpringBoot framework and stores messages on a MySQL Database.

## Execute Local Servers
The app needs an instance of ZooKeeper and an instance of Kafka running locally in order to work.
For the configuration of the local servers follow this guide: https://dzone.com/articles/running-apache-kafka-on-windows-os

Open up Windows Command Prompt and run this command to start ZooKeeper server:
- `zkserver`

Then from the Kafka installation folder run this command:
- `.\bin\windows\kafka-server-start.bat .\config\server.properties`

## REST Service
To send a message to the queue simply launch a get request to this endpoint:
- `http://localhost:8081/send?message=typeHereYourMessage`