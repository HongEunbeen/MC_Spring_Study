package polymorphism3;

import org.springframework.context.support.GenericXmlApplicationContext;

// 다형성 = 상속 + Overriding + 형변환
public class TVUser {
	public static void main(String[] args) {
		// 1. 스프링 IoC 컨테이너를 생성(구동)한다.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. 스프링 IoC 컨테이너로부터 테스트할 객체를 검색(Lookup)한다.
		TV tv = (TV) container.getBean("tv");
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();		
		tv.powerOff();
		
		// 3. 스프링 IoC 컨테이너를 종료한다.
		container.close();
	}
}
