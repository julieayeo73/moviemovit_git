package kr.co.moviemovit.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.*;

@Controller
public class UserCont {
	@Autowired
	UserDAO dao;
	
	public UserCont() {
		System.out.println("---UserCont() ��ü ����");
	}//�⺻������
	
	//���Ȯ��
	//http://localhost:9090/moviemovit/index.do
	
	//1) ȸ�����
	@RequestMapping(value="/user/agree.do", method = RequestMethod.GET)
	public String agree() {
		return "user/agreement";
	}//agree() end
	
	//2) ȸ������ ��
	@RequestMapping(value="/user/joinform.do", method = RequestMethod.GET)
	public String joinForm() {
		return "user/joinForm";
	}//joinForm() end
	
	//3) ȸ������
	@RequestMapping(value="/user/joininsert.do", method = RequestMethod.POST)
	public ModelAndView joinInsert(UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		String msg = "";
		int result = dao.joinMember(dto);
		
		//ȸ������ ��� â
		if(result == 0) {
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('ȸ�����Կ� �����߽��ϴ�');";
			msg += "  history.go(-1);";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}else {
			mav.addObject("result", result);
			mav.setViewName("redirect:/index.do");
		}//if end
		return mav;
	}//joinInsert() end
	
	//4) ���̵� �ߺ��˻�
	@RequestMapping(value="/user/idcheck.do", method = RequestMethod.POST)
	public void idCheck(UserDTO dto, HttpServletResponse resp) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		String message = "";
		
		//joinForm.jsp���� uid�ѱ�� �޾ƿ�
		if(dto.getUid().length()<5 || dto.getUid().length()>10) {
			message = "<span style='color:red;font-weight:bold'>���Ұ�. 5~10�� �̳��� �Է��ϼ���</span>";
		}else {
			int id_result = dao.idCheck(dto); //���̵� �ߺ�Ȯ��
			if(id_result == 0) {
				message = "<span style='color:blue;font-weight:bold'>��� ������ ���̵� �Դϴ�</span>";
			} else {
				 message = "<span style='color:green;font-weight:bold'>�ߺ� ���̵� �Դϴ�</span>";
			}
		}//if end
		
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(message);
		out.flush();
		out.close();
	}//idCheck() end
	
	//5) �̸��� �ߺ��˻�
	@RequestMapping(value="/user/emailcheck.do", method = RequestMethod.POST)
	public void emailCheck(UserDTO dto, HttpServletResponse resp) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
		String message = "";
		
		if(dto.getEmail().indexOf("@")<0 || dto.getEmail().length()<0) {
			message = "<span style='color:red;font-weight:bold'>�̸����� Ȯ���ϼ���</span>";
		}else {
			int em_result = dao.emailCheck(dto); //�̸��� �ߺ�Ȯ��
			if(em_result == 0) {
				message = "<span style='color:blue;font-weight:bold'>��� ������ �̸��� �Դϴ�</span>";
			} else {
				 message = "<span style='color:green;font-weight:bold'>�ߺ� �̸��� �Դϴ�</span>";
			}
		}//if end
		
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(message);
		out.flush();
		out.close();

	}//emailCheck() end
	
	/***************************************************************************/
	
	//1) �α��� �� 
	@RequestMapping(value="/user/loginform.do", method = RequestMethod.GET)
	public String loginForm() {
		return "user/loginForm";
	}//joinForm() end
	
	//2) �α���
	@RequestMapping(value="/user/loginproc.do", method = RequestMethod.POST)
	public ModelAndView loginProc(UserDTO dto, HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mav = new ModelAndView();
		String msg = "";

		dto = dao.loginProc(dto);  
		
		if(dto==null) {
			//����� Ʋ���� ���
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('���̵�� ��й�ȣ�� �ٽ� Ȯ���ϼ���');";
			msg += "  history.go(-1);";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}else {
			//�α��� ���� �� �α��� �� �� session ������ �ø���
			if(!(dto.getGrade().equals("F"))) {
				HttpSession session = req.getSession();
				session.setAttribute("s_id", dto.getUid());
				session.setAttribute("s_passwd", dto.getUpw());
				//180903 ��� �߰�
				session.setAttribute("s_grade", dto.getGrade());
				
				//��Ű(���̵� ����)
				String c_id = req.getParameter("c_id");
				if(c_id==null) { //üũ���� ���� ���
					c_id="";
				}
				
				Cookie cookie = null;
				if(c_id.equals("SAVE")) {
					cookie = new Cookie("c_id", dto.getUid());
					cookie.setMaxAge(60*60*24*31); //�Ѵ�
				}else {
					cookie = new Cookie("c_id", "");
					cookie.setMaxAge(0);
				}
				resp.addCookie(cookie);
				mav.addObject("dto",dto);
				mav.setViewName("redirect:/index.do");
			} else if(dto.getGrade().equals("F")) {
				//Ż���� ȸ���� �α��� �� ��			
				msg += "<!DOCTYPE html>";
				msg += "<html><body>";
				msg += "<script>";
				msg += "  alert('Ż���� ȸ���� ���̵��Դϴ�. �簡�� �� �α��� �ϼ���');";
				msg += "  history.go(-1);";
				msg += "</script>";
				msg += "</html></body>";
				mav.addObject("msg", msg);
				mav.setViewName("msgView");
			}
		}//if end

		return mav;
	}//loginProc() end
	
	//3) �α׾ƿ�
	@RequestMapping(value="/user/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession();
		session.removeAttribute("s_id");
		session.removeAttribute("s_passwd");		
		
		mav.setViewName("redirect:/index.do");
		return mav;
	}//logout() end
	
	//4) ����������
	@RequestMapping(value="/user/mypage.do", method = RequestMethod.GET)
	public ModelAndView mypage(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = req.getSession();
		
		//�α��εǾ session�� �ö� �� ��������
		String uid = (String)session.getAttribute("s_id");
		String upw = (String)session.getAttribute("s_passwd");
		
		//uid,upw dto�� ���
		UserDTO dto = new UserDTO();
		dto.setUid(uid);
		dto.setUpw(upw);
		
		//DB���� ȸ������ ��������
		dto = dao.getMemberInfo(dto);

		//������ �̵� �� �� �ø���
		mav.addObject("dto", dto);
		mav.setViewName("user/userInfo");
		return mav;
	}//mypage() end
	
	//5) ȸ��Ż�� ��
	@RequestMapping(value="/user/userDelete.do", method = RequestMethod.GET)
	public ModelAndView userDeleteForm(UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		//userCheckForm.jsp���� dto.uid�� ����Ӽ��� ������ؼ�
		mav.addObject("dto", dto);
		mav.setViewName("user/userCheckForm");
		return mav;
	}//userDeleteForm() end
	
	//6) ȸ��Ż�� ó��
	@RequestMapping(value="/user/userDelete.do", method = RequestMethod.POST)
	public ModelAndView userDeleteProc(UserDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String root = Utility.getRoot(); // /moviemovit
		String msg = "";
		//dto�� ���� upw, hidden�� ���� uid �޾ƿ�
		//DB�� ȸ��Ż��(����� F�� ����)
		int check = dao.userDelete(dto);
		
		if(check==1) {
			HttpSession session = req.getSession();
			session.removeAttribute("s_id");
			session.removeAttribute("s_passwd");
			
			//ȸ��Ż�� ���� ��
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('ȸ��Ż�� �����߽��ϴ�');";
			msg += " location.href='" + root + "/index.do'";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}else {
			//ȸ��Ż�� ���� ��
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('��й�ȣ�� Ʋ�Ƚ��ϴ�');";
			msg += "  history.go(-1);";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}
		
		return mav;
	}//userDeleteProc() end
	
	//7) ȸ������ ��
	@RequestMapping(value="/user/userUpdate.do", method = RequestMethod.GET)
	public ModelAndView userUpdate(UserDTO dto) {
		ModelAndView mav = new ModelAndView();
		//userUpdate.jsp���� dto.uid�� ����Ӽ��� ������ؼ�
		mav.addObject("dto", dto);
		mav.setViewName("user/userUpdate");
		return mav;
	}//userUpdateForm() end
	
	//8) ȸ������ �Է� ��
	@RequestMapping(value="/user/userUpdateForm.do", method = RequestMethod.POST)
	public ModelAndView userUpdateForm(UserDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String root = Utility.getRoot(); // /moviemovit
		//dto�� ���� upw, hidden�� ���� uid �޾ƿ�
		//DB���� ȸ������ ��ȸ�ϱ�
		dto = dao.getMemberInfo(dto);
		
		mav.addObject("dto",dto);
		mav.addObject("root", root);
		mav.setViewName("user/userUpdateForm");
		return mav;
	}//userUpdateForm() end
	
	//9) ȸ������ ó��
	@RequestMapping(value="/user/userUpdateProc.do", method = RequestMethod.POST)
	public ModelAndView userUpdateProc(UserDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String root = Utility.getRoot(); // /moviemovit
		String msg = "";
		//dto�� ���� ���� �Է��� ȸ������ ������
		//DB�� ������ ȸ������ �Է��ϱ�
		int result = dao.updateMember(dto);
		
		if(result == 0) {
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('ȸ�������� �����߽��ϴ�');";
			msg += "  history.go(-1);";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}else {			
			msg += "<!DOCTYPE html>";
			msg += "<html><body>";
			msg += "<script>";
			msg += "  alert('ȸ�������� �Ϸ��߽��ϴ�');";
			msg += " location.href='" + root + "/index.do'";
			msg += "</script>";
			msg += "</html></body>";
			mav.addObject("msg", msg);
			mav.setViewName("msgView");
		}//if end
		return mav;

	}//userUpdateProc() end
	
	//10) ���̵� ã�� ��
	@RequestMapping(value="/user/findUserId.do", method = RequestMethod.GET)
	public String findUserId() {
		return "user/findUserId";
	}//findUseruid() end
	
	//11) ���̵� ã�� ó��
	@RequestMapping(value="/user/findUserIdProc.do", method = RequestMethod.POST)
	public ModelAndView findUserIdProc(UserDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String msg1 = "";
		String root = Utility.getRoot(); // /moviemovit
		
		//11-1) DB���� �̸��� �����ּҰ� ��ġ�ϴ� ȸ������ ���̵� �߱�
		//dto���� ���̵�, �̸�, �����ּҰ� ��ȯ
		dto = dao.findUserID(dto);
		
		//11-2) ���ϼ��� �����ϱ�
		String mailServer = "mw-002.cafe24.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.auth", "true");
		
		//11-3) ���� �������� �������� ���̵�/���
		Authenticator myAuth = new MyAuthenticator();
		
		//11-4) 2)�� 3)�� ���� ���� �����ϴ� �������� ����
		Session sess = Session.getInstance(props,myAuth);
		
		//11-5) ���� ������
		//�޴»��, �����»��, ����, ����
		try {
			req.setCharacterEncoding("UTF-8");
			String to = dto.getEmail();
			String from = "moviemovit_manager@naver.com";
			String subject = dto.getUid().trim() + "���� ���̵� �߼��մϴ�";
			String msgText = "���̵� : " + dto.getUid().trim();
			msgText += " / �ش� ���̵�� �α����Ͻñ� �ٶ��ϴ�.";
			
			//���� �� Ư������ ����
			msgText = Utility.convertChar(msgText);
			
			//�޴»��1 (������ �迭���� ����)
			InternetAddress[] address = {new InternetAddress(to)};
			
			Message msg = new MimeMessage(sess);
			
			//�޴»�� - ���� �������� �ϴ� ����� �����ּ�
			msg.setRecipients(Message.RecipientType.TO, address);
			
			//������ ���
			msg.setFrom(new InternetAddress(from));
			
			//��������
			msg.setSubject(subject);
			
			//���� ����
			//���� ���� ���� ������ html���·� �ѱ��� �������ʰ� �����ڴ�
			msg.setContent(msgText, "text/html; charset=UTF-8");
			
			//���� ����
			Transport.send(msg);
			
		} catch (Exception e) {
			System.out.println("���̵� �߼� ���� : " + e);
		}//end
		
		if(dto == null) {
			msg1 += "<!DOCTYPE html>";
			msg1 += "<html><body>";
			msg1 += "<script>";
			msg1 += "  alert('���̵� ã�� ����');";
			msg1 += "  history.go(-1);";
			msg1 += "</script>";
			msg1 += "</html></body>";
			mav.addObject("msg", msg1);
			mav.setViewName("msgView");
		}else {			
			msg1 += "<!DOCTYPE html>";
			msg1 += "<html><body>";
			msg1 += "<script>";
			msg1 += "  alert('" + dto.getUname() + " �Կ��� ������ �߼۵Ǿ����ϴ�');";
			msg1 += " location.href='" + root + "/user/loginform.do'";
			msg1 += "</script>";
			msg1 += "</html></body>";
			mav.addObject("msg", msg1);
			mav.setViewName("msgView");
		}//if end
		return mav;
	}//findUserIdProc() end
	
	//12) ��й�ȣ ã�� ��
	@RequestMapping(value="/user/findUserPw.do", method = RequestMethod.GET)
	public String findUserPw() {
		return "user/findUserPw";
	}//findUserPw() end
	
	//13) ��й�ȣ ã�� ó��
	@RequestMapping(value="/user/findUserPwProc.do", method = RequestMethod.POST)
	public ModelAndView findUserPwProc(UserDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		String root = Utility.getRoot(); // /moviemovit
		String msg1 = "";
		String uid = "";
		String uname = "";
		String email = "";
		String temPasswd = ""; //�ӽú�й�ȣ
		int pw_result = 0;
		
		//11-1) DB���� ���̵�,�̸�,�����ּҰ� ��ġ�ϴ� ȸ������ ��й�ȣ �߱�
		//dto���� ���̵�, �̸�, �����ּ�, ��й�ȣ�� ��ȯ
		dto = dao.findUserPw(dto);
		
		if(dto != null) {
			//�ӽ� ��й�ȣ ����
			temPasswd = randomPasswd(8);
			
			//�ӽ� ��й�ȣ dto�� �����ؼ� �Ʒ� DB�� ����
			dto.setUpw(temPasswd);
			
			//������ �ӽú�й�ȣ ������Ʈ
			pw_result = dao.setRandomPw(dto);
		}else {
			System.out.println("��й�ȣ ã�� ����");
		}//if end
		
		//11-2) ���ϼ��� �����ϱ�
		String mailServer = "mw-002.cafe24.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.auth", "true");
		
		//11-3) ���� �������� �������� ���̵�/���
		Authenticator myAuth = new MyAuthenticator();
		
		//11-4) 2)�� 3)�� ���� ���� �����ϴ� �������� ����
		Session sess = Session.getInstance(props,myAuth);
		
		//11-5) ���� ������
		//�޴»��, �����»��, ����, ����
		try {
			req.setCharacterEncoding("UTF-8");
			String to = dto.getEmail();
			String from = "moviemovit_manager@naver.com";
			String subject = dto.getUid().trim() + "���� �ӽ� ��й�ȣ�� �߼��մϴ�";
			String msgText = "���̵� : " + dto.getUid().trim();
			msgText += " / �ӽ� ��й�ȣ : " + dto.getUpw().trim();
			msgText += "//�ӽú�й�ȣ�� �α��� �� ��й�ȣ�� �����Ͻñ� �ٶ��ϴ�.";
			
			//���� �� Ư������ ����
			msgText = Utility.convertChar(msgText);
			
			//�޴»��1 (������ �迭���� ����)
			InternetAddress[] address = {new InternetAddress(to)};
			
			Message msg = new MimeMessage(sess);
			
			//�޴»�� - ���� �������� �ϴ� ����� �����ּ�
			msg.setRecipients(Message.RecipientType.TO, address);
			
			//������ ���
			msg.setFrom(new InternetAddress(from));
			
			//��������
			msg.setSubject(subject);
			
			//���� ����
			//���� ���� ���� ������ html���·� �ѱ��� �������ʰ� �����ڴ�
			msg.setContent(msgText, "text/html; charset=UTF-8");
			
			//���� ����
			Transport.send(msg);
			
		} catch (Exception e) {
			System.out.println("��й�ȣ �߼� ���� : " + e);
		}//end
		
		if(pw_result == 0) {
			msg1 += "<!DOCTYPE html>";
			msg1 += "<html><body>";
			msg1 += "<script>";
			msg1 += "  alert('��й�ȣ ã�� ����');";
			msg1 += "  history.go(-1);";
			msg1 += "</script>";
			msg1 += "</html></body>";
			mav.addObject("msg", msg1);
			mav.setViewName("msgView");
		}else {			
			msg1 += "<!DOCTYPE html>";
			msg1 += "<html><body>";
			msg1 += "<script>";
			msg1 += "  alert('" + dto.getUname() + " �Կ��� ������ �߼۵Ǿ����ϴ�');";
			msg1 += " location.href='" + root + "/user/loginform.do'";
			msg1 += "</script>";
			msg1 += "</html></body>";
			mav.addObject("msg", msg1);
			mav.setViewName("msgView");
		}//if end
		return mav;
	}//findUserPwProc() end
	
	  //�ӽú�й�ȣ ���� ------------8���� �������� Ȯ�� Ư�����ڰ� �ݵ�� ����!
	  public String randomPasswd(int length) {
	    int index = 0;
	    char[] charSet = new char[] {
	        '0','1','2','3','4','5','6','7','8','9'
	        ,'a','b','c','d','e','f','g','h','i','j','k','l','m'
	        ,'n','o','p','q','r','s','t','u','v','w','x','y','z'
	        ,'A','B','C','D','E','F','G','H','I','J','K','L','M'
	        ,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
	        ,'!','@','#','$','%','^','&','*'
	    };
	    StringBuffer sb = new StringBuffer();
	    for(int idx=0; idx<length; idx++) {
	      index = (int)(charSet.length * Math.random());
	      sb.append(charSet[index]);
	    }//for end
	    
	    return sb.toString();
	  }//randomPasswd() end
	
}//class end