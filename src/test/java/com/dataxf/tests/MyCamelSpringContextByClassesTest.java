package com.dataxf.tests;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringRunner.class) // aka CamelSpringJUnit4ClassRunner.class
@ContextConfiguration(classes={MyContextLoader.class})
public class MyCamelSpringContextByClassesTest {

    @EndpointInject(uri="direct:something")
    ProducerTemplate producerTemplate;

    // Already Mocked up in existing context loaded by MyContextLoader.class
    @EndpointInject(uri="mock:somethingMocked")
    MockEndpoint mockEndpoint;

    @Test
    public void testContext() throws InterruptedException {

        mockEndpoint.expectedBodiesReceived("Hello");
        mockEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("Hello");
        System.out.println("test executed");

        mockEndpoint.assertExchangeReceived(0);
        mockEndpoint.assertIsSatisfied();
    }
}
