package com.intrasoft.Api.ActivosFijos;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ActivosFijosApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args)
    {
        new ActivosFijosApplication().configure(new SpringApplicationBuilder(ActivosFijosApplication.class)).run(args);
    }
}
