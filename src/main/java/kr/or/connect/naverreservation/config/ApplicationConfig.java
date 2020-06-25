package kr.or.connect.naverreservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.or.connect.naverreservation.config.DBConfig;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.naverreservation"})
@Import({DBConfig.class})
public class ApplicationConfig {
	
}
