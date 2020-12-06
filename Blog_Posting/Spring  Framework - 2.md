
\
#  Spring  Framework - 2
Spring Framework를 공부 하면서 작성하는 문서입니다..!!

 
---
# Spring 컨테이너
### IoC(Inversion of Control) 개념
1. IoC 개념
- `가장 중요한 개념으로 컨테이너의 개념은 서블릿이나 EJB 같은 자바 기술에서 이미 사용됨`
- 객체들을 관리 할 목적으로 사용(객체 생성 및 운용, 객체간 의존성)
	- 객체 생성과 객체간 의존성 코드 사라짐 -> **낮은 결합도**의 컴포넌트 구현 가능
- **Servlet** Container
	1.  WEB.INF/web.xml 파일을 로딩하여 서블릿 컨테이너가 생성됨
	2. 클라이언트(브라우저)로부터 *.do 요청 전달
	3.  web.xml 파일에 등록된 클래스 찾아 객체 생성 + doGet() 메소드 호출
	4. doGet() 메소드 실행결과 반환
	- **객체를 생성하고 객체들 사이의 읜존관계 처리 책임은 개발자에게 존재**
		- 어떤 객체 생성할지 판단 + 의존관계 -> 소스코드 이용해 표현
		- **제어의 역행을 이용하면 이런 작업을 개발자 -> 컨테이너 처리**
- Spring Container = IoC Container 기법 사용!!
2. 결합도(Coupling)와 유지보수성
- 결합도란 ?
	- 소프트웨어 코드의 한 요소가 다른 것과 얼마나 강력하게 연결되어 있는지를 나타냄
	- 소프트웨어의 기본적 규칙
		- 결합도 낮음 + 응집도 높음  -> 독립가능한 Component
		- 결합도 	: 소프트웨어 코드의 한 요소가 다른것과 강력하게 연결되어 있는지
		- 응집도 	: 메소드 이름에 걸맞는 기능만 작성되었는지,  클래스에 필요한 메소드만 작성되었는지



	1. 결합도(Coupling)가 높은 프로그램
	- Coffee Class와 Green Tea Class가 변경될 가능성있는 객체라면?
		``` java
		public class Coffee {
			public  void drink(){
				System.out.println("coffee drink!!");
			}
		}	

		public class GreenTea{
			public  void drinking(){
				System.out.println("greenTea drinking!!");
			}
		}

		public class User{
			public static void main(String[] args){
				Coffee cfe = new Coffee();
				cfe.drink();

				GreenTea gt = new GreenTea();
				gt.drink(); //error 메소드 이름이 맞지 않기 때문
				//이렇게 객체를 변경할 떄마다 객체 안 메소드 이름을 알아야지 수정 가능
			}
		}
		```
	- Coffee와 GreenTea가 **같은 메소드를 가지게끔 강제할 어떤 수단도 없음**
- 결합도 떨어트리는 방법
	1. 다형성(Polymorphism)
	- 상속, 메소드 재정의(Overriding), 형변환 필요
	- 자바 같은 객체지향 언어는 이를 문법 지원( 다형성 == 객체지향 기본적 개념)
		```java
		public interface Drink{
			public void drinkAnyThings();
		}		

		public class Coffee implements Drink{
			public  void drinkAnyThings(){
				System.out.println("coffee drink!!");
			}
		}	

		public class GreenTea implements Drink{
			public  void drinkAnyThings(){
				System.out.println("greenTea drinking!!");
			}
		}

		public class User{
			public static void main(String[] args){
				Coffee cfe = new Coffee();
				cfe.drinkAnyThings();

				GreenTea gt = new GreenTea();
				gt.drinkAnyThings(); //error발생 안하고 잘 동작
			}
		}
		```
- 장점 : 클라이언트 프로그램이 여러개 있더라도 최소한의 수정으로 변경가능
- 단점 : 소스수정의 범위 최소화 가능 but **소스 수정 전혀 안 하기 불가**
2. Factory 패턴 이용
- 다형성의 단점인 소스 수정 전혀 안 하기 불가를 해결위해서 디자인 패턴 적용
- Factory 패턴 이용시 클라이언트에서 사용할 객체 생성을 캡슐화 해서 느슨한 결합상태로 만듬
	- BeanFactory
		```java
		public class BeanFactory{
			public Object getBean(String beanName){
				if(beanName.equals("coffee"){
					return new Coffee();		
				}else if(beanName.equals("greentea")){
					return new GreanTea();
				}
				return null;
			}
		}	
		public class User{
			public static void main(String[] args){
				BeanFactory factory = new BeanFactory();
				Drink drink = (Drink) factory.getBean("coffee");
				//interface Class로 받아온다.
				//coffee 부분만 변경해서 사용하면 된다.
				drink.drinkAnyThings();
			}
		}

		```
	- 직접 객체 생성 코드를 작성하지 않기 때문에 생성결합 문제 해결 가능 
	3. Spring IoC 컨테이너
 	- 컨테이너는 각 컨테이너가 관리할 객체들을 위한 별도의 XML 설정파일 존재
 	- POJO 객체들을 관리하기 위한 별도의 xml 파일 필
	 	- Servlet Container : web.xml
	 	- Spring Container : resources/applicationContext.xml
		```xml
		<?xml version="1.0" encoding="UTF-8"?>

		<!--beans : Root elemtent-->
		<beans xmlns="http://www.springframework.org/schema/beans"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:p="http://www.springframework.org/schema/p"
				xmlns:context="http://www.springframework.org/schema/context"
			xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
				http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd"> <!--스키마 문서-->

			<bean id="coffee" class="com.eunoia.Coffee"/>

				```
	- `<beans>`: 네임스페이스(xmlns) 와 스키마 문서(schemaLocation)의 위치 선언
		- 네임스페이스를 통해 스프링 XML 설정파일에서 사용할 엘리먼트와 속성 제한
		- 새로운 네임스페이스를 등록한다는 것은 전혀 다른 종류의 엘리먼트와 속성 사용 -> 스프링 컨테이너에게 전혀 다른 종류의 기능 처리 지시 의미
		- `<beans>` 속성 (4개의 태그만 사용 가능)
			- `<description>`
			- ` <import>`
			- `<alias>`
			- `<bean>`
		``` java
		public class User{
			public static void main(String[] args){

				//1. Spring 컨테이너 구동(xml 파일 참조해서)
				GenericXmlApplicationContext factory =
		 			new GenericXmlApplicationContext("applicationContext.xml");
				//2, Spring 컨테이너로부터 필용한 객체 요청(LookUp) 함
				Drink drink = (Drink) new factory.getBean("coffee");
				drink.drinkAnyThings();
			}
		}	
		```
	- 동작 과정
		1. 클라이언트가 스프링 설정 파일(XML) 로딩해 컨테이너 구동  
		2. 컨테이너는 스프링 설정 파일에 <bean> 등록 객체 찾아 생성
		3. getBean() 메소드로 ID와 대조해 객체 요청(LookUp) 한다.
		4. Id의 객체 반환한다.
		- 스프림 설정 파일(XML)에서 객체를 변경하여 비즈니스 소스를 수정하지 않아도 된다.(유지보수 업업)
	- Spring 컨테이너의 종류
		- 가장 많이 사용하는 두 개의 클래스(ApplicationContext 인터페이스 구현)
		- GenericXmlApplicationContext 
			-  클라이언트가 직정 객체 생성 및 구동 컨테이너
		- WmlWebApplicationContext 
			- 웹 애플리케이션 개발에 사용, 직접 생성하여 사용하지 않는 컨테이너
- 정리 
	- 순제어 : 비즈니스 로직에서 VO를 new 사용 -> 유지보수 어렵
	- 역제어 : 비즈니스 로직에 new 존재 x  -> xml 참조해서 Container가 생성 후 반환
### Spring 설정 파일(XML)
1. 스프링 설정 파일
- 스프링 XML(Bean 저장소) 설정 파일 통해 스프링 컨테이너 동작 제어 가능
	- Bean의 설정 정보 참조해 Bean객체 라이프 사이클 관리
	- 여러가지 컨테이너 서비스 제공
- <bean> 엘리먼트
	- id, class, init-method, destroy-method, lazy-init
	- `<bean id="coffee" class="com.eunoia.Coffee"/>`
		- class : 클래스의 위치 등록(정확한 패키지 경로, 클래스 이름 지정)
		- id : 생성 객체 LookUp 시 사용
			- 지정하지 않으면 **패키지 경로 + 클래스 이름 +#0** 자동 생성
			- unique 하고 자바 식별자 작성 규칙 적용
	- `<bean id="coffee" class="com.eunoia.Coffee" init-method="멤버변수초기화" destroy-method="자원해제" lazy-init="true"/>`
		```java
		public class Coffee implements Drink{
			int cnt;

			public void 멤버변수초기화(){
				cnt = 12;
			}
			public  void drinkAnyThings(){
				System.out.println("coffee drink!!");
			}
		}		

		public class User{
			public static void main(String[] args){
				BeanFactory factory = new BeanFactory();
				Drink drink = (Drink) factory.getBean("coffee");
				drink.drinkAnyThings();

				factory.close();
			}
		}
		```
	- init-method 
		- Container 객체 생성 시 디폴트 생성자 호출(없으면 error) -> 객체에 대한 초기화 작업 처리 별도의 메소드 필요하기 떄문에 init-method에 지정
	- destroy-method
		- 객체가 삭제되기 전  호출 메소드 지정
		- Spring Container는 종료 전 자신이 관리하는 모든 객체 삭제
		- ex) DB 연결 끊기위해 사용
	- lazy-init
		- Boolean (default : fasle)
		- Container 구동시점 아닌 해당 객체가 실제로 사용되는 시점*(getBean메소드 호출)*에 메모리 올라감**(Lazy-Loading)**
		- Container는 XML에 bean 등록되면 실행시 객체를 무조건 메모리에 생성함**(Pre-Loading)**