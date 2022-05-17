package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
@EnableConfigurationProperties(AppImageProperties.class)
public class AppConfig {
}
