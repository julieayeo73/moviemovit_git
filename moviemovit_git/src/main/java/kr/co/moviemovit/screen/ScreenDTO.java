package kr.co.moviemovit.screen;

public class ScreenDTO {
	private int sCode;			//���ڵ�
	private String roomCode;	//�󿵰�
	private String sdate;		//�󿵳�¥	10��
	private String stime;		//�󿵽��۽ð� 4��, 24�ð���
	private int mCode;			//��ȭ�ڵ�
	
	public ScreenDTO() {}//�⺻������

	public int getsCode() {
		return sCode;
	}

	public void setsCode(int sCode) {
		this.sCode = sCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public int getmCode() {
		return mCode;
	}

	public void setmCode(int mCode) {
		this.mCode = mCode;
	}
	
}//class end
