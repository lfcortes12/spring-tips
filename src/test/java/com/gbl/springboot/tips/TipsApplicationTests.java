package com.gbl.springboot.tips;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest(classes = TipsApplication.class)
class TipsApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void explicitBeanDefinitionCreationTest() {
		Assert.notNull(applicationContext.getBean(BookService.class), "Service bean is not null");
		Assert.notNull(applicationContext.getBean(RatingService.class), "Rating bean is not null");
	}

	@Test
	void componentScanBeanDefinitionCreationTest() {
		Assert.notNull(applicationContext.getBean(CarService.class), "Car bean is not null");
		Assert.notNull(applicationContext.getBean(EngineService.class), "Engine bean is not null");
	}

	@Test
	void programmaticBeanDefinitionCreationTest() {
		Assert.notNull(applicationContext.getBean(MovieService.class), "Movie bean is not null");
		Assert.notNull(applicationContext.getBean(DirectorService.class), "Director bean is not null");
	}

}
