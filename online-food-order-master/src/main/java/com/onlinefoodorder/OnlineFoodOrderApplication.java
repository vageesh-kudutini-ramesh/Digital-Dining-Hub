/**
 * The main class for the Online Food Order application.
 * Uses Spring Boot to configure and bootstrap the application.
 */
package com.onlinefoodorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineFoodOrderApplication {
    
	/**
     * The main method that starts the Online Food Order application.
     *
     * @param args Command line arguments passed to the application.
     */
	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderApplication.class, args);
	}

}
