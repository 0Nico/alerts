package com.safetynet.alerts.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.AlertsApplication;


@SpringBootTest(classes = AlertsApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

   protected MockMvc mvc;
   
   @Autowired
   WebApplicationContext webApplicationContext;
   
   
   @BeforeEach
   public void setUp() {
	   mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
      
      try {
   	   clearJsonDatabase();
      } catch (IOException e) {
   	   e.printStackTrace();
      }
   }

   
   protected String mapToJson(Object obj) throws JsonProcessingException {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
	   }
   protected <T> T mapFromJson(String json, Class<T> clazz)
      throws JsonParseException, JsonMappingException, IOException {
      
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.readValue(json, clazz);
   }
   
   protected void clearJsonDatabase() throws IOException {
	   Path sourceFile = Paths.get("src/test/resources/data.json");
	   Path targetFile = Paths.get("src/main/resources/data.json");
	   Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
   }
   
}