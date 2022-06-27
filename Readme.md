# Springboot + Kafka (On Docker)
This repo is used to explore the springboot config for Kafka Streams.
Use the docker-compose to startup kafka in a docker container.

**Setting concurrency on consumer channel**

To set the concurrency on your kafka stream listener, you need to set the concurrency property of the consumer channel

```spring.cloud.stream.bindings.consumer-channel.consumer.concurrency: 6```

Note: 
1. If the instance count (or instance count * concurrency) exceeds the number of partitions, some consumers will remain idle.
2. Concurrency should be tightly linked to the number of partitions on the topic. 
3. It is generally best to “over-provision” the partitions to allow for future increases in consumers or concurrency.

**Increase throughput on your consumers**

To increase throughput on your consumers, we have two options - 
   1. Increase the number of partitions and the concurrency on your consumers. This would spread out messages even further and would increase the chance that messages are sent to different threads.
   2. if you don't care about ordering would be to process messages asynchronously downstream: by delegating the stream listener to a thread executor. The caveat with this approach is that the messages get dropped if they fail to process successfully. You can do manual acks or use a DLQ for this scenario.

**Manual acks for kafka messages**

To provide manual ack/nacks for your kafka messages, set the config property below - 

```spring.cloud.stream.kafka.bindings.consumer-channel.consumer.autoCommitOffset: false```

Then, in your kafka listener method add the below code logic to ack/nack the messages manually

```java
Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
    if (acknowledgment != null) {
        acknowledgment.acknowledge();
}
```
