package org.delivery.api.common.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    // 특정 exchange를 사용하고 key를 통해 바인딩된 큐를 선택하고 object를 넘기겠다.
    public void producer(String exchange, String routeKey, Object object){
        rabbitTemplate.convertAndSend(exchange, routeKey, object);
    }
}
