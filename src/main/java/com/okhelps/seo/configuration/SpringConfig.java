package com.okhelps.seo.configuration;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.okhelps.seo.bean.ObjectIdSerializer;

@Configuration
@ComponentScan(basePackages = { "com.okhelps.seo" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = SpringWebConfig.class))
@PropertySource("classpath:config.properties")
public class SpringConfig {

	// @Bean
	// public ObjectMapper mapper() {
	// ObjectMapper mapper = new ObjectMapper();
	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	// // mapper.convertValue(Object o, toValueType)
	// return mapper;
	// }

	@Bean(name = "mapper")
	public ObjectMapper mapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		// defaultDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleModule objectIdModule = new SimpleModule("ObjectIdModule");
		objectIdModule.addSerializer(new ObjectIdSerializer());
		mapper.registerModule(objectIdModule);
		
		
		// mapper.setDateFormat(defaultDateFormat);
		return mapper;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
