package com.stark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Strak on 2017/5/10.
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class JpaApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(JpaApplication.class,args);
    }
}
