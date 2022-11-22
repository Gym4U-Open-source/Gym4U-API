package com.acme.gym4u.test.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ={
                "src/test/java/com/acme/gym4u/test/feature"
        },
        glue= {
                "com.acme.gym4u.test.step",
                "com.acme.gym4u.test.cucumber"
        }


)

public class CucumberIntegrationTest {


}
