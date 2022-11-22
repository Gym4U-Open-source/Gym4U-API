package com.acme.gym4u.test.cucumber;


import com.acme.gym4u.Gym4UApplication;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = Gym4UApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Gym4UApplication.class,
loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
