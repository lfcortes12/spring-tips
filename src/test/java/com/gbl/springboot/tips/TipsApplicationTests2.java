package com.gbl.springboot.tips;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest(classes = TipsApplication2.class)
class TipsApplicationTests2 {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void programmaticBeanDefinitionCreationTest() {
		Assert.notNull(applicationContext.getBean(RatingService2.class), "RatingService2 bean is not null");
		Assert.notNull(applicationContext.getBean(BookService2.class), "BookService2 bean is not null");
	}

}
