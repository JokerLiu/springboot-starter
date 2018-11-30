package com.joker.doc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableSwagger2
@Configuration
@Slf4j
public class ApiDocAutoConfiguration {

    //	@Value("${app.profile:}")
    public static String customized_env_profile;
    //	@Value("${spring.profiles.active:}")
    public static String spring_env_profiles;
    //	@Value("${apidoc.invisible.profile.pattern:}")
    public static String invisible_profile_pattern;

    @PostConstruct
    public void init() {
        customized_env_profile = env.getProperty("app.profile", "");
        spring_env_profiles = env.getProperty("spring.profiles.active", "");
        invisible_profile_pattern = env.getProperty("apidoc.invisible.profile.pattern", "");
    }

    @Autowired(required = false)
    private ApiInfo apiInfo;

    @Autowired
    private ConfigurableEnvironment env;

    @Bean
    public ApiDoc getApi() {
        return (ApiDoc) new ApiDoc()
                .groupName("Default")
                .apiInfo(apiInfo == null ? apiInfo() : apiInfo)
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        // log.warn("当前环境：{}", env);
        // log.warn("当前应用名称：{}", env.getProperty("spring.application.name"));
        return new ApiInfoBuilder()
                .title(env.getProperty("spring.application.name") + "的API文档")
                .description("springboot-apidoc-starter自动生成的文档")
                .termsOfServiceUrl("https://github.com/JokerLiu")
                .contact(new Contact("joker", "https://github.com/JokerLiu", ""))
                //          .license("Apache License Version 2.0")
                //          .licenseUrl("https://github.com/spring/master/LICENSE")
                .version("1.0")
                .build();
    }

    // @Bean
    // public ApiDoc otherApi() {
    //     return (ApiDoc) new ApiDoc()
    //             .groupName("Other")
    //             .apiInfo(apiInfo())
    //             .select()
    //             .paths(regex("/archaius.*|/autoconfig.*|/beans.*|/channels.*|/configprops.*|/consul.*|/dump.*|/info.*|/mappings.*" +
    //                     "|/trace.*|/env.*|/pause.*|/refresh.*|/resume.*|/health.*|/heapdump.*|/jolokia.*|/metrics.*|/restart.*|/hystrix.*|/features.*|/routes.*" +
    //                     "|/auditevents.*|/loggers.*|/service-registry/instance-status.*"))
    //             .build();
    // }

}
