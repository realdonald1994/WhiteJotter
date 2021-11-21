#!/usr/bin/env bash
cd /home/ubuntu/jars
value=`cat /home/ubuntu/jars/env.txt`
nohup java -Xms128m -Xmx256m -jar nvio-ordering-backend-1.0.0.jar --spring.profiles.active=$value > springboot.log 2>&1 & sleep 15