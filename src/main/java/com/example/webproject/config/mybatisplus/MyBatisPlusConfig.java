package com.example.webproject.config.mybatisplus;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MyBatisPlusConfig {
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
        }
//    @Bean
//    public GlobalConfig globalConfig() {
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(MetaHandler.of());
//        return globalConfig;
//    }
}

