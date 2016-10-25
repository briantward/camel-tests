package com.dataxf.tests;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;


@RunWith(CamelSpringRunner.class) // aka CamelSpringJUnit4ClassRunner.class
//@BootstrapWith(CamelTestContextBootstrapper.class)  // Can be used but is not needed
@ContextConfiguration(locations={"classpath:spring-context-with-mock-all.xml"})
public class MyCamelSpringContextByLocationsMockInXmlTest {


    @EndpointInject(uri="direct:something")
    ProducerTemplate producerTemplate;

    @EndpointInject(uri="mock:direct:something")
    MockEndpoint mockEndpoint;

    @Test
    public void testContext() throws InterruptedException {

        mockEndpoint.expectedBodiesReceived("Hello");
        mockEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("Hello");

        mockEndpoint.assertExchangeReceived(0);
        mockEndpoint.assertIsSatisfied();
    }
}
