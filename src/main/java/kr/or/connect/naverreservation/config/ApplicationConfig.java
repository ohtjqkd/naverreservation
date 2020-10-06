package kr.or.connect.naverreservation.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import kr.or.connect.naverreservation.config.DBConfig;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.naverreservation"})
@Import({DBConfig.class})

public class ApplicationConfig{
	
	@Bean
	public MultipartResolver multipartResolver(MultipartHttpServletRequest request)  {
		final long maxUploadSize = 1000000;
		final int maxInMemorySize = 1000000;
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSizePerFile(maxUploadSize);
		resolver.setMaxInMemorySize(maxInMemorySize);
//		resolver.resolveMultipart(request);
//		System.out.println(resolver.isMultipart(request));
		return resolver;
	}
}
