### :computer: How to execute

run all containers using the command

```docker-compose up```

when all containers are running, you can send an event using the following command

``docker exec -it container_name: mycontainername kafka-console-producer.sh --bootstrap-server localhost:9092 --topic online``

send some payment event in JSON format, the consumer will handle it 

### :memo: Notes

The solution is based on ``Spring boot``, ``Spring cloud stream`` and ``kafka-stream`` framework

The project contains the consumer class ``PaymentKafkaConsumer`` who will accept the kafka stream event and send it to the service classes to be handle





