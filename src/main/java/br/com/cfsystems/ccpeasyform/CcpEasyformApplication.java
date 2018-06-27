package br.com.cfsystems.ccpeasyform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.cfsystems.ccpeasyform.config.property.CcpEasyFormProperty;

@SpringBootApplication
@EnableConfigurationProperties(CcpEasyFormProperty.class)
public class CcpEasyformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcpEasyformApplication.class, args);
	}
}
