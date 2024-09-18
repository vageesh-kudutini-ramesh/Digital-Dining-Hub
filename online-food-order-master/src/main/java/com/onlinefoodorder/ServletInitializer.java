/**
 * ServletInitializer class for configuring the application when deployed as a WAR (Web Application Archive).
 * Extends SpringBootServletInitializer to enable Spring Boot features in a servlet-based deployment.
 */
package com.onlinefoodorder;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    
	 /**
     * Configures the application when deployed as a WAR.
     *
     * @param application The SpringApplicationBuilder used to configure the application.
     * @return The configured SpringApplicationBuilder with the application sources.
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OnlineFoodOrderApplication.class);
	}

}
