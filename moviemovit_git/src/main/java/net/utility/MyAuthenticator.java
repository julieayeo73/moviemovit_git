package net.utility;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
  //����ϰ��� �ϴ� ���� �������� �������� ������ ��� ���� Ŭ���� ����
  private PasswordAuthentication pa;

  public MyAuthenticator() { //������ �Լ�
    pa = new PasswordAuthentication("soldesk@pretyimo.cafe24.com", "soldesk6901");
  }

  @Override
  protected PasswordAuthentication getPasswordAuthentication() {
    return pa;
  }

}//class end