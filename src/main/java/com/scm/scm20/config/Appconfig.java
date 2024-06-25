package com.scm.scm20.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class Appconfig {

    @Value("${cloudinary.cloude.name}")
    private String cloudname;

    @Value("${cloudinary.api.key}")
    private String apikey;

    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudname,
                        "api_key", apikey,
                        "api_secret", apiSecret));
    }
}
