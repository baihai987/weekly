//package com.hyy.kcbweekly.weekly.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author xrw
// * 跨域请求配置
// */
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    //从配置文件中读取允许访问的非本机地址
//    @Value("${wedOrigin.name}")
//    private String wn;
//
//    //允许跨域请求
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        //遍历数组，将允许访问的地址添加进去
//        String[] result = wn.split(",");
//        for (int i = 0; i < result.length; i++) {
//            corsConfiguration.addAllowedOrigin(result[i]);
//        }
//
//        //这一句表示允许任何地址访问服务器
//        //corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }
//}
