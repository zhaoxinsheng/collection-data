package io.nakong.config;

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .select()
//            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))           //加了ApiOperation注解的方法，生成接口文档
//            //.apis(RequestHandlerSelectors.basePackage("io.renren.modules.job.controller"))  //包下的类，生成接口文档
//            .paths(PathSelectors.any())
//            .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//            .title("人人开源")
//            .description("renren-fast文档")
//            .termsOfServiceUrl("http://www.renren.io")
//            .version("2.0")
//            .build();
//    }
//
//}