package com.example.webproject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class WebProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebProjectApplication.class, args);
	}
//	private CorsConfiguration buildConfig() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.addAllowedHeader("*");
//		corsConfiguration.addAllowedOrigin("*");
//		corsConfiguration.addAllowedMethod("*");
//		corsConfiguration.setAllowCredentials(true);
//		return corsConfiguration;
//	}
//
//	@Bean
//	public CorsFilter corsFilter() {
//		System.out.println("=============== 设置跨域过滤器 ================");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", buildConfig());
//		return new CorsFilter(source);
//	}
}
