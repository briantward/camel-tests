package com.dataxf.tests;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

//@RunWith(CamelSpringJUnit4ClassRunner.class)
@RunWith(CamelSpringRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(classes={MyContextLoader.class})
@MockEndpoints // only works when spring-context is loaded directly by @ContextConfiguration and @BootstrapWith(CamelTestContextBootstrapper.class)
public class MyCamelSpringContextByClassesMockExceptionTest {


    @EndpointInject(uri="direct:something")
    ProducerTemplate producerTemplate;

    @EndpointInject(uri="mock:direct:something")
    MockEndpoint mockEndpoint;

    @Test(expected = NullPointerException.class)
    public void testContext() throws InterruptedException {

        // Throws NPE because it did not set up MockEndpoints because the annotation doesn't work
        // when using a ContextLoader.class value in the annotation @ContextConfiguration(classes={MyContextLoader.class})
        mockEndpoint.expectedBodiesReceived("Hello");
        mockEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("Hello");
        System.out.println("test executed");

        mockEndpoint.assertExchangeReceived(0);
        mockEndpoint.assertIsSatisfied();
    }
}
