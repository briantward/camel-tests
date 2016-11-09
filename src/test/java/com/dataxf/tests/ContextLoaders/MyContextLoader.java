package com.dataxf.tests.ContextLoaders;

import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring-context.xml"})
//@ContextConfiguration(locations = {"classpath:spring-context.xml"}) would NOT work here in combination with a parent @ContextConfiguration(classes = {MyContextLoader.class})
public class MyContextLoader {
}
