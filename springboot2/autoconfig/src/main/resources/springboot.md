# 走向自动装配

## spring 模式注解装配

### 模式注解(Steretype Annotation)

> https://github.com/spring-projects/spring-framework/wiki/spring-annotation-programming-model
>
> A ***stereotype annotation*** is an annotation that is used to declare the role that a component plays within the application. For example, the `@Repository` annotation in the Spring Framework is a marker for any class that fulfills the role or *stereotype* of a repository (also known as Data Access Object or DAO).
>
> `@Component` is a generic stereotype for any Spring-managed component. Any component annotated with `@Component` is a candidate for component scanning. Similarly, any component annotated with an annotation that is itself meta-annotated with `@Component` is also a candidate for component scanning. For example, `@Service` is meta-annotated with `@Component`.

- spring模式注解装配: 一种用于申明在应用中扮演**组件**角色的注解

- **组件**例子: `@Component`(spring 2.5) `@Service`(spring 2.5) `@Configuration`(spring 3)

- 装配:

  ```
  spring 2.5
  <context:annotation-config/> 
  <context:component-scan base-package=""/>
  ```

  或者`@ComponentScan(base-package="")`(spring 3.1)

#### 自定义模式注解

##### `@Component` 派生性

```
/**
 * 一级 {@link Repository @Repository}
 * 
 * created by XUAN on 2019/09/21
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface FirstLevelRepository {
    String value() default "";
}
```

##### `@Component` 层次性

```
/**
 * 二级 {@link Repository @Repository}
 *
 * @author xuan
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelRepository
public @interface SecondLevelRepository {
    String value() default "";
}
```

`@Component`

- `@Repository`
  - `@FirstLevelRepository`
    - `@SecondLevelRepository`



## Spring `@Enable` 模块装配

Spring framework 3.1开始支持"@Enable模块驱动". 所谓"模块"是指具备相同领域的功能组件集合, 组合所形成的一个独立单元. 如 Web MVC模块, Aspectj代理模块, Caching(缓存)模块, JMX(java管理扩展)模块, Asyns(异步处理)模块等.

### `@Enable`注解模块举例

| 框架实现         | `@Enable`注解模块                | 激活模块           |
| ---------------- | -------------------------------- | ------------------ |
| Spring framework | `@EnableWebMvc`                  | Web MVC 模块       |
|                  | `@EnableTransactionManagement`   | 事务管理模块       |
|                  | `@EnableCaching`                 | 缓存模块           |
|                  | `@EnableMBeanExport`             | JMX模块            |
|                  | `@EnableAsyn`                    | 异步处理模块       |
|                  | `@EnableWebFlux`                 | Web Flux模块       |
|                  | `@EnableAsectJAutoProxy`         | AspectJ模块        |
| Spring boot      | `@EnableAutoConfiguration`       | 自动装配模块       |
|                  | `@EnableManagementContext`       | Actuator模块       |
|                  | `@EnableConfigurationProperties` | 配置属性绑定模块   |
|                  | `@EnableOAuth2Sso`               | OAuth2单点登录模块 |
| Spring cloud     | `@EnableEurekaServer`            | Eureka服务器模块   |
|                  | `@EnableConfigServer`            | 配置服务器模块     |
|                  | `@EnableFeginClients`            | Fegin客户端模块    |
|                  | `@EnableZuulProxy`               | 服务网关zuul模块   |
|                  | `@EnableCircuitBreaker`          | 服务熔断模块       |



### 实现方式

#### 注解驱动方式

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {}
```

#### 接口编程方式

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CachingConfigurationSelector.class)
public @interface EnableCaching {}
```



### 自定义`@Enable`模块

#### 基于注解驱动实现 - `@EnableHelloWorld` 3.0

示例: start项目中的`EnableHelloWorldBootstrap`类

```
@Import(HelloWorldConfiguration.class)
```

获得 `@Configuration`标注的类所提供的所有`@Bean`

#### 基于接口驱动实现  3.1

示例:

```
@Import(HelloWorldImportSelector.class)
```

获得 `ImportSelector.selectImports()` 方法提供的所有class的名称



## Spring 条件装配

从 Spring framework 3.1 开始, 允许在Bean装配时增加前置条件判断

### 条件注解举例

| Spring 注解    | 场景说明       | 起始版本 |
| -------------- | -------------- | -------- |
| `@Profile`     | 配置化条件装配 | 3.1      |
| `@Conditional` | 编程条件装配   | 4.0      |
|                |                |          |



### 实现方式

#### 配置方式 - `@Profile`

#### 编程方式 - `@Condition`

| 注解                            | 解析类                | 作用 | 起始版本 |
| :------------------------------ | --------------------- | ---- | -------- |
| `@Conditional`                  |                       |      |          |
| `@ConditionalOnClass`           | `OnClassCondition`    |      |          |
| `@ConditionalOnBean`            | `OnBeanCondition`     |      |          |
| `@ConditionalOnMissingBean`     | `OnBeanCondition`     |      |          |
| `@ConditionalOnMissingClass`    | `OnClassCondition`    |      |          |
| `@ConditionalOnProperty`        | `OnPropertyCondition` |      |          |
| `@ConditionalOnSingleCandidate` | `OnBeanCondition`     |      |          |
| `@Import`                       |                       |      |          |
| `@AutoConfigureAfter`           |                       |      |          |



### 自定义条件装配

#### 基于配置方式实现 - `@Profile`

#### 基于编程方式实现 - `@ConditionalOnSystemProperty`



## Spring boot 自动装配

在spring boot 场景下, 基于约定大于配置的原则, 实现spring 组件自动装配的目的. 其中使用了



### 底层装配技术

- spring模式注解装配
- spring `@Eable` 模块装配
- spring 条件装配装配
- spring 工厂加载机制
  - 实现类: `SpringFactoriesLoader`
  - 配置资源: `MATE-INF/spring-factories`

### 自动装配举例

参考 `MATE-INF/spring-factories`



### 实现方法

1. 激活自动装配 - `@EnableAutoConfiguration`
2. 实现自动装配实现类 - `XXXAutoConfiguration`
3. 配置自动装配实现类至 `MATE-INF/spring-factories` 参考`spring-boot-autoconfigure`下的`MATE-INF/spring-factories`



#### 自定义自动装配
`HelloWorldAutoConfiguration`

-   判断条件: `user.name == "MSI-PC"`
-   模式注解: `@Configuration`
-   `@Enable`模块: `@EnableHelloWorld` -> `HelloWorldImportSelector` -> `HelloWorldConfiguration` -> `HelloWorld`


# 理解SpringApplication

## `SpringApplication`运行
``` java
    public static void main(String[] args) {
        SpringApplication.run(Springboot2AutoconfigApplication.class, args);
    }
```

## 自定义`SpringApplication`

### 通过`SpringApplication`Api调整
``` java
SpringApplication springApplication = SpringApplication(Springboot2AutoconfigApplication.class);
springApplication.setBannerMode(Banner.Mode.CONSOLE);
springApplication.setWebApplicationType(WebApplicaitonType.NONE);
springApplication.setAdditionalProfiles("prod");
springApplication.setHeadless(true);
```

### 通过`SpringApplicationBuilder`Api调整
``` java
new SpringApplicationBuilder(Springboot2AutoconfigApplication.class)
    .bannerMode(Banner.Mode.CONSOLE);
    .webApplicationType(WebApplicaitonType.NONE)
    .additionalProfiles("prod")
    .headless(true)
    .run(args);
```

## `SpringApplication`准备阶段
### 配置Springboot bean源

[java配置class] 或 [xml上下文配置文件集合], 用于springboot `BeanDefinitionLoader`读取, 并且将配置源解析加载为 spring bean 

-   数量: 一个或多个

### java配置class
用于spring注解驱动中java配置类, 大多数情况是spring模式注解所标注的类, 如@Configuration

### xml上下文配置文件集合
用于spring 传统配置驱动中的xml文件

## 推断web应用类型
根据当前应用classpath中是否存在相关实现类来推断web应用的类型, 包括:





## 数据相关

- JDBC: 数据源,` JDBCTemplate`, 自动装配

  - ``` java
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    ```

  - 数据源:  `javax.sql.DataSource`

  - 自动装配:`DataSourceAutoConfigration`

- JPA: 实体映射关系, 实体操作, 自动装配

  - ``` java
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

  - 实体映射关系

    - `@javax.persistence.OneToOne`
    - `javax.persistence.OneToMany`
    - `@javax.persistence.ManyToOne`
    - `@javax.persistence.ManyToMany`
    
  - 实体操作 `javax.persistence.EntityManager`

  - 自动装配`HibernateJpaAutoConfiguration`


- 事务: Spring事务抽象, JDBC事务处理, 自动装配

  - ```
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-tx</artifactId>
    </dependency>
    ```

  - spring事务抽象,包括数据库事务和分布式事务`PlatformTransactionManager`

  - JDBC事务处理`DataSourceTransactionManager`

  - 分布式事务`JtaTransactionManager`

  - 自动装配`TransactionAutoConfiguration`





## 功能扩展

- SpringApplication: 
  - 失败分析`FailureAnalysisReporter`
  - 应用特性`SpringApplication` Fluent API
  - 事件监听
- spingboot配置
  - 外部化配置`ConfigurationProperty`
  - `@profile`
  - 配置属性`PorpertySource`
- springboot starter
  - starter开发
  - 最佳实践



## 运维管理

- Spring Boot Actuator

  - ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    ```

  - 端点(EndPoints): 各类 Web EndPoints 和 JMX EndPoints

  - 健康检查: Heath  HeathIndicator

  - 指标: 

    - 内建Metrics EndPoints:`/actuator/metrics`
    -  自定义Metrics

