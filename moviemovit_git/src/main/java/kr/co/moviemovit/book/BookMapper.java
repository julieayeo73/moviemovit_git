package kr.co.moviemovit.book;

import java.util.ArrayList;

public interface BookMapper {
  /*
      ���ۿ� ��ϵǾ� �ִ� id�� �������̽��� �޼ҵ忡
      �ٽ� �ѹ� ��������ν� MyBatis3�� ȣ���ϱ� ���ϵ��� ������.
   */
  
  // .xml ���� ������ <insert id="create"> Ȱ��. id�� �޼ҵ������ �;���. body �ۼ� ���� ����.
  public ArrayList<BookDTO> list();
  //public int create(CouponDTO dto);
  //public ArrayList<String> uidlist();
  //public ArrayList<String> uidsearch(String uid);
  //public CouponDTO read(int cCode);
  //public int update(CouponDTO dto);
  //public int delete(int cCode);
  
  
}//interface end
