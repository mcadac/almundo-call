package com.almundo.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Main class to set and run Almundo Rest service application
 * 
 * @author  <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@SpringBootApplication
public class AlmundoServiceApplication implements RabbitListenerConfigurer  {
	
	/**
	 * <p>Queue created in queue component (RabbitMq) to send call message</p>  
	 * <strong>Default name: </strong> call.request
	 * 
	 * @return {@link Queue} - Queue request object
	 */
	@Bean
	public Queue queueRequest( @Value("${queue.name.call.request:almundo.call.request}") final String queueName){
		
		return new Queue(queueName);
		
	}
	
	
	/**
	 * <p>Queue created in queue component (RabbitMq) to response call message</p>  
	 * <strong>Default name: </strong> call.response
	 * 
	 * @return {@link Queue} - Queue Response object
	 */
	@Bean
	public Queue queueResponse(@Value("${queue.name.call.response:almundo.call.response}") final String queueName){

		return new Queue(queueName);
		
	}
	
	
	/**
	 * Create a {@link Jackson2JsonMessageConverter} in order to be used for Queue Producer
	 * 
	 * @return {@link Jackson2JsonMessageConverter}
	 */
	@Bean
	public Jackson2JsonMessageConverter producerJsonMessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}
	
	
	/**
	 * Create {@link MappingJackson2MessageConverter} in order to be used for Queue consumer
	 * 
	 * @return {@link MappingJackson2MessageConverter}
	 */
	@Bean
	public MappingJackson2MessageConverter consumerJsonMessageConverter() {
	   return new MappingJackson2MessageConverter();
	}
	
	
	/**
	 * Creates {@link RabbitTemplate} bean to interact with RabbitMQ component
	 * 
	 * @param connectionFactory - {@link ConnectionFactory}
	 * @param producerMessageConverter - {@link Jackson2JsonMessageConverter}
	 * @return {@link RabbitTemplate}
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final Jackson2JsonMessageConverter producerMessageConverter) {
	    
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerMessageConverter);
	    return rabbitTemplate;
	}
	
	
	/**
	 * Bean required to sets configurations about message converter to Rabbit listener
	 * 
	 * @return DefaultMessageHandlerMethodFactory
	 */
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
		
	   DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
	   factory.setMessageConverter( consumerJsonMessageConverter());
	   return factory;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer#configureRabbitListeners(org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar)
	 */
	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
	   
		registrar.setMessageHandlerMethodFactory( messageHandlerMethodFactory());
	}
	
	
	/**
	 * Main method to run {@link SpringApplication#run(String...)} 
	 * 
	 * @param args - Arguments to start
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(AlmundoServiceApplication.class, args);
	}

}
