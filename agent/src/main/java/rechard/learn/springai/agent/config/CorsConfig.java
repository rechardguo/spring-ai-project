package rechard.learn.springai.agent.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域的域名（来源），* 表示允许所有域名
                .allowedOriginPatterns("*")
                // 设置允许的 HTTP 方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 是否允许发送 Cookie
                .allowCredentials(true)
                // 设置预检请求的缓存时间（秒）
                .maxAge(3600)
                // 设置允许的请求头
                .allowedHeaders("*");
    }
}