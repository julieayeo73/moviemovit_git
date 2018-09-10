package kr.co.moviemovit.screen;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviemovit.review.CinemaDTO;

@Controller
public class ScreenCont {
	@Autowired
	ScreenDAO dao;
	
	public ScreenCont() {
		System.out.println("---ScreenCont() ��ü ����");
	}//�⺻ ������
	
	//�󿵽ð��� 3�ϸ��� ������ �����
	//���Ȯ��
	//http://localhost:9090/moviemovit/screen/screenform.do
	
	//1-1) �󿵽ð� ��� ��
	@RequestMapping(value="/screen/screenform.do", method = RequestMethod.GET)
	public ModelAndView screenForm() {
		ModelAndView mav = new ModelAndView();
		
		/*
			screenTable���� �󿵰� ��� ����
			- ��ȭ�� �귣����� screenForm.jsp���� �Է��� ��
			- ��ȭ�� �������� cinemaTable -> DB���� �� ��ȭ�� ���� �ڵ�� ���(�ϴ� ��ɾ� �̿�)
			- �󿵰� �̸��� screenForm.jsp���� �Է��� ��
			  �׷��� screenTable�� �󿵰� Į���� ���� �� ������� where���� ã�Ƽ� �����ϴ� �������!
			- ��ȭ�̸��� ������ â�� ����� movieTable���� �˻��ؼ� ����ϵ���!
		*/		
		mav.setViewName("screen/screenForm");
		return mav;
	}//screenForm() end
	
	//1-2) ��ȭ�� �귣����� Ŭ���ϸ� �ش� ������ ���
	@RequestMapping(value="/screen/cineNameList.do", method = RequestMethod.POST)
	public void cineNameList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		//screenForm.jsp���� �ѱ� brandName����
		String brandName = req.getParameter("brandName");

		
		//CinemaDTO�� brandName set�ϱ�
		CinemaDTO dto = new CinemaDTO();
		
		//DB���� ��ȭ�� �귣�忡 �ش��ϴ� ���� ��������
		ArrayList<CinemaDTO> cineList = dao.cineNameList(brandName);
		
		//screenForm.jsp�� ���� ��
		String cineMsg = "";
		if(cineList!=null) {
			cineMsg += "<select id='cineName' name='cineName'>";
			cineMsg += "		<option value='0'>�����ϼ���</option>";
			for(int idx = 0; idx<cineList.size(); idx++) {
				dto = cineList.get(idx);
				cineMsg += "<option value='" + dto.getCineCode() + "'>" + dto.getCineName() + "</option>";
			}
			cineMsg += "</select>";
		}else {
			cineMsg += "�ش� �귣���� ������ �������µ� �����߽��ϴ�";
		}//if end
        
		resp.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println(cineMsg);
		out.flush();
		out.close();
		
	}//cineNameList() end
	
}//class end
