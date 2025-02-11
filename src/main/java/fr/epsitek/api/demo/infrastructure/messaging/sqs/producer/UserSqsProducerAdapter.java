package fr.epsitek.api.demo.infrastructure.messaging.sqs.producer;

import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.domain.ports.UserEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSqsProducerAdapter implements UserEventProducer {

    @Value("${aws.sqs.my-queue.name}")
    private String queueName;

    private final SqsClient sqsClient;

    public void publish(User user) {
        try {
            GetQueueUrlResponse getQueueUrlResponse = sqsClient
                    .getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());

            String queueUrl = getQueueUrlResponse.queueUrl();

            SendMessageBatchRequestEntry entry = SendMessageBatchRequestEntry.builder()
                    .messageGroupId(user.userId().id().toString())
                    .id(user.userId().id().toString())
                    .messageBody(user.email())
                    .build();

            SendMessageBatchRequest sendMessageBatchRequest = SendMessageBatchRequest.builder()
                    .queueUrl(queueUrl)
                    .entries(entry)
                    .build();

            SendMessageBatchResponse sendMessageBatchResponse = sqsClient.sendMessageBatch(sendMessageBatchRequest);

            if (sendMessageBatchResponse.hasSuccessful()) {
                log.info("Publish message [messageId={}] successful", sendMessageBatchResponse.successful().getFirst().messageId());
            }
        } catch (SqsException e) {
            log.error(e.awsErrorDetails().errorMessage());
        }
    }
}