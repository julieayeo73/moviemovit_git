package kr.co.moviemovit.people;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviemovit.movie.MovieDTO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class PeopleCont {
	
	@Autowired
	PeopleDAO dao;

	public PeopleCont() {
		System.out.println("---------PeopleCont() ��ü ����");
	}//�⺻������
	
	//���Ȯ��
	//http://localhost:9090/moviemovit/people/create.do
	
	@RequestMapping(value="/people/create.do", method=RequestMethod.GET)
	  public ModelAndView createForm(PeopleDTO dto) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/createForm");
	    mav.addObject("list", dao.list());
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("peoCode", dto.getPeoCode());    
	    return mav;
	  }//createForm() end 
		
	@RequestMapping(value="/people/create.do", method=RequestMethod.POST)
	  public ModelAndView createProc(PeopleDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/msgView");
	    mav.addObject("root", Utility.getRoot());
	    
	   
	//------------------------------------------------------------    
	//  ���۵� ������ ����Ǵ� ���� ���
	    String basePath = req.getRealPath("/people/storage");
	    MultipartFile posterMF = dto.getPosterMF();
	    String peoPic = UploadSaveManager.saveFileSpring30(posterMF, basePath);
	    dto.setPeoPic(peoPic);
	    dto.setFilesize(posterMF.getSize());
	//------------------------------------------------------------   
	    if(dto.getCountry()==null) {
	    	dto.setCountry("default");
	    }
	    
	    if(dto.getGender().equals("W")) {
	    	dto.setGender("����");
	    } else if(dto.getGender().equals("M")) {
	    	dto.setGender("����");
	    }
	    
	    
	    	    
	    int cnt=dao.create(dto);
	    if(cnt==0) {
	      mav.addObject("msg1", "<p>�ι� ��� ����</p>");
	      mav.addObject("img", "<img src='../img/fail.png'>");
	      mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
	      mav.addObject("link2", "<input type='button' value='�������' onclick='location.href=\"./peoList.do?peoCode=" + dto.getPeoCode() + "\"'>");
	    }else {
	      mav.addObject("msg1", "<p>�ι� ��� ����</p>");
	      mav.addObject("img", "<img src='../img/success.jpg'>");
	      mav.addObject("link1", "<input type='button' value='�������' onclick='location.href=\"./peoList.do\"'>");      
	    }
	    return mav;
	       
	 }//createProc() end
	
	@RequestMapping("/people/peoList.do")
	 public ModelAndView list(PeopleDTO dto) {
	     ModelAndView mav = new ModelAndView();
	     mav.setViewName("people/peoList");
	     ArrayList<PeopleDTO> list = dao.list();
	     mav.addObject("list", list);
	     return mav;
	 }//list() end
	
	
	@RequestMapping(value="/people/peoRead.do", method=RequestMethod.GET)
	 public ModelAndView read(PeopleDTO dto) {
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("people/peoRead");
		 dto = dao.read(dto); 
		 mav.addObject("dto", dto);
		 return mav;    
	 }//read() end
	
	@RequestMapping(value="/people/delete.do", method=RequestMethod.GET)
	 public ModelAndView deleteForm(PeopleDTO dto) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/deleteForm");
	    dto = dao.read(dto); 
	    mav.addObject("dto", dto);
	    return mav;    
	 }//deleteForm() end
	
	@RequestMapping(value="/people/delete.do", method=RequestMethod.POST)
	  public ModelAndView deleteProc(PeopleDTO dto) {
	  ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/msgView");
	    int cnt = dao.delete(dto);
	    if(cnt==0) {
	      mav.addObject("msg1", "<p>�ι� ���� ����</p>");
	      mav.addObject("img", "<img src='../img/fail.png'>");
	      mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
	      mav.addObject("link2", "<input type='button' value='���' onclick='location.href=\"./peoList.do\"'>");
	    }else {
	      mav.addObject("msg1", "<p>�ι� ���� ����</p>");
	      mav.addObject("img", "<img src='../img/success.jpg'>");
	      mav.addObject("link2", "<input type='button' value='���' onclick='location.href=\"./peoList.do\"'>");
	    }//if end
	    return mav;    
	 }//deleteProc() end
	
	 @RequestMapping(value="/people/update.do", method=RequestMethod.GET)
	  public ModelAndView updateForm(PeopleDTO dto) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/updateForm");
	    mav.addObject("root", Utility.getRoot());
	    mav.addObject("getPeoCode", dto.getPeoCode());   
	    dto = dao.read(dto); 
	    mav.addObject("dto", dto);
	    return mav;    
	  }//deleteForm() end
	  
	  @RequestMapping(value="/people/update.do", method=RequestMethod.POST)
	  public ModelAndView updateProc(PeopleDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("people/msgView");
	    mav.addObject("root", Utility.getRoot());
	    //------------------------------------------------------------    
		//���۵� ������ ����Ǵ� ���� ���
		  String basePath = req.getRealPath("/people/storage");
		//<input type='file' name='posterMF'>
		  MultipartFile posterMF = dto.getPosterMF();
		  String peoPic = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		  dto.setPeoPic(peoPic);
		  dto.setFilesize(posterMF.getSize());
		//------------------------------------------------------------   
				
		int cnt = dao.update(dto);
	    
		if(cnt==0) {
	      mav.addObject("msg1", "<p>�ι� ���� ����</p>");
	      mav.addObject("img", "<img src='../img/fail.png'>");
	      mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
	      mav.addObject("link2", "<input type='button' value='�ι����' onclick='location.href=\"./peoList.do\"'>");
	    }else {
	      mav.addObject("msg1", "<p>�ι� ���� ����</p>");
	      mav.addObject("img", "<img src='../img/success.jpg'>");
	      mav.addObject("link2", "<input type='button' value='�ι����' onclick='location.href=\"./peoList.do\"'>");
	    }//if end
	    return mav;    
	  }//deleteProc() end
	

}//class end
