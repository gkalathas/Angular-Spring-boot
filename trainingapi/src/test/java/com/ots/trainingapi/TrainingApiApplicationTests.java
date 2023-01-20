package com.ots.trainingapi;

import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingApiApplicationTests {
  
  @Autowired
  private TestRestTemplate template;

  private URL base;

  @Before
  public void setUp() throws Exception {
    this.base = new URL("http://localhost:8080/trainingapi");
  }


  @Test
  public void contextLoads() {
  }

}
