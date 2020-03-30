package com.gbl.springboot.tips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class TipsApplication2 {

	public static void main(final String[] args) {
		final ConfigurableApplicationContext applicationContext = SpringApplication.run(TipsApplication.class, args);
	}

}

class CustomApplicationContextInitilizer implements ApplicationContextInitializer<GenericApplicationContext> {

	@Override
	public void initialize(final GenericApplicationContext applicationContext) {
		System.out.println("Custom context initializer ##########");

		final RatingService2 ratingService2 = new RatingService2();
		final BookService2 bookService2 = new BookService2(ratingService2);

		applicationContext.registerBean(RatingService2.class, () -> ratingService2);
		applicationContext.registerBean(BookService2.class, () -> bookService2);

	}

}

class BookService2 {

	private final RatingService2 ratingService;

	public BookService2(final RatingService2 ratingService) {
		this.ratingService = ratingService;
	}
}

class RatingService2 {

}
