package polymorphism3;

public class SamsungTV implements TV {
	private Speaker spearker;
	private int price;
	//null�� �⺻������ �����Ѵ�.
	
	public SamsungTV() {
		System.out.println("===> SamsungTV(1) ����");
	}
	
/*	public SamsungTV(Speaker spearker) {
		System.out.println("===> SamsungTV(2) ����");
		this.spearker = spearker;
	}
	
	public SamsungTV(Speaker spearker, int price) {
		System.out.println("===> SamsungTV(3) ����");
		this.spearker = spearker;
		this.price = price;
	}*/

	public void setSpearker(Speaker spearker) {
		System.out.println(" ---> setSpeaker() ȣ��");
		this.spearker = spearker;
	}

	public void setPrice(int price) {
		System.out.println(" ---> setPrice() ȣ��");
		this.price = price;
	}
	
	public void powerOn() {
		System.out.println("SamsungTV---���� �Ҵ�." + price);
	}
	public void powerOff() {
		System.out.println("SamsungTV---���� ����.");
	}
	public void volumeUp() {
		spearker.volumeUp();
	}
	public void volumeDown() {
		spearker.volumeDown();
	}
}
