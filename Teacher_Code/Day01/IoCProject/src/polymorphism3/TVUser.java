package polymorphism3;

import org.springframework.context.support.GenericXmlApplicationContext;

// ������ = ��� + Overriding + ����ȯ
public class TVUser {
	public static void main(String[] args) {
		// 1. ������ IoC �����̳ʸ� ����(����)�Ѵ�.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. ������ IoC �����̳ʷκ��� �׽�Ʈ�� ��ü�� �˻�(Lookup)�Ѵ�.
		TV tv = (TV) container.getBean("tv");
		tv.powerOn();
		tv.volumeDown();
		tv.volumeUp();		
		tv.powerOff();
		
		// 3. ������ IoC �����̳ʸ� �����Ѵ�.
		container.close();
	}
}
