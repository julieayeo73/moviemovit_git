package kr.co.moviemovit.book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookCont {
  
  @Autowired  // ���� ���踦 �ڵ����� ���� �� ��ü �ڵ� ����
  BookDAO dao;

  public BookCont() {
    System.out.println("---------- BookCont() ��ü ����");
  }//default constructor
  
  
/*********************************** ���� ��� *********************************************/
  // ���Ȯ�� http://localhost:9090/moviemovit/book/list.do
  @RequestMapping(value="/book/list.do")
  public ModelAndView list(BookDTO dto) {
      ModelAndView mav = new ModelAndView();
      ArrayList<BookDTO> list = dao.list();
      mav.setViewName("book/bookList");
      mav.addObject("list", list);
      return mav; 
  }//list() end
  
  
/************************************ ��� *************************************************/
  // ���� ��¥�� 2018-08-31 ������ String���� ��ȯ�ϴ� �Լ�
  /*private String today() {
    Calendar cal = new GregorianCalendar(Locale.KOREA);
    int y = cal.get(Calendar.YEAR);
    int m = cal.get(Calendar.MONTH)+1;
    int d = cal.get(Calendar.DATE);
    
    String startDate = "" + y;
    if(m<10) {
      startDate += "-0" + m;
    } else {
      startDate += "-" + m;
    }//if end
    if(d<10) {
      startDate += "-0" + d;
    } else {
      startDate += "-" + d;
    }//if end
    
    return startDate;
  }//today() end
  *//*
  // ����+1�� ���� ��¥�� 2019-08-31 ������ String���� ��ȯ�ϴ� �Լ�
  private String afterYear() {
    Calendar cal = new GregorianCalendar(Locale.KOREA);
    int y = cal.get(Calendar.YEAR);
    int m = cal.get(Calendar.MONTH)+1;
    int d = cal.get(Calendar.DATE);
    
    String expDate   = "" + (y+1);
    if(m<10) {
      expDate   += "-0" + m;
    } else {
      expDate   += "-" + m;
    }//if end
    if(d<10) {
      expDate   += "-0" + d;
    } else {
      expDate   += "-" + d;
    }//if end
    
    return expDate;
  }//afterYear() end
  *//*
  // ���� ����ϴ� ��
  @RequestMapping(value="/coupon/create.do", method=RequestMethod.GET)  // link Ŭ���ؼ� �Ѿ���°� �� GET ���
  public ModelAndView createForm(HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    
    // startDate �⺻�� ���ó�¥�� ����, expDate �⺻�� ���ó�¥ + 1���ķ� ����
    String startDate = today();
    String expDate   = afterYear();
    
    // ������ �̵� �� �� �ø���
    mav.addObject("startDate", startDate);
    mav.addObject("expDate", expDate);
    mav.setViewName("coupon/couponForm");
    return mav;  // /coupon/couponForm.jsp
  }//createForm() end
  *//*
  // ���� ��� DB INSERT
  @RequestMapping(value="/coupon/create.do", method=RequestMethod.POST)
  public ModelAndView createProc(CouponDTO dto) {
    ModelAndView mav = new ModelAndView();
    
    // �Էµ� �����Ͱ� ������ ���� ��¥, ���ó�¥+1���ķ� ����
    if(dto.getStartDate().equals("")) {
      dto.setStartDate(today());
    }
    if(dto.getExpDate().equals("")) {
      dto.setExpDate(today());
    }
    
    int count = dao.create(dto);
    mav.addObject("count", count);
    
    String msg = "";
    if(count == 0) {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('������Ͽ� �����Ͽ����ϴ�.');";
      msg += "  history.go(-1);";
      msg += "</script>";
      msg += "</html></body>";
      mav.addObject("msg", msg);
      mav.setViewName("msgView");
    }else {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('������ ��ϵǾ����ϴ�.');";
      msg += "  window.location='./list.do';";
      msg += "</script>";
      msg += "</html></body>";
      mav.addObject("msg", msg);
      mav.setViewName("msgView");
    }//if end
    return mav;
  }//createProc() end
  *//*
  // ���� ��� �Ǵ� ������ �ʿ��� ȸ�� ���̵� ��� ��������
  @RequestMapping(value="/coupon/uidSearch.do", method=RequestMethod.GET)
  public ModelAndView uidlist() {
    ModelAndView mav = new ModelAndView();
    ArrayList<String> list = dao.uidlist();
    
    // ������ �̵� �� �� �ø���
    mav.addObject("list", list);
    mav.setViewName("coupon/uidSearch");  // .do ��ɾ�� �̵��� "redirect:" ���
    return mav;
  }//uidSearch() end
  *//*
  // ���� ��� �Ǵ� ������ �ʿ��� ȸ�� ���̵� �˻��ϱ� (AJAX �����)
  @RequestMapping(value="/coupon/uidresult.do")
  public ModelAndView uidresult(HttpServletRequest req, HttpServletResponse resp) {
    // uid ����� resultList�� ����
    ArrayList<String> resultList = new ArrayList<String>();
    try {
      // ������ϴ� �������� �����ڵ���� UTF-8 �� ����
      req.setCharacterEncoding("UTF-8");
      
      // �˻��� ��������
      String searchfield = req.getParameter("searchfield").trim();
      //System.out.println("CouponCont : searchfield : " + searchfield);
      
      // DB�� ��������
      if(searchfield.length() <= 0) { // �˻�� ������ ��ü uid ���
        resultList = dao.uidlist();
        //System.out.println("CouponCont : resultList.get(0) : " + resultList.get(0));
      } else if(searchfield.length()>0) { // �˻�� ������ �˻��� uid ���
        resultList = dao.uidsearch(searchfield);
        //System.out.println("CouponCont : resultList.get(0) : " + resultList.get(0));
      }//if end
    }catch (Exception e) {
      System.out.println("����:" + e);
    }//try end
    //System.out.println("CouponCont : resultList.toString() : "+resultList.toString());
    
    // ������ �̵� �� �� �ø���
    ModelAndView mav = new ModelAndView();
    mav.addObject("resultList", resultList);
    mav.setViewName("coupon/uidSearchTable");  // .do ��ɾ�� �̵��� "redirect:" ���
    return mav;
  }//uidresult() end
*/
  
/************************************ ���� *************************************************/
  // ���� �����ϴ� ��
  /*@RequestMapping(value="/coupon/update.do", method=RequestMethod.GET)  // link Ŭ���ؼ� �Ѿ���°� �� GET ���
  public ModelAndView updateForm(int cCode) {
    ModelAndView mav = new ModelAndView();
    CouponDTO dto = dao.read(cCode);
    mav.addObject("dto", dto);
    mav.setViewName("coupon/couponUpdate");
    return mav;  // /coupon/couponUpdate.jsp
  }//updateForm() end
  *//*
  // ���� ���� DB UPDATE
  @RequestMapping(value="/coupon/update.do", method=RequestMethod.POST)
  public ModelAndView updateProc(CouponDTO dto) {
    ModelAndView mav = new ModelAndView();
    System.out.println("dto.getStartDate() : "+dto.getStartDate());
    
    // �Էµ� �����Ͱ� ������ ���� ��¥, ���ó�¥+1���ķ� ����
    if(dto.getStartDate().equals("")) {
      dto.setStartDate(today());
    }
    if(dto.getExpDate().equals("")) {
      dto.setExpDate(today());
    }
    
    int count = dao.update(dto);
    mav.addObject("count", count);
    
    String msg = "";
    if(count == 0) {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('���������� �����Ͽ����ϴ�.');";
      msg += "  history.go(-1);";
      msg += "</script>";
      msg += "</html></body>";
      mav.addObject("msg", msg);
      mav.setViewName("msgView");
    }else {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('���������� �Ϸ�Ǿ����ϴ�.');";
      msg += "  window.location='./list.do';";
      msg += "</script>";
      msg += "</html></body>";
      mav.addObject("msg", msg);
      mav.setViewName("msgView");
    }//if end
    return mav;
  }//updateProc() end
  */
  
/************************************ ���� *************************************************/
  // ���� ���� Ȯ��â ��
  /*@RequestMapping(value="/coupon/delete.do", method=RequestMethod.GET)  // link Ŭ���ؼ� �Ѿ���°� �� GET ���
  public ModelAndView deleteForm(int cCode) {
    ModelAndView mav = new ModelAndView();
    String msg = "";
    msg += "<!DOCTYPE html>";
    msg += "<html><body>";
    msg += "<script>";
    msg += "  var bResult = confirm('���� �����Ͻðڽ��ϱ�? ������ ������ �������� �ʽ��ϴ�.');";
    msg += "  if (bResult == true) {";  // ���� ���� ���
    msg += "    window.location='./deleteProc.do?cCode="+cCode+"';";
    msg += "  } else {";  // �ƴϿ��� ���� ���
    msg += "    history.go(-1);";
    msg += "  }";
    msg += "</script>";
    msg += "</html></body>";
    mav.addObject("msg", msg);
    mav.setViewName("msgView");
    return mav;
  }//updateForm() end
  *//*
  // ���� ���� DB DELETE
  @RequestMapping(value="/coupon/deleteProc.do", method=RequestMethod.GET)
  public ModelAndView deleteProc(int cCode) {
    ModelAndView mav = new ModelAndView();
    int count = dao.delete(cCode);
    mav.addObject("count", count);
    
    String msg = "";
    if(count == 0) {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('���������� �����Ͽ����ϴ�.');";
      msg += "  history.go(-1);";
      msg += "</script>";
      msg += "</html></body>";
    }else {
      msg += "<!DOCTYPE html>";
      msg += "<html><body>";
      msg += "<script>";
      msg += "  alert('���������� �Ϸ�Ǿ����ϴ�.');";
      msg += "  window.location='./list.do';";
      msg += "</script>";
      msg += "</html></body>";
    }//if end
    mav.addObject("msg", msg);
    mav.setViewName("msgView");
    return mav;
  }//deleteProc() end
  */
}//class end
