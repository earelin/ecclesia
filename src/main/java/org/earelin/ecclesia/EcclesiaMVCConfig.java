package org.earelin.ecclesia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Web configuration
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="org.earelin.ecclesia.web")
public class EcclesiaMVCConfig extends WebMvcConfigurerAdapter {
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{
            "/WEB-INF/tiles.xml"
        });
        return tilesConfigurer;
    }
    
    @Bean
    public UrlBasedViewResolver viewResolver() {
        return new TilesViewResolver();
    }

}
