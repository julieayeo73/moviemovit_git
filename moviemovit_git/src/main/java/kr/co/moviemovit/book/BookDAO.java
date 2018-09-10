package kr.co.moviemovit.book;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
  private String bCode;  // �����ڵ�(PK)
  private String wdate;  // ���ų�¥
  private String uid;    // ���� ���̵� <- userTable
  private int sCode;     // ���ڵ� <- screenTable
  private int cCode;     // ������뿩�� <- couponTable
  private String seat;   // �����¼���ġ(�ִ�5��)
  private String auditType;  // ���������� (adu you sen kid -> ���� û�ҳ� ��ο�� ����)
*/
@Component
public class BookDAO {
  
  //@Autowired
  //DBOpen dbopen;
  
  //root-context.xml�� <bean id=sqlSession> ���� ���踦 �ڵ����� ���� �� ��ü �ڵ� ����
  @Autowired
  SqlSession sqlSession;
  
  public BookDAO() {
    System.out.println("---------- BookDAO() ��ü ����");
  }//default constructor
  
  
/*********************************** ���� ��� *********************************************/
  // ���� ��� select
  public ArrayList<BookDTO> list() {
    BookMapper mapper = sqlSession.getMapper(BookMapper.class);
    ArrayList<BookDTO> list = mapper.list();
    return list;
  }//list() end
  

/************************************ ��� *************************************************/
  // ���� ��� insert
  /*public int create(CouponDTO dto) {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    int count = mapper.create(dto);
    return count;
  }//create() end
  
  // ���� ���&������ �ʿ��� ȸ�� ���̵� ��� �������� select
  public ArrayList<String> uidlist() {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    ArrayList<String> list = mapper.uidlist();
    return list;
  }//uidlist() end
  
  // ���� ���&������ �˻���� ��ġ�ϴ� ȸ�� ���̵� ��� �������� select where
  public ArrayList<String> uidsearch(String uid) {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    ArrayList<String> list = mapper.uidsearch(uid);
    return list;
  }//uidsearch() end
  */
  
/************************************ ���� *************************************************/
  // ���� �Ѱ� �󼼵����� �������� select
  /*public CouponDTO read(int cCode) {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    CouponDTO dto = mapper.read(cCode);
    return dto;
  }//read() end
  
  // ���� ���� update
  public int update(CouponDTO dto) {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    int count = mapper.create(dto);
    return count;
  }//update() end
  */
  
/************************************ ���� *************************************************/
  // ���� ���� delete
  /*public int delete(int cCode) {
    CouponMapper mapper = sqlSession.getMapper(CouponMapper.class);
    int count = mapper.delete(cCode);
    return count;
  }//delete() end
  */
}//class end
