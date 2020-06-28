package com.huida.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ImgMvcUtils implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:D:/YzImages/XianzhiImages")
                .addResourceLocations("file:D:/YzImages/ShowloveImages")
                .addResourceLocations("file:D:/YzImages/ScstateImages")
                .addResourceLocations("file:D:/YzImages/RenzhengImg")
                .addResourceLocations("file:D:/YzImages/WendaImages");
    }
}
