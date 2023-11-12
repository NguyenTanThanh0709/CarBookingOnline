package com.carbooking.carbookingonlineserver;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CarBookingOnlineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarBookingOnlineServerApplication.class, args);
    }

    @Bean
    public Cloudinary getCloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dw0gecloe",
                "api_key", "615485495432393",
                "api_secret", "vfWViXxykFyLaQ99KxR1y9kBKbw",
                "secure", true
        ));
        return cloudinary;
    }

}
