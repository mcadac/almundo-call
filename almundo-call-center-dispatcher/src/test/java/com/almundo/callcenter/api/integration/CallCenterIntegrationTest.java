package com.almundo.callcenter.api.integration;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.almundo.callcenter.api.ICallCenterManager;
import com.almundo.callcenter.api.IDispatcher;
import com.almundo.callcenter.api.IQueueService;
import com.almundo.callcenter.api.impl.CallCenterService;
import com.almundo.callcenter.api.impl.Dispatcher;
import com.almunfo.callcenter.model.CallAgent;
import com.almunfo.callcenter.model.CallRequest;

/**
 * Call center integration test
 * 
 * @author <a href="dcespitiam@gmail.com">Camilo Espitia</a>
 * @version 1.0
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CallCenterIntegrationTest {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CallCenterService.class);

	/** Call center manager for integration test */
	@Autowired
	private ICallCenterManager<CallAgent> callCenterManager;

	/** Fixed threads */
	@Value("${dispatcher.fixed.threads}")
	private int fixedThreads;

	/** Call request listener */
	private IDispatcher dispatcher;

	/** Queue of new call attempt */
	private ConcurrentLinkedQueue<CallRequest> queueNewAttempt = new ConcurrentLinkedQueue<>();

	/**
	 * Init integration test
	 */
	@Before
	public void init() {
		createDispatcher();
	}

	/**
	 * validate ten threads process concurrent with:
	 * <ul>
	 * <li>Operators = 5</li>
	 * <li>Supervisors = 3</li>
	 * <li>Directors = 2</li>
	 * </ul>
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void validateTenThreadsProcessConcurrent() throws InterruptedException {

		sendCallRequest(10);
		TimeUnit.SECONDS.sleep(10);
		LOGGER.info("Queue new call attempt size : {}", queueNewAttempt.size());
	}

	
	/**
	 * Validates twenty thread process concurrent with:
	 * <ul>
	 * <li>Operators = 5</li>
	 * <li>Supervisors = 3</li>
	 * <li>Directors = 2</li>
	 * </ul>
	 * 
	 * <p>
	 * This should has ten message pending to process in the queue
	 * </p>
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void validateTwentyThreadsProcessConcurrent() throws InterruptedException {

		sendCallRequest(20);
		TimeUnit.SECONDS.sleep(20);
		LOGGER.info("Queue new call attempt size : {}", queueNewAttempt.size());
		
	}

	
	
	/**
	 * Send call requests to {@link Dispatcher}
	 * 
	 * @param numberRequests
	 */
	private void sendCallRequest(final int numberRequests) {

		Stream.generate(() -> new CallRequest(UUID.randomUUID().toString())).limit(numberRequests)
				.forEach(callRequest -> dispatcher.dispatchCall(callRequest));
	}

	/**
	 * Create the {@link Dispatcher} for integration test
	 */
	private void createDispatcher() {

		dispatcher = new Dispatcher(fixedThreads,
				new CallCenterService(callCenterManager, createQueueService(queueNewAttempt)));
	}

	/**
	 * Create queue service for integration test without rabbitmq
	 * 
	 * @param queueNewAttempt
	 * @return {@link IQueueService}
	 */
	private IQueueService createQueueService(final ConcurrentLinkedQueue<CallRequest> queueNewAttempt) {

		return new IQueueService() {

			private ConcurrentLinkedQueue<CallRequest> queue = queueNewAttempt;

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.almundo.callcenter.api.IQueueService#putNewCallAttempt(com.
			 * almunfo.callcenter.model.CallRequest)
			 */
			@Override
			public void putNewCallAttempt(final CallRequest callRequest) {

				LOGGER.info("Creating a new attempt: {}", callRequest.getId());
				queue.add(callRequest);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.almundo.callcenter.api.IQueueService#putCallResponse(java.
			 * lang.String)
			 */
			@Override
			public void putCallResponse(final String callRequest) {
				return;

			}

		};
	}

}
