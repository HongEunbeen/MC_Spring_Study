# Spring  Framework - 1

Spring Framework를 공부 하면서 작성하는 문서입니다..!! 
- - - -

## 1. 프레임워크(Framework) 개요
### 1) 프레임 워크(Framework) 개요
1. 프레임 워크
 - 뼈대 혹은 골조로서 애플리케이션을 개발할 떄 아키텍처에 해당하는 골격 코드를 제공
 - 반복적인 구조나 기능들을 모듈화 시켜서 재상용할 수 있도록 지원
 - 비즈니스 로직만 개발하면 되기 때문에 쉽고 빠르게 원하는 시스템 구축 가능
	 - 이 부분은 운영적인 부분에서도 이점
2. 프레임워크의 구조
- 프레임워크 코어(Cold Spot)
	- 아키텍처에 해당하는 클래스 -> 프레임워크가 제공하는 JAR(Java Archive) 파일
	- 확장포인트(Hook Point) -> 추상클래스나 인터페이스 형태
- 확장 모듈(Hot Spot)
	- 확장 포인트를 확장하여 애플리케이션의 비즈니스 로직 추가
- 메타데이터
	- 콜드스팟(아키텍처) + 핫스팟(비즈니스 로직) -> 유기적으로 결합하는 메타데이터
	- XML 설정파일로 작성,  Java 소스에 Annotation 통해 제공
3. 프레임워크의 장점 
애플리케이션에 대한 분석, 설계, 구현에 이르는 거의 모든 부분 재사용 가능
- 빠른 구현 시간
- 관리의 용의성 증가
- 개발자들의 역량 획일화
- 검증된 아키택처의 재 사용과 일관성 유지
4. 자바 기반의 프레임워크
- 오픈소스(Open Source) -> 변경의 자유로움(유료버전 되어도 그 전 버전 이용해 개발)
- 대표적인 오픈소스 프레임워크
Spring 기능단위로 모듈와 되어 있음(모듈 한개 == .jar)
	- Presentation(UI)
		- Structs -> Apache 개발  MVC 프레임워크[독립된 프레임워크]
		- Spring(MVC) ->  Spring 프레임워크 구성 모듈
	- Business
		- Spring(IoC/AOP) -> 비즈니스 컴포넌트 개발
			-> Ioc  모듈 이용해 비즈니스 컴포넌트 개발
			-> AOP 모듈 이용해 비즈니스 컴포넌트의 공통 기능 개발
	- Persistence(DB)
		- Hibernate(or JPA) -> ORM 프레임워크로 SQL 프레임워크에서 제너레이션(SQL 까지도 제공(접근성이 낮고 SQL 보이지 않는다))
		- iBATIS(or MyBatis) -> SQL Mapper 프레임워크 )개발자가 XML 파일에 작성한 SQL 명령어를 자바의 객체와 매핑)
		
### 2)스프링 프레임워크
1. 스프링 프레임워크의 구조
- 기존 : Java 대부분 EJB 개발 -> 스팩복잡, 시간 필요, 고가의 장비 필요
- 현재 : Spring 프레임워크 -> POJO 사용 및 EJB에서만 가능했던 많은 일 가능 지원
POJO란?(Plain Old Java Object)
```
아무런 규칙이 적용되지 않는 클래스(리턴, 매개변수 등 자유롭게 설정 가능)
장점 : 문법적 제약이 없어 유연하고 배우기 쉽다(접근성 높음) 또한 메모리 사용이 많지 않다.(not pojo인 클래스는 모든 규칙을 엄격하게 지켜야하기 때문에 필요없는 객체가 많이 생성된다.)
 ... not pojo는 Servlet, jsp, EJB 등등,,,
```
2. 스프링 프레임워크의 특징
- 경량 
	- 여러개의 모듈로 구성되어 메모리 사용량 낮음
		- 스프링이 관리하는 객체가 POJO이기 때문(불 필요한 객체 생성 x)
	- 제어역행(IoC)
		- 애플리케이션의 느슨한 결합 지원(결합도 낮다)
		- 제어역행 주체 : Container(Spring)
		- 제어의 대상 : POJO 형태의 자바 객체
	- 관점지향(AOP)
		- 비즈니스 로직과 공통 기능의 코드 분리함(응집도 높다)
		- ex)  예외처리, 로그, 트랜젝션 -> 모듈화 + 선언적(XML)
			- 기존의 객체지향의 한계 벗어남
	- 컨테이너(Container)
		- 애플리케이션 운용에 사용되는 객체의 생명주기 + 의존성 관리
		- 이건 Servlet이나 EJB와 개념은 같지만 Spring 에서의 객체는 POJO이다.
