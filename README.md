# mysite (내 블로그 만들기)

## jsp/servlet 부터 spring boot 까지, 똑같지만 각각 다른버젼의 mysite만들어보기

***
## mysite02
### jsp/servlet, jquery를 사용

## mysite03 - .XML
- spring을 사용하여 mysite를 제작하되 XML파일로 spring 설정함.
- spring-servlet.xml = viewResolver, MessageSource 등 설정을 Bean객체로 구성
- applicationContext.xml =DB에 관련된 설정들을 Bean객체로 등록

## mysite04 - @Annotation
- spring을 사용하여 mysite를 제작하되 Annotation을 사용하여 조금더 자동화된 spring 설정을 구성함.
- MVC설정, MessageSource 설정, 보안설정 그리고 DB설정까지 각각의 클래스를 만들고 조금더 쉽게 설정함.
- 하지만 Front Controller인 DisptcherServlet 설정이나 공통의 애러페이지 설정, default페이지 등은 아직 web.xml으로 설정함


## mysite05 - except .XML
- spring을 사용하여 mysite를 제작하되 오직 @Annotation 기반으로 구성을 한 프로젝트.
- WebApplicationInitlalizer를 통해 위 web.xml에서 설정해 주었던 설정을  MysiteWebApplicationInitlalizer 클래스에 설정

## mysite06 - SpringBoot
- spring Boot를 
