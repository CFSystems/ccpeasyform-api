package br.com.cfsystems.ccpeasyform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.cfsystems.ccpeasyform.config.property.CcpEasyFormProperty;

@SpringBootApplication
@EnableConfigurationProperties(CcpEasyFormProperty.class)
public class CcpEasyformApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CcpEasyformApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CcpEasyformApplication.class, args);
	}
}
