package it.infocamere.application;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = { "it.infocamere.springrepository" })
@EntityScan("it.infocamere.entity")
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/views/");
		resolver.setSuffix(".jsp");

		registry.viewResolver(resolver);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		// builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		// builder.serializationInclusion(Include.NON_EMPTY);
		// i campi non annotati sono inseriti indipendendentemente dalla
		// jsonView usata
		builder.featuresToEnable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		builder.indentOutput(true).dateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		Hibernate5Module hm = new Hibernate5Module();
		// se non esplicitamente inizializzati,gli oggetti lazy load appaiono
		// null
		hm.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);

		builder.modulesToInstall(hm);
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

}