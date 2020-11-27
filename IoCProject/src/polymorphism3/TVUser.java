package polymorphism3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// ������ = ��� + Overriding + ����ȯ
public class TVUser {
	public static void main(String[] args) {
		//BeanFactory container = new BeanFactory();
		
		//1. ������ IoC �����̳ʸ� ����(����)�Ѵ�. (������ ��Ĺ�� ������������ �������� ���� ������ �� �ִ�.)
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("applicationContext.xml");//xml���� ����(�ε�)
		//GenericXmlApplicationContext�� ������ ClassPathXmlApplicationContext�� ����, ����� �Ѵ� ��� ����
		
		//2. ������ IoC �����̳ʷκ��� �׽�Ʈ�� ��ü�� �˻�(Lookup) �Ѵ�.
		//getBean �ȿ� id�� �������� �ʱ� ���ؼ� bean�� ID ����
		TV tv = (TV) container.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		//3. ������  IoC �����̳ʸ� �����Ѵ�.	
		container.close();
	}
}
