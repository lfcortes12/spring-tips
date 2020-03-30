## Creating Spring Beans

* [XML Based Bean Definition](#xml-based-bean-definition)
* [Java Based Bean Definition](#java-based-bean-definition)
  * [Explicit Bean Definition](#explicit-bean-definition)
  * [Component Scanning bean definition](#component-scanning-bean-definition)
  * [Programmatic bean definition](#programmatic-bean-definition)

## XML Based Bean Definition

The oldest way to register a bean, just creating an instance of a given class.

~~~

<beans>
   <bean name="com.gbl.springboot.tips.bean.MyBean">
   </bean>
</beans>

~~~
	

## Java Based Bean Definition

### Explicit Bean Definition

~~~

    @Bean
    RatingService ratingService() {
        return new RatingService();
    }

    @Bean
    BookService bookService() {
        return new BookService(ratingService());
    }

~~~

### Component Scanning Bean Definition

It requires the use of @ComponentScan annotation

~~~

@ComponentScan
@Configuration
public class TipsApplication {
}

................

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

~~~

### Programmatic bean definition 

It's a lower level way for registering beans declaring a custom BeanDefinitionRegistryPostProcessor:

~~~

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

~~~

Another way for doing that is creating a custom implementation of ApplicationContextInitializer like 
below:

~~~

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

~~~

Then, register this in the application factories file under src/main/resource/META-INF/spring.factories:

~~~

org.springframework.context.ApplicationContextInitializer=com.gbl.springboot.tips.CustomApplicationContextInitilizer

~~~


	
	

	



	
	
	

