package com.ningzeta.example.querydsl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ningzeta.sample.querydsl.QuerydslExampleApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QuerydslExampleApplication.class)
@WebAppConfiguration
public class QuerydslExampleApplicationTests {

	@Test
	public void contextLoads() {
	}

}
