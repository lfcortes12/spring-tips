package com.gbl.springboot.tips;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan
@Configuration
public class TipsApplication {

	@Component
	public static class CustomBeanBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

		@Override
		public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) throws BeansException {

		}

		@Override
		public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) throws BeansException {
			final BeanFactory beanFactory = BeanFactory.class.cast(registry);
			registry.registerBeanDefinition("directorService",
					BeanDefinitionBuilder.genericBeanDefinition(DirectorService.class).getBeanDefinition());
			registry.registerBeanDefinition("movieService",
					BeanDefinitionBuilder
							.genericBeanDefinition(MovieService.class,
									() -> new MovieService(beanFactory.getBean(DirectorService.class)))
							.getBeanDefinition());

		}

	}

	@Bean
	RatingService ratingService() {
		return new RatingService();
	}

	@Bean
	BookService bookService() {
		return new BookService(ratingService());
	}

	public static void main(final String[] args) {
		final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TipsApplication.class);
	}

}

class BookService {

	private final RatingService ratingService;

	public BookService(final RatingService ratingService) {
		this.ratingService = ratingService;
	}
}

class RatingService {

}

@Component
class CarService {
	private final EngineService engineService;

	public CarService(final EngineService engineService) {
		super();
		this.engineService = engineService;
	}
}

@Component
class EngineService {

}

class MovieService {

	private final DirectorService directorService;

	public MovieService(final DirectorService directorService) {
		super();
		this.directorService = directorService;
	}

}

class DirectorService {

}