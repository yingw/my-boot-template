# My Boot Template

My Boot Template 是基于 Spring Boot 和 AdminLTE 制作的 Web 应用开发模板，主要包括这些组件：

## 前端

- [AdminLTE](https://adminlte.io/)
    - Bootstrap
    - jQuery
    - DataTables 数据表格
    - iCheck 单选框和多选框
    - bootstrap date picker 日期选择控件
    - bootstrap date range picker 日期范围选择控件
    - jQuery Validation jQuery 的验证控件
    - select2 选择框控件
    - font awesome 图标
- [Thymeleaf](http://www.thymeleaf.org/)
- [Spring MVC](http://projects.spring.io/spring-framework/)
- [Spring HATEOAS](http://projects.spring.io/spring-hateoas/)
- [Swagger](https://swagger.io/)
- [HAL Browser](https://github.com/mikekelly/hal-browser)


## 后端

- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring Data](http://projects.spring.io/spring-data/)
- [Spring Security](https://projects.spring.io/spring-security/)
- H2
- JPA
- [Lombok](https://projectlombok.org/)
- [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin)
- Redis
- [Druid](https://github.com/alibaba/druid)

## 其他

对 AdminLTE 做了少量修改，如：增加国内 Google Fonts 的代理；调整 skin；增加了一些控件；做了一些控件的升级和汉化。

最后基于 Spring Boot 和 Spring Security 等技术做了一套简单的权限管理系统，也作为开发前后端代码的模板。

## 使用

- 首页 [http://localhost:8080](http://localhost:8080)
- Druid [http://localhost:8080/druid](http://localhost:8080)
- H2 Console [http://localhost:8080/h2-console](http://127.0.0.1:8080/h2-console)

账号和密码
```
spring.datasource.url=jdbc:h2:~/my-boot
spring.datasource.username=sa
spring.datasource.password=
```

- Druid

账号和密码
```
spring.datasource.druid.StatViewServlet.loginUsername=druid
spring.datasource.druid.StatViewServlet.loginPassword=druid
```

- Spring Boot Admin [http://localhost:8080/admin](http://localhost:8080/admin)
- HAL Browser [http://localhost:8080/actuator](http://localhost:8080/actuator)

## 系统截图

![image](http://note.youdao.com/yws/public/resource/86cc310f1857fe0e7059989fd25bf399/xmlnote/BA57B2641304400796C577E691407145/10850)
![image](http://note.youdao.com/yws/public/resource/86cc310f1857fe0e7059989fd25bf399/xmlnote/FA31D92C0F43498BBF7DBFCE8393CB12/10851)
