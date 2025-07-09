# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)[![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml)

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/spring-projects/spring-petclinic) [![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=7517918)

## Understanding the Spring Petclinic application with a few diagrams

[See the presentation here](https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application)

## 项目架构概述

Spring Pet Clinic 是一个基于现代 Spring Boot 技术栈的宠物诊所管理系统，采用经典的 MVC 架构模式，具有以下特点：

### 📁 项目结构

```
src/
├── main/
│   ├── java/                    # Java 源代码
│   ├── kotlin/                  # Kotlin 源代码（双语言支持）
│   └── resources/
│       ├── application.properties
│       ├── db/                  # 数据库脚本
│       │   ├── h2/
│       │   ├── mysql/
│       │   └── postgres/
│       ├── templates/           # Thymeleaf 模板
│       └── static/              # 静态资源
└── test/                        # 测试代码
```

### 🏗️ 技术架构

#### 核心技术栈
- **框架**: Spring Boot 3.5.0
- **语言**: Java 17+ 和 Kotlin 2.1.0 双语言支持
- **Web**: Spring MVC + Thymeleaf 模板引擎
- **数据访问**: Spring Data JPA + Hibernate
- **数据库**: H2 (默认), MySQL, PostgreSQL
- **缓存**: Spring Cache + Caffeine
- **前端**: Bootstrap 5.3.6 + Font Awesome
- **构建工具**: Maven 和 Gradle 双构建支持

#### 业务模块划分

**1. 模型层 (Model)**
- `BaseEntity`: 基础实体类，提供ID和新建状态判断
- `NamedEntity`: 命名实体基类，继承自BaseEntity
- `Person`: 人员基类，包含姓名属性

**2. 业务模块**
- **Owner 模块**: 宠物主人管理
  - `Owner`: 主人实体（地址、电话等）
  - `Pet`: 宠物实体（生日、类型等）
  - `Visit`: 就诊记录
  - `PetType`: 宠物类型

- **Vet 模块**: 兽医管理
  - `Vet`: 兽医实体
  - `Specialty`: 专业技能

- **System 模块**: 系统功能
  - 欢迎页面
  - 错误处理
  - 缓存配置

#### 分层架构设计

**表现层 (Controller)**
- `OwnerController`: 主人信息管理
- `PetController`: 宠物信息管理
- `VisitController`: 就诊记录管理
- `VetController`: 兽医信息管理
- `WelcomeController`: 首页控制器

**业务层**
- 使用 Spring Data JPA Repository 模式
- 自定义验证器（如 `PetValidator`）
- 业务逻辑内嵌在 Controller 中（适合小型项目）

**数据访问层**
- `OwnerRepository`: 主人数据访问
- `PetTypeRepository`: 宠物类型数据访问
- `VetRepository`: 兽医数据访问
- 支持分页查询和自定义查询

### 🔧 关键特性

#### 多数据库支持
- **H2**: 内存数据库，默认配置，适合开发和演示
- **MySQL**: 生产环境关系型数据库
- **PostgreSQL**: 企业级关系型数据库
- 通过 Spring Profile 切换：`spring.profiles.active=mysql|postgres`

#### 缓存机制
- 使用 `@Cacheable` 注解缓存兽医信息
- 基于 JCache 标准，使用 Caffeine 作为缓存提供者
- 支持缓存统计和监控

#### 数据验证
- Bean Validation (JSR-303) 标准验证
- 自定义验证器 `PetValidator`
- 前后端双重验证保证数据完整性

#### 国际化支持
- 多语言消息资源文件
- 支持德语、英语、西班牙语、波斯语、韩语、葡萄牙语、俄语、土耳其语

### 🐳 部署架构

#### Docker 支持
- 使用 Spring Boot 构建插件创建容器镜像
- `docker-compose.yml` 提供完整的开发环境
- 支持 MySQL 和 PostgreSQL 容器化部署

#### Kubernetes 支持
- `k8s/` 目录包含 Kubernetes 部署配置
- 支持 Service、Deployment 等资源定义
- 配置健康检查和就绪探针

#### 监控与运维
- Spring Boot Actuator 提供应用监控端点
- 支持健康检查、指标收集、应用信息展示
- 可集成 Prometheus、Grafana 等监控工具

### 🧪 测试策略

#### 测试类型
- **单元测试**: JUnit 5 + Mockito
- **集成测试**: Spring Boot Test + Testcontainers
- **Web 层测试**: MockMvc 测试控制器
- **性能测试**: JMeter 测试计划

#### 测试数据库
- 使用 Testcontainers 在测试中启动真实数据库
- 支持 MySQL 和 PostgreSQL 的集成测试
- 测试数据通过 SQL 脚本初始化

### 📈 可扩展性设计

- **模块化设计**: 按业务功能分包，便于扩展
- **接口驱动**: Repository 接口便于替换实现
- **配置外部化**: 支持多环境配置
- **双语言支持**: Java 和 Kotlin 代码共存
- **多构建工具**: 同时支持 Maven 和 Gradle

这个架构设计体现了 Spring Boot 的最佳实践，适合作为企业级应用的参考模板。

## Run Petclinic locally

Spring Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/) or [Gradle](https://spring.io/guides/gs/gradle/). You can build a jar file and run it from the command line (it should work just as well with Java 17 or newer):

```bash
git clone https://github.com/spring-projects/spring-petclinic.git
cd spring-petclinic
./mvnw package
java -jar target/*.jar
```

(On Windows, or if your shell doesn't expand the glob, you might need to specify the JAR file name explicitly on the command line at the end there.)

You can then access the Petclinic at <http://localhost:8080/>.

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this, it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```bash
./mvnw spring-boot:run
```

> NOTE: If you prefer to use Gradle, you can build the app using `./gradlew build` and look for the jar file in `build/libs`.

## Building a Container

There is no `Dockerfile` in this project. You can build a container image (if you have a docker daemon) using the Spring Boot build plugin:

```bash
./mvnw spring-boot:build-image
```

## In case you find a bug/suggested improvement for Spring Petclinic

Our issue tracker is available [here](https://github.com/spring-projects/spring-petclinic/issues).

## Database configuration

In its default configuration, Petclinic uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is exposed at `http://localhost:8080/h2-console`,
and it is possible to inspect the content of the database using the `jdbc:h2:mem:<uuid>` URL. The UUID is printed at startup to the console.

A similar setup is provided for MySQL and PostgreSQL if a persistent database configuration is needed. Note that whenever the database type changes, the app needs to run with a different profile: `spring.profiles.active=mysql` for MySQL or `spring.profiles.active=postgres` for PostgreSQL. See the [Spring Boot documentation](https://docs.spring.io/spring-boot/how-to/properties-and-configuration.html#howto.properties-and-configuration.set-active-spring-profiles) for more detail on how to set the active profile.

You can start MySQL or PostgreSQL locally with whatever installer works for your OS or use docker:

```bash
docker run -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:9.2
```

or

```bash
docker run -e POSTGRES_USER=petclinic -e POSTGRES_PASSWORD=petclinic -e POSTGRES_DB=petclinic -p 5432:5432 postgres:17.5
```

Further documentation is provided for [MySQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/mysql/petclinic_db_setup_mysql.txt)
and [PostgreSQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/postgres/petclinic_db_setup_postgres.txt).

Instead of vanilla `docker` you can also use the provided `docker-compose.yml` file to start the database containers. Each one has a service named after the Spring profile:

```bash
docker compose up mysql
```

or

```bash
docker compose up postgres
```

## Test Applications

At development time we recommend you use the test applications set up as `main()` methods in `PetClinicIntegrationTests` (using the default H2 database and also adding Spring Boot Devtools), `MySqlTestApplication` and `PostgresIntegrationTests`. These are set up so that you can run the apps in your IDE to get fast feedback and also run the same classes as integration tests against the respective database. The MySql integration tests use Testcontainers to start the database in a Docker container, and the Postgres tests use Docker Compose to do the same thing.

## Compiling the CSS

There is a `petclinic.css` in `src/main/resources/static/resources/css`. It was generated from the `petclinic.scss` source, combined with the [Bootstrap](https://getbootstrap.com/) library. If you make changes to the `scss`, or upgrade Bootstrap, you will need to re-compile the CSS resources using the Maven profile "css", i.e. `./mvnw package -P css`. There is no build profile for Gradle to compile the CSS.

## Working with Petclinic in your IDE

### Prerequisites

The following items should be installed in your system:

- Java 17 or newer (full JDK, not a JRE)
- [Git command line tool](https://help.github.com/articles/set-up-git)
- Your preferred IDE
  - Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, follow the install process [here](https://www.eclipse.org/m2e/)
  - [Spring Tools Suite](https://spring.io/tools) (STS)
  - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  - [VS Code](https://code.visualstudio.com)

### Steps

1. On the command line run:

    ```bash
    git clone https://github.com/spring-projects/spring-petclinic.git
    ```

1. Inside Eclipse or STS:

    Open the project via `File -> Import -> Maven -> Existing Maven project`, then select the root directory of the cloned repo.

    Then either build on the command line `./mvnw generate-resources` or use the Eclipse launcher (right-click on project and `Run As -> Maven install`) to generate the CSS. Run the application's main method by right-clicking on it and choosing `Run As -> Java Application`.

1. Inside IntelliJ IDEA:

    In the main menu, choose `File -> Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.

    - CSS files are generated from the Maven build. You can build them on the command line `./mvnw generate-resources` or right-click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.

    - A run configuration named `PetClinicApplication` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right-clicking on the `PetClinicApplication` main class and choosing `Run 'PetClinicApplication'`.

1. Navigate to the Petclinic

    Visit [http://localhost:8080](http://localhost:8080) in your browser.

## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [PetClinicApplication](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java) |
|Properties Files | [application.properties](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

## Interesting Spring Petclinic branches and forks

The Spring Petclinic "main" branch in the [spring-projects](https://github.com/spring-projects/spring-petclinic)
GitHub org is the "canonical" implementation based on Spring Boot and Thymeleaf. There are
[quite a few forks](https://spring-petclinic.github.io/docs/forks.html) in the GitHub org
[spring-petclinic](https://github.com/spring-petclinic). If you are interested in using a different technology stack to implement the Pet Clinic, please join the community there.

## Interaction with other open-source projects

One of the best parts about working on the Spring Petclinic application is that we have the opportunity to work in direct contact with many Open Source projects. We found bugs/suggested improvements on various topics such as Spring, Spring Data, Bean Validation and even Eclipse! In many cases, they've been fixed/implemented in just a few days.
Here is a list of them:

| Name | Issue |
|------|-------|
| Spring JDBC: simplify usage of NamedParameterJdbcTemplate | [SPR-10256](https://github.com/spring-projects/spring-framework/issues/14889) and [SPR-10257](https://github.com/spring-projects/spring-framework/issues/14890) |
| Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility |[HV-790](https://hibernate.atlassian.net/browse/HV-790) and [HV-792](https://hibernate.atlassian.net/browse/HV-792) |
| Spring Data: provide more flexibility when working with JPQL queries | [DATAJPA-292](https://github.com/spring-projects/spring-data-jpa/issues/704) |

## Contributing

The [issue tracker](https://github.com/spring-projects/spring-petclinic/issues) is the preferred channel for bug reports, feature requests and submitting pull requests.

For pull requests, editor preferences are available in the [editor config](.editorconfig) for easy use in common text editors. Read more and download plugins at <https://editorconfig.org>. All commits must include a __Signed-off-by__ trailer at the end of each commit message to indicate that the contributor agrees to the Developer Certificate of Origin.
For additional details, please refer to the blog post [Hello DCO, Goodbye CLA: Simplifying Contributions to Spring](https://spring.io/blog/2025/01/06/hello-dco-goodbye-cla-simplifying-contributions-to-spring).

## License

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
