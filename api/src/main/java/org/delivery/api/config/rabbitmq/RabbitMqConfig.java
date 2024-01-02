package org.delivery.api.config.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // api 서버 즉 producer 가 exchange에 데이터를 넣어야함.
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("delivery.exchange");
    }

    // 사용할 큐를 만들어줌
    @Bean
    public Queue queue(){
        return new Queue("delivery.queue");
    }

    // 위에서 만든 exchange가 큐에 데이터를 전달 할 수 있도록 바인딩을 진행
    // 이렇게 매개변수에 특정 값을 넣지 않아도 applicationcontext에서 타입에 맞는 빈을 넣어준다.
    @Bean
    public Binding binding(DirectExchange directExchange, Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("delivery.key");
    }

    // end queue 설정

    //api 서버에서 rabbitmq로 데이터를 넘겨야한다
    //spring은 restTemplate을 이용하지만 rabbitMq에서 따로 지원하는 템플릿을 사용하면 됨.
    // messageConverter는 ObjectMapper와 같이 직렬화 역직렬화 해주는 역할 즉 dto <-> Json
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory, // amqp 하위에 있는 거 선택해야함.
            MessageConverter messageConverter // amqp에있는 거 선택해야 함.
    ){
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    // ConnectionFactory는 application.yml에 설정해주면 알아서 빈이 등록된다.



}
