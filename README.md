# "Spring Boot:four_leaf_clover:"

Spring Framework만 사용해서 개발해봤는데 ,

이번 기회에 Spring Boot를 공부하면서 다시 한번 Spring 도 복습해본다.



https://goddaehee.tistory.com/category/3.%20%EC%9B%B9%EA%B0%9C%EB%B0%9C/3_1_3%20%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

해당 블로그가 많이 도움이 되었다. 



------

**IDE tool** : intellij (개인적으로 git연동 프로젝트 - intellij / svn - eclipse 호환성이 좋은듯)

**DB** : MariaDB

![](/src/main/resources/static/study/image/springboot.png)



#### 🚩Spring Boot와 일반 Spring의 차이?

 **- Spring Boot makes it easy to create stand-alone.**  단독실행가능한 스프링애플리케이션을 생성한다.  

**- Most Spring Boot applications need very little Spring configuration.**  Spring Boot는 최소한의 초기 스프링 구성으로 가능한 한 빨리 시작하고 실행할 수 있도록 설계되었다.



#### 🚩Spring Boot의 장점

**- 반복되는 개발환경 구축을 위한 코드작성등의 노력을 줄여주고 쉽고 빠르게 프로젝트를 설정할 수 있도록 도와준다.**

 **- 프로젝트 환경 구축에서 큰 영역을 차지하는 비기능적인 기능들을 기본적으로 제공한다.**  **(내장형 서버, 시큐리티, 측정, 상태 점검, 외부 설정)**



### **1. 기본설정**

1. pom.xml - maven을 통한 설정

2. tomcat 설정 및 프로젝트 설정파일

   :point_right: application.properties에서 설정 ( port, prefix, suffix등 여러가지 설정)

   

### **2. Controller 설정**

​    :heavy_check_mark:**기본 annotation**

​			- @Controller vs @RestController

​			- @RequestMapping (Spring 4.3이후)

​				ex) 🔔<u>@RequestMapping(value="/login", method=@RequestMethod.GET)  ==  @GetMapping("/login")</u> 

​      			RequestMethod.GET 뿐만 아니라, POST , PUT , DELETE도 동일하게 사용가능

​      			4.3이후에는 많이 사용되는 소스인 것 같다. 알아두면 편리하게 사용할듯 

​			- @RequestParam

​				ex) public String member(@RequestParam String name) == public String member(String name)

​			- @PathValue 

​				ex) @RequestMapping("/member/**{name}**/**{age}**")

​      				 public String member(@PathVariable("**name**") String name, @PathVariable("**age**") String age)

​			- consumes : RequestBody 타입 설정

​				ex) @PostMapping("/login", comsumes="application/json")

​			- @ResponseBody : view가 아닌 data를 반환하는 경우, ajax 통신에 주로쓰임

​			- <u>**@RestController**</u>  : @ResponseBody 추가할 필요 없이 ,@ResponseBody 기본적으로 활성화되어 있음

​				Spring 4.0이상은 @Controller와 @ResponseBody 어노테이션을 추가하는 것 대신 @RestController을 제공하다.



###  **3. view 및 jsp 연동**

:heavy_check_mark:**Thymeleaf Template** (view를 연결해주는 템플릿을 이용)

​	기존의 tiles Template를 이용해봤으므로, 새로운 Thymeleaf Template을 이용해보자!

- pom.xml 에서 Thymeleaf 를 이용할수 있게 의존성 추가

  :curly_loop:jsp 서버 재시작 없이 설정 : pom.xml에 추가 설정 

  ```xml
  <!-- Thymeleaf Template -->
  <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
      <scope>provided</scope>
  </dependency>
  
  <!-- jstl 라이브러리 -->
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
  </dependency>
  
  <!-- jsp reload없이 반영 -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
  </dependency>
  ```



### **4. DB 연동**(MariaDB 이용)

:heavy_check_mark:Connection pool 이용하기

* pom.xml : mariadb 의존성 추가		
* application.properties : db 접속정보 설정



​		:heavy_check_mark:HikariCP

​			-	DataBase와의 Connection Pool을 관리(Connection Pool을 관리하는 라이브러리의 한 종류)

​				-	미리 정해놓은 만큼의 커넥션을 Pool에 담아 놓는다. 

​				-	요청이 들어오면 Thread가 커넥션을 요청하고 , Hikari는 Pool내에 있는 커넥션을 연결해준다.

​				📣 단, 커넥션이 끝나더라도 해당 커넥션을 반납하지 않는다. Pool에 계속 살려둔다. 그러므로 설정값 주의필요!

​		    	    *자세한 HikariCP 설정 👉[https://brunch.co.kr/@jehovah/24](https://brunch.co.kr/%40jehovah/24)*





### **5 Logback 설정(Spring boot)**

- Spring Boot의 기본 설정, 따로 별도의 라이브러리 추가할 필요 X
- spring-boot-starter-web 안에 spring-boot-starter-logging에 구현체가 있다

- log4j, log4j2보다 성능이 좋다고 함

|            | spring      | spring boot                                    |
| ---------- | ----------- | ---------------------------------------------- |
| /resource/ | logback.xml | logback-spring.xml  or  application.properties |

​		:heavy_check_mark:로그 레벨

​		  TRACE < DEBUG < INFO < WARN < ERROR



​		 :heavy_check_mark: application.properties 설정

```properties
# 루트 레벨(전체 레벨) 전체 로깅 레벨 지정
logging.level.root=info

# 패키지별로 로깅 레벨 지정
logging.level.com.god.bo.test=info
logging.level.com.god.bo.test.controller=debug
```

​		 

​		:heavy_check_mark: logback-spring.xml 설정

​				**appender**와 **logger** 크게 두개로 구분

```xml
<configuration scan="true" scanPeriod="60 seconds">
	<springProfile name="local">
    	<property resource="logback-local.properties"/>
    </springProfile>
    
    <!--Environment 내의 프로퍼티들을 개별적으로 설정할 수도 있다.-->
	<springProperty scope="context" name="LOG_LEVEL" source="logging.level.root"/>

    <!-- log file path -->
    <property name="LOG_PATH" value="${log.config.path}"/>
    
    <!-- pattern -->
    <property name="LOG_PATTERN" value="%-5level %d{yy-MM-dd HH:mm:ss}[%thread] [%logger{0}:%line] - %msg%n"/>

    <!-- Console Appender -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
        	<pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>
    
    ...
    ...
</configuration>
```





### **6. Logback 설정(JDBC)**

:heavy_check_mark:pom.xml 설정

```xml
<!-- DB Logback -->
<dependency>
    <groupId>org.bgee.log4jdbc-log4j2</groupId>
    <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
    <version>1.16</version>
</dependency>
```



:heavy_check_mark: logback-spring.xml 설정

```xml
<!-- log4jdbc 옵션 설정 -->
<logger name="jdbc" level="OFF"/>
<!-- 커넥션 open close 이벤트를 로그로 남긴다. -->
<logger name="jdbc.connection" level="OFF"/>
<!-- SQL문만을 로그로 남기며, PreparedStatement일 경우 관련된 argument 값으로 대체된 SQL문이 보여진다. -->
<logger name="jdbc.sqlonly" level="OFF"/>
<!-- SQL문과 해당 SQL을 실행시키는데 수행된 시간 정보(milliseconds)를 포함한다. -->
<logger name="jdbc.sqltiming" level="DEBUG"/>
<!-- ResultSet을 제외한 모든 JDBC 호출 정보를 로그로 남긴다. 많은 양의 로그가 생성되므로 특별히 JDBC 문제를 추적해야 할 필요가 있는 경우를 제외하고는 사용을 권장하지 않는다. -->
<logger name="jdbc.audit" level="OFF"/>
<!-- ResultSet을 포함한 모든 JDBC 호출 정보를 로그로 남기므로 매우 방대한 양의 로그가 생성된다. -->
<logger name="jdbc.resultset" level="OFF"/>
<!-- SQL 결과 조회된 데이터의 table을 로그로 남긴다. -->
<logger name="jdbc.resultsettable" level="OFF"/>
```





### **7. JPA 설정**

:heavy_check_mark: **ORM - JPA - Hibernate 정의**

| ORM       | Object-Relational Mapping<br />객체가 테이블이 되도록 매핑 시켜주는 프레임워크<br />ex) 기존쿼리 : SELECT * FROM MEMBER;<br />       Member테이블과 매핑된 객체가 member라고 할 때, member.findAll()이라는 메서드 호출로 데이터 조회가 가능 |
| --------- | ------------------------------------------------------------ |
| JPA       | Java Persistence API<br />Hibernate, OpenJPA 등이 JPA를 구현한 구현체 |
| Hibernate | JPA를 사용하기 위해서 JPA를 구현한 ORM 프레임워크중 하나     |



:heavy_check_mark: **JPA를 사용하는 이유​ **

​		: 간단하게 말하자면, CRUD 반복되는 피하고자 함(컬럼 하나만 추가되더라도 DTO, DAO 등 추가작업이 많음)

​        :warning:**하지만, 업무환경에 따라 JPA를 적용할 수 없는 경우가 많다. (통계처리와 같은 복잡한 쿼리에 부적합)**

​        

| **장점** | 1. 생산성이 뛰어나고 유지보수가 용이<br />- 객체 지향적인 코드로 인해 더 직관적이고 비즈니스 로직에 더 집중할 수 있게 도와준다. <br />- 객체지향적으로 데이터를 관리할 수 있기 때문에 전체 프로그램 구조를 일관되게 유지할 수 있다. <br />- SQL을 직접적으로 작성하지 않고 객체를 사용하여 동작하기 때문에 유지보수가 더욱 간결하고, <br />  재사용성도 증가하여 유지보수가 편리해진다. <br />- DB컬럼이 추가될 때마다 테이블 수정이나 SQL 수정하는 과정이 많이 줄어들고, 값을 할당하거나, 변수 선언등의 부수적인 코드 또한 급격히 줄어든다. <br />- 각각의 객체에 대한 코드를 별도로 작성하여 코드의 가독성도 올라간다.<br /><br /><br />2.DBMS에 대한 종속성이 줄어든다<br />\- DBMS가 변경된다 하더라도 소스, 쿼리, 구현 방법, 자료형 타입 등을 변경할 필요가 없다. <br />- 즉 프로그래머는 Object에만 집중하면 되고, DBMS를 교체하는 작업에도 비교적 적은 리스크와 시간이 소요된다. |
| -------- | ------------------------------------------------------------ |
| **단점** | 1.어렵다. <br />- JPA의 장점을 살려 잘 사용하려면 학습 비용이 높은 편이다. <br />- 복잡한 쿼리를 사용해야 할 때에 불리하다.  업무 비즈니스가 매우 복잡한 경우 JPA로 처리하기 어렵고, 통계처리와 같은 복잡한 쿼리 자체를 ORM으로 표현하는데 한계가 있다.  (실시간 처리용 쿼리에 더 최적화되어 있다고 봐도 무방할 것이다.) <br />- 결국 기존 데이터베이스 중심으로 되어 있는 환경에서는 JPA를 사용하기도 어렵고, 힘을 발휘하기 어렵다. <br />- 잘못사용할 경우 실제 SQL문을 직접 작성하는 것보다는 성능이 비교적 떨어질 수 있다. <br />- 대용량 데이터 기반의 환경에서도 튜닝이 어려워 상대적으로 기존 방식보다 성능이 떨어질 수 있다. |



:heavy_check_mark:JPA 설정

- pom.xml 설정	
- **Repository 클래스 설정** : Mapper 대신 사용
- Service , Controller 설정



### 8. Junit 설정 및 실행

조금만 더 일찍 공부했었더라면,,, 아쉬움이 남는 부분이다.. 진작 공부할걸!!😭😭😭



:heavy_check_mark:Junit 설정하기

​		1.	test 디렉토리 설정하기

![](/src/main/resources/static/study/image/juint.png)



​				👉 src/test 하위에 테스트 대상 클래스와 동일한 클래스 패키지로 생성 ( /test 디렉토리 없는 경우 생성)

​			      쉽게 말하면,  빨간색 MemberVO파일과 노란색 MemberVOTest 파일의 패키지(com.example.naflex.jpaTest.vo)가 동일해야 한다!!



​			2.  모듈 설정

​                  - 위 이미지에서 test > java 폴더만 초록색으로 설정되어 있는데 다음과 같이 설정하면 된다. 

​                  - File > Project Structure > Module' 로 가서 해당 디렉토리 선택 후 Mark as에서 'Tests'를 선택하고 적용한다. 

​                  - Test Source Folders로 지정이 되면 아래와 같이 초록색 폴더로 바뀐다.

 		  

​			3.  TestClass 생성

​				  - MemberVO.class  > 클래스명 위에다가 우클릭 Create Test click!

​                  - Testing Library  : Junit설정 / 아래 Generate test method는 필요한거 선택가능



​			4. :star:**Test code 작성:star:**				                  

​				1) 테스트 클래스는 반드시 public으로 선언해야 한다.

​				2) 클래스명은 관례상 테스트클래명 + Test 끝나는 이름으로 사용 한다.

​				3) @Test 어노테이션을 선언한 메서드는 JUnit이 알아서 실행 할 수 있게 한다.

```java
public class MemberVOTest {

    @Test
    public void getId() {
        final MemberVO memberVO = MemberVO.builder()
                                          .id("mhcha")
                                          .name("민혜")
                                          .build();
        final String id = memberVO.getId();
        assertEquals("mhcha", id);
    }

    @Test
    public void getName(){
        final MemberVO memberVO = MemberVO.builder()
                                          .id("mhcha")
                                          .name("민혜")
                                          .build();
        final String name = memberVO.getName();
        assertEquals("차민혜", name);
    }
}
```

​			더 많은 어노테이션과 테스트는 소스코드 참고   ->  <a href="https://github.com/chaminhye/NaFlex/blob/study/src/test/java/com/example/naflex/jpaTest/vo/MemberVOTest.java">링크</a>



​			5. Test method run

​                - 원하는 메소드 우클릭해서 실행가능 

​                  ex)  getId()  > 우클릭  run getId() > 결과 Tests passed 

​                         getName()  > 우클릭  run getName() > 결과 Tests failed

​                - 전체 메소드 우클릭해서 전체 메소드 실행가능 

​                  ex)  MemberVOTest  > 우클릭  run MemberVOTest > 결과  1 Tests passed  / 1 Tests failed

​                 

​             6. Junit method

​                참고 : http://junit.sourceforge.net/javadoc/org/junit/Assert.html





### 9. 통합테스트 및 단위테스트

:heavy_check_mark: **종류**

| annotation      | 설명                                                    | Bean                | 링크                                                         |
| --------------- | ------------------------------------------------------- | ------------------- | ------------------------------------------------------------ |
| @SpringBootTest | 통합테스트, 전체                                        | Bean 전체           | <a href="https://github.com/chaminhye/NaFlex/tree/study/src/test/java/com/example/naflex/jpaTest/controller/TestJpaRestControllerTest.java">SpringBootTest</a> |
| @WebMvcTest     | 단위테스트, Mvc 테스트                                  | MVC관련 Bean        | <a href="https://github.com/chaminhye/NaFlex/tree/study/src/test/java/com/example/naflex/test/controller/TestRestControllerTest.java">WebMvcTest</a> |
| @DataJpaTest    | 단위테스트, JPA테스트                                   | JPA 관련 Bean       | <a href="https://github.com/chaminhye/NaFlex/tree/study/src/test/java/com/example/naflex/jpaTest/repository/MemberRepositoryTest.java">DataJpaTest</a> |
| @RestClientTest | 단위테스트, Rest API 테스트(request test)               | 일부 Rest 사용 Bean | <a href="https://github.com/chaminhye/NaFlex/tree/study/src/test/java/com/example/naflex/jpaTest/service/MemberServiceTest.java">RestClientTest</a> |
| @JsonTest       | 단위테스트, Json 테스트(serialization, deserialization) | 일부 Bean           |                                                              |

​	📣 **given() , then() 메소드 사용시** 

​         Junit에서 명시적으로 지원해주지 않으므로 <u>수동으로 import 필수</u>(이것때문에,,,,엄청 고생했다😂😂)

```java
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;
```

​        참고로, Mock기반으로 테스트를 진행하므로 실제 환경에서 제대로 돌아가지 않는 경우도 있음!! 

​       

​	:heavy_check_mark: **개념**

​		 	1.  **Mock**📌

​             	 - 실제 객체를 만들기엔 비용과 시간이 많이 들거나 의존성이 길게 걸쳐져 있어 제대로 구현하기 어려울 경우, 가짜 객체를 만들어 사용한다.

​            	    Database를 연동하지 않아도 된다. 

​            	  - WebApplicationContext를 로드하며 내장된 서블릿 컨테이너가 아닌 Mock 서블릿을 제공한다.  

​           	  - 별도로 지정하지 않으면 기본값은 Mock 서블릿을 로드하여 구동하게 된다. 

​        	     - <u>@AutoConfigureMockMvc</u> 어노테이션을 함께 사용하면 별다른 설정 없이 간편하게 MockMvc를 사용한 테스트를 진행할 수 있다. 

​        	     - MockMvc는 브라우저에서 요청과 응답을 의미하는 객체로서 Controller 테스테 사용을 용이하게 해주는 라이브러리이다.

​              

​              2. **@MockBean**📌

​                  - Mock 객체를 빈으로써 등록할 수 있다.

​                      

​              3. **MocMvc📌**

​                  -  perform : 요청을 전송하는 역할. 

​                                       결과로 ResultActions 객체를 받고, ResultActions 객체는 리턴값으로 검증하고 확인할수 있는 andExpect() 메소드 제공

​                  -  get("/mock/getMember") : HTTP 메소드를 결정할 수 있음(get(), post(), put(), delete()) , 인자로는 url  

​                  - params(info) : key=value의 값을 전달,  단일값인 경우 param() 이용

​                  - andExpect() : 응답을 검증하는 역할

​                                            - status() : 상태코드 ( ex) 메소드 이름 : 상태코드

​                                                                                                  isOk() : 200  

​                                                                                      isNotFound() : 404       )

​                                             - view : 리턴하는 view 이름 검증

​                                             - redirect() : 리다이렉트 응답을 검증

​                                             - model() : 컨츠롤러에서 지정한 모델들의 정보 검증

​                                             - content() : 응답에 대한 정보 검증 

​                  - andDo(print()) : 요청/응답 전체메시지 확인



### 10. YAML 이용하기(properties vs yaml)	

 ​ :heavy_check_mark: **개념**

​       문법은 상대적으로 이해하기 쉽고, 가독성이 좋도록 디자인되었으며, 고급 컴퓨터 언어에 적합하다.  

​       또한 들여쓰기 및 XML의 특수기호를 사용하기 때문에, XML과 거의 비슷함.



 :heavy_check_mark: properties vs yaml

​       내가 생각하기에 아래의 두가지가 큰 차이점이라고 생각하여 간단하게 정리했다.       

|              | properties                                                   | yaml                                                         |
| ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| profile 설정 | 각 profile 별 파일 필요<br />application-local.properties / application-dec.properties / application-prod.properties | application.yaml 파일 하나로 구분가능                        |
|              | @PropertySource로 프로퍼티값 이용가능                        | @PropertySource로 프로퍼티값 이용불가                        |
| 링크         | <a href="https://github.com/chaminhye/NaFlex/blob/study/src/main/resources/application.properties">properties</a> | <a href="https://github.com/chaminhye/NaFlex/blob/study/src/main/resources/application.yml">yaml</a> |



### 11. error page 설정

:heavy_check_mark:  properties (or yaml) 설정

```properties
server.error.include-exception : 오류 응답에 exception의 내용을 포함할지 여부 (TRUE, FALSE)
server.error.include-stacktrace : 오류 응답에 stacktrace 내용을 포함할지 여부 (ALWAYS, NEVER, ON_TRACE_PARAM)
server.error.path : 오류 응답을 처리할 핸들러(ErrorController)의 path
server.error.whitelabel.enabled : 브라우저 요청에 대해 서버 오류시 기본으로 노출할 페이지를 사용할지 여부 (TRUE, FALSE)
```

```yaml
server:
  error:
    include-exception: true # 오류 응답에 exception의 내용을 포함할지 여부
    include-stacktrace: always # 오류 응답에 stacktrace 내용을 포함할 지 여부
```



