package com.shobs.springboot.kafka.controller;

import com.shobs.springboot.kafka.messaging.TransactionProcessorChannels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class Controller {

    @Autowired
    TransactionProcessorChannels channels;

    @Autowired
    TaskExecutor executor;

    @GetMapping(path = "produce")
    public void produceMessages() {
        for(int i = 1; i<=20; i++) {
            CompletableFuture.runAsync(this::sendMessage, executor);
        }

    }

    public void sendMessage() {
        String payload = "{\"encodedKey\":\"8a928eff8188e18f0181957793a569fe\",\"transactionId\":###tid###,\"parentAccountKey\":\"8a928e9f7cbf874e017cc0e77e3f0be9\",\"type\":\"WITHDRAWAL\",\"creationDate\":\"2022-06-24T13: 27: 31+02: 00\",\"entryDate\":\"2022-06-24T13: 27: 31+02: 00\",\"amount\":\"-89.99\",\"interestAmount\":\"0\",\"feesAmount\":\"0\",\"overdraftAmount\":\"0\",\"fundsAmount\":\"89.9900000000\",\"balance\":\"35.8100000000\",\"productTypeKey\":\"8a928e486031f98d01604f2c7a740161\",\"currencyCode\":\"ZAR\",\"comment\":\"Purchase at MrP Mabopane Mabopane ZA 217511606239\",\"customInformation\":[{\"value\":\"710\",\"customFieldID\":\"CURRENCY_CODE_ACCOUNT\"},{\"value\":\"72      \",\"customFieldID\":\"CARD_ACCEPTOR_TERMINAL_ID\"},{\"value\":\"2022-06-24\",\"customFieldID\":\"SETTLEMENT_DATE\"},{\"value\":\"4\",\"customFieldID\":\"POSI_PINCaptureCapability\"},{\"value\":\"774\",\"customFieldID\":\"SVFE_TRANSACTION_TYPE\"},{\"value\":\"0\",\"customFieldID\":\"POSI_CardDataOutputCapability\"},{\"value\":\"51043918279\",\"customFieldID\":\"ACCOUNT_IDENTIFICATION\"},{\"value\":\"POS\",\"customFieldID\":\"SVFE_TERMINAL_TYPE\"},{\"value\":\"1\",\"customFieldID\":\"POSI_CardHolderAuthCapability\"},{\"value\":\"710\",\"customFieldID\":\"ACQUIRING_INSTIT_COUNTRY_CODE\"},{\"value\":\"002000\",\"customFieldID\":\"PROCESSING_CODE\"},{\"value\":\"61000000\",\"customFieldID\":\"CONVERSION_RATE_ACCOUNT\"},{\"value\":\"7353280895507263\",\"customFieldID\":\"PRIMARY_ACCOUNT_NUMBER\"},{\"value\":\"89.99\",\"customFieldID\":\"AMOUNT_ACCOUNT\"},{\"value\":\"1\",\"customFieldID\":\"POSI_CardPresent\"},{\"value\":\"710\",\"customFieldID\":\"CURRENCY_CODE_TRANSACTION\"},{\"value\":\"24-06-2022 11: 27: 25\",\"customFieldID\":\"DATE_TIME_LOCAL_TRANSACTION\"},{\"value\":\"0\",\"customFieldID\":\"POSI_CardCaptureCapability\"},{\"value\":\"5651\",\"customFieldID\":\"MERCHANT_TYPE\"},{\"value\":\"7001\",\"customFieldID\":\"SVFE_ISSUER_INSTIT_IDENTIFIER\"},{\"value\":\"355026704\",\"customFieldID\":\"SVFE_UTRNNO\"},{\"value\":\"89.99\",\"customFieldID\":\"AMOUNT_TRANSACTION\"},{\"value\":\"0\",\"customFieldID\":\"POSI_TerminalOutputCapability\"},{\"value\":\"5\",\"customFieldID\":\"POSI_CardDataInputmode\"},{\"value\":\"1100 - Non Financial\",\"customFieldID\":\"MESSAGE_TYPE\"},{\"value\":\"1\",\"customFieldID\":\"POSI_CardHolderAuthMethod\"},{\"value\":\"1\",\"customFieldID\":\"POSI_OperatingEnvironment\"},{\"value\":\"3\",\"customFieldID\":\"POSI_CardHolderAuthEntity\"},{\"value\":\"MrP Mabopane              Mabopane    ZA\",\"customFieldID\":\"CARD_ACCEPTOR_NAME_LOCATION\"},{\"value\":\"0\",\"customFieldID\":\"POSI_CardHolderPresenceIndicator\"},{\"value\":\"810101513004\",\"customFieldID\":\"POINT_OF_SERVICE_ENTRY_CODE\"},{\"value\":\"8\",\"customFieldID\":\"POSI_CardDataInputCapability\"},{\"value\":\"217511606239\",\"customFieldID\":\"RETRIEVAL_REFERENCE_NUMBER\"},{\"value\":\"026704\",\"customFieldID\":\"SYSTEMS_TRACE_AUDIT_NUMBER\"},{\"value\":\"355026704\",\"customFieldID\":\"SFVE_ORIGINAL_UTRNNO\"},{\"value\":\"000000001489178\",\"customFieldID\":\"CARD_ACCEPTOR_MERCHANT_ID\"},{\"value\":\"455200\",\"customFieldID\":\"ACQUIRING_INSTITUTION_ID_CODE\"},{\"value\":\"TXN1040\",\"customFieldID\":\"TXN_NUMBER\"}]}";
        payload = payload.replace("###tid###", UUID.randomUUID().toString());
        boolean status = channels.produceJsonUnencryptedTransactionChannel()
                .send(MessageBuilder.withPayload(payload).build());
        log.info("message delivery status {}", status);

    }
}