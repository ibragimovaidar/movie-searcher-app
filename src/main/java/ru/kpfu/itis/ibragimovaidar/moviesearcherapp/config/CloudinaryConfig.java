package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config;

import com.cloudinary.Cloudinary;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConstructorBinding
@Configuration
@ConfigurationProperties(prefix = "app.cloudinary")
public class CloudinaryConfig {

    private String cloudName;

    private String apikey;

    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apikey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
