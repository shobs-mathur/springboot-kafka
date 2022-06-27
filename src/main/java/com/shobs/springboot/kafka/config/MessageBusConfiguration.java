package com.shobs.springboot.kafka.config;

import com.shobs.springboot.kafka.messaging.TransactionProcessorChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(TransactionProcessorChannels.class)
public class MessageBusConfiguration {

}