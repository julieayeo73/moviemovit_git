package kr.co.moviemovit.review;


public class ReviewStar {
  
  //ù��° �迭�� code�� list�� code�� ��ġ�ϸ� �ش� �迭 ��������
  //��ġ���� ������ �������ͼ� �� ���� �迭�� �Ѿ�� 0���� ����
  // reviewstartable
  private int no;
  private String cineCode; 
  private String uid;       
  private int pixel;     
  private int sound;   
  private int clean;    
  private int service;   
  private int seat;     
  private int around;  
  private int snack; 
  private int trans;
  private String s_e;   /*0904 ���� reviewTable�� ��ġ���� �߰�*/
  private String ip;    /*0904 ���� reviewTable�� ��ġ���� �߰�*/
  private int heart;    /*0904 ���� reviewTable�� ��ġ���� �߰�*/
  private String wdate; /*0903 ���� DATETIME -> wdate �ٲ�(��������)*/

  private int startotal;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCineCode() {
    return cineCode;
  }

  public void setCineCode(String cineCode) {
    this.cineCode = cineCode;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public int getPixel() {
    return pixel;
  }

  public void setPixel(int pixel) {
    this.pixel = pixel;
  }

  public int getSound() {
    return sound;
  }

  public void setSound(int sound) {
    this.sound = sound;
  }

  public int getClean() {
    return clean;
  }

  public void setClean(int clean) {
    this.clean = clean;
  }

  public int getService() {
    return service;
  }

  public void setService(int service) {
    this.service = service;
  }

  public int getSeat() {
    return seat;
  }

  public void setSeat(int seat) {
    this.seat = seat;
  }

  public int getAround() {
    return around;
  }

  public void setAround(int around) {
    this.around = around;
  }

  public int getSnack() {
    return snack;
  }

  public void setSnack(int snack) {
    this.snack = snack;
  }

  public int getTrans() {
    return trans;
  }

  public void setTrans(int trans) {
    this.trans = trans;
  }

  public String getS_e() {
    return s_e;
  }

  public void setS_e(String s_e) {
    this.s_e = s_e;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public int getHeart() {
    return heart;
  }

  public void setHeart(int heart) {
    this.heart = heart;
  }

  public String getWdate() {
    return wdate;
  }

  public void setWdate(String wdate) {
    this.wdate = wdate;
  }

  public int getStartotal() {
    return startotal;
  }

  public void setStartotal(int startotal) {
    this.startotal = startotal;
  }
  
  
 
} // class end
