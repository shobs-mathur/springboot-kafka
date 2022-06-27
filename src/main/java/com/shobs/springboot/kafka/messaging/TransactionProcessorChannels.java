package com.shobs.springboot.kafka.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface TransactionProcessorChannels {

    String CTNS_POSTED_TRANSACTION_UNENCRYPTED_CONSUME_CHANNEL = "ctns-posted-transaction-unencrypted-consume-channel";
    String TMPS_POSTED_TRANSACTION_UNENCRYPTED_PRODUCE_CHANNEL = "tmps-posted-transaction-unencrypted-produce-channel";

    @Input(CTNS_POSTED_TRANSACTION_UNENCRYPTED_CONSUME_CHANNEL)
    SubscribableChannel consumeUnencryptedTransactionChannel();

    @Output(TMPS_POSTED_TRANSACTION_UNENCRYPTED_PRODUCE_CHANNEL)
    MessageChannel produceJsonUnencryptedTransactionChannel();
}