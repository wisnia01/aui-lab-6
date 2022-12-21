#!/bin/bash 
 
cd lab3-room
java -jar target/lab2-0.0.1-SNAPSHOT.jar &
sleep 10
cd ../lab3-hotel
java -jar target/lab2-0.0.1-SNAPSHOT.jar &
sleep 10
cd ../lab3-gate
java -jar target/lab3-0.0.1-SNAPSHOT.jar