package polymorphism3;

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("===> SonySpeaker ����");
	}
	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker---�Ҹ� �ø���.");
	}
	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker---�Ҹ� ������.");
	}
}
