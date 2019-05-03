README
-

* Usage
  - java -jar filename.jar [server:port[,another.host:port]] username password group topic
    - kafka-sandboxes.jrtdev.com:30093 bob bob bob-group test

* Build it
  - mvn clean package 

* Run it
  - java -jar target/kafka-consumer-1.1-SNAPSHOT-shaded.jar kafka-sandboxes.jrtdev.com:30093 bob bob bob-group test
