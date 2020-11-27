package polymorphism3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// 다형성 = 상속 + Overriding + 형변환
public class TVUser {
	public static void main(String[] args) {
		//BeanFactory container = new BeanFactory();
		
		//1. 스프링 IoC 컨테이너를 생성(구동)한다. (서블릿은 톰캣이 생성해주지만 스프링은 직접 구동할 수 있다.)
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");//xml파일 생성(로딩)
		//GenericXmlApplicationContext와 같은데 ClassPathXmlApplicationContext는 절대, 상대경로 둘다 사용 가능
		
		//2. 스프림 IoC 컨테이너로부터 테스트할 객체를 검색(Lookup) 한다.
		//getBean 안에 id를 변경하지 않기 위해서 bean에 ID 지정
		TV tv = (TV) container.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		//3. 스프링  IoC 컨테이너를 종료한다.	
		container.close();
	}
}
