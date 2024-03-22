package kr.co.sboard.config;

import kr.co.sboard.interceptor.AppInfoIntercepter;
import org.apache.ibatis.ognl.OgnlRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private AppInfo appInfo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInfoIntercepter(appInfo));
    }
}
