package polymorphism3;

public class SamsungTV implements TV {
	private Speaker spearker;
	private int price;
	//null로 기본값으로 설정한다.
	
	public SamsungTV() {
		System.out.println("===> SamsungTV(1) 생성");
	}
	
/*	public SamsungTV(Speaker spearker) {
		System.out.println("===> SamsungTV(2) 생성");
		this.spearker = spearker;
	}
	
	public SamsungTV(Speaker spearker, int price) {
		System.out.println("===> SamsungTV(3) 생성");
		this.spearker = spearker;
		this.price = price;
	}*/

	public void setSpearker(Speaker spearker) {
		System.out.println(" ---> setSpeaker() 호출");
		this.spearker = spearker;
	}

	public void setPrice(int price) {
		System.out.println(" ---> setPrice() 호출");
		this.price = price;
	}
	
	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다." + price);
	}
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}
	public void volumeUp() {
		spearker.volumeUp();
	}
	public void volumeDown() {
		spearker.volumeDown();
	}
}
