package kr.co.moviemovit.book;

public class BookDTO {
  private String bCode;  // �����ڵ�(PK)
  private String wdate;  // ���ų�¥
  private String uid;    // ���� ���̵� <- userTable
  private int sCode;     // ���ڵ� <- screenTable
  private int cCode;     // ������뿩�� <- couponTable
  private String seat;   // �����¼���ġ(�ִ�5��)
  private String auditType;  // ���������� (adu you sen kid -> ���� û�ҳ� ��ο�� ����)
  
  public BookDTO() {
    System.out.println("---------- BookDTO() ��ü ����");
  }//default constructor

  public String getbCode() {
    return bCode;
  }

  public void setbCode(String bCode) {
    this.bCode = bCode;
  }

  public String getWdate() {
    return wdate;
  }

  public void setWdate(String wdate) {
    this.wdate = wdate;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public int getsCode() {
    return sCode;
  }

  public void setsCode(int sCode) {
    this.sCode = sCode;
  }

  public int getcCode() {
    return cCode;
  }

  public void setcCode(int cCode) {
    this.cCode = cCode;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public String getAuditType() {
    return auditType;
  }

  public void setAuditType(String auditType) {
    this.auditType = auditType;
  }
  
}//class end
