package com.junfly.water.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by bj on 2016/12/20.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("waterApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false).forCodeGeneration(false)
                // 过滤的接口
                .pathMapping("/").select().paths(Predicates.or(PathSelectors.regex("/water/.*")))
                .build().apiInfo(someWebApiInfo());
    }

    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("adminApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false).forCodeGeneration(false)
                // 过滤的接口
                .pathMapping("/").select().paths(Predicates.or(PathSelectors.regex("/admin_api/.*")))
                .build().apiInfo(someWebApiInfo());
    }

    @Bean
    public Docket frontApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("frontApi")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false).forCodeGeneration(false)
                // 过滤的接口
                .pathMapping("/").select().paths(Predicates.or(PathSelectors.regex("/front_api/.*")))
                .build().apiInfo(someWebApiInfo());
    }

    private ApiInfo someWebApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "智慧水务",// 大标题
                "API",// 小标题
                "1.0.0",// 版本
                "pengqiang", new Contact("pengqiang", "", "pq27120@126.com"),// 作者
                "版权所有@俊翔科技有限公司",// 链接显示文字
                ""// 网站链接
        );
        return apiInfo;
    }
}
