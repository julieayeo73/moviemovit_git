package kr.co.moviemovit.coupon;

public class CouponDTO {
  private int cCode;    // �����ڵ�
  private String uid;   // ���� ���̵�
  private String startDate;   // �߱���
  private String expDate;     // ������
  
  public CouponDTO() {
    System.out.println("---------- CouponDTO() ��ü ����");
  }//default constructor

  public int getcCode() {
    return cCode;
  }

  public void setcCode(int cCode) {
    this.cCode = cCode;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getExpDate() {
    return expDate;
  }

  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }
  
}//class end
