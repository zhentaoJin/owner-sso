package com.jzt.sso;

import com.jzt.sso.conf.ApplicationProperties;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class},scanBasePackages = {"com.jzt.sso"})
@PropertySource({ "classpath:application.yml" })
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    @Autowired
    private Environment env;

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
//        SpringApplication.run(OtsApplication.class);
    }

    @Bean
    public ApplicationProperties applicationProperties() {
        ApplicationProperties bean = new ApplicationProperties();
        bean.setReUrl(env.getProperty("application.reUrl"));
        return bean;
    }

    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

}
