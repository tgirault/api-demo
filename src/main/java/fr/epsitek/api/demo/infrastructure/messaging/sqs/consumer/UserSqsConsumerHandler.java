package fr.epsitek.api.demo.infrastructure.messaging.sqs.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSqsConsumerHandler {

    @Value("${aws.sqs.my-queue.name}")
    private String queueName;

    private final SqsClient sqsClient;

    @Scheduled(initialDelay = 15000, fixedDelay = 1000) // It runs every 1 second.
    public void consumeMessages() {
        try {
            GetQueueUrlResponse getQueueUrlResponse = sqsClient
                    .getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());

            String queueUrl = getQueueUrlResponse.queueUrl();

            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl).build();

            List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
            for (Message message : messages) {
                log.info("Read Message from queue: [id={}, body={}]", message.messageId(), message.body());

                DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .receiptHandle(message.receiptHandle())
                        .build();

                sqsClient.deleteMessage(deleteMessageRequest);
            }

        } catch (SqsException e) {
            log.error("Queue Exception Message: {}", e.getMessage());
        }
    }
}
