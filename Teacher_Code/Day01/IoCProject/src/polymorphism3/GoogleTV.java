package polymorphism3;

public class GoogleTV implements TV {
	private int price;

	public GoogleTV() {
		System.out.println("===> GoogleTV ����");
	}
	public void ��������ʱ�ȭ() {
		System.out.println("---> ��������ʱ�ȭ() ȣ��");
		this.price = 1500000;
	}
	public void �ڿ�����() {
		System.out.println("---> �ڿ�����() ȣ��");
		this.price = 0;
	}
	public void powerOn() {
		System.out.println("GoogleTV---���� �Ҵ�." + price);
	}
	public void powerOff() {
		System.out.println("GoogleTV---���� ����.");
	}
	public void volumeUp() {
		System.out.println("GoogleTV---�Ҹ� �ø���.");
	}
	public void volumeDown() {
		System.out.println("GoogleTV---�Ҹ� ������.");
	}
}
