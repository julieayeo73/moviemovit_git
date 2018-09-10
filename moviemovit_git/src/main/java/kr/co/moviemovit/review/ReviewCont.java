package kr.co.moviemovit.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class ReviewCont {
  
  @Autowired
  ReviewDAO dao;
  
  public ReviewCont(){
    
    System.out.println("----------------ReviewCon created");
    
  } //
  
   
  //////////////////////////////// CINEMA  ////////////////////////////////
  
  
  // CREATE
  
  // CINEMA INSERT
  @RequestMapping(value="/review/cinemaForm.do", method=RequestMethod.GET)
  public ModelAndView cinemaForm() {
    
    ModelAndView mav= new ModelAndView();
    mav.setViewName("review/cinemaForm");
    return mav;
    
  } // 
  
  // CINEMA INSERT PROCESS
  @RequestMapping(value="/review/cinemaForm.do", method=RequestMethod.POST)
  public ModelAndView cinemaForm(CinemaDTO dto, HttpServletRequest req) {

    ModelAndView mav= new ModelAndView();
    mav.setViewName("msgView");
    
    // cinema IMG file
    mav.addObject("root", Utility.getRoot());
    // Real Path
    String basePath = req.getRealPath("/review/img");
    MultipartFile logoImgMF = dto.getLogoImgMF();
    
    
    // �̹����� ÷�ε��� �ʾ����� ����Ʈ �̹����� SET
    if(logoImgMF.isEmpty()) {  
      dto.setLogoImg("default.png");
    } else { 
      String logoImg = UploadSaveManager.saveFileSpring30(logoImgMF, basePath);
      dto.setLogoImg(logoImg);
    }
    
    // CINECODE�� NVL�� ��������� �ʱ� ������ ������ ����
    // BRAND�� ���ڸ� + �ش� �귣�� �� ���° ��ȭ������ cincodeset
    String cineCode = dao.setCineCode(dto.getBrandName());
    dto.setCineCode(cineCode);
    int count = dao.cinemaForm(dto);
    
    if(count==0) {
      mav.addObject("msg1", "<p>��ȭ�� ��� ����</p>");
      mav.addObject("img", "<img src='../img/fail.png'>");
      mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
      mav.addObject("link2", "<input type='button' value='�������' onclick='location.href=\"./cinemaList.do?cineCode=" + dto.getCineCode() + "\"'>");
    } else {
      mav.addObject("msg1", "<p>��ȭ�� ��� ����</p>");
      mav.addObject("img", "<img src='../img/success.jpg'>");
      mav.addObject("link1", "<input type='button' value='�������' onclick='location.href=\"./cinemaList.do?cineCode=" + dto.getCineCode() + "\"'>");      
    }

    return mav;
  } // 
  
  // CREATE : ����, �浵�� ��� ���� ����
  @RequestMapping(value="/review/geoCoding.do")
  public void geoCoding(HttpServletRequest req, HttpServletResponse response) throws Exception{
    String location = req.getParameter("addr");

    Geocoder geocoder = new Geocoder();
    // setAddress : ��ȯ�Ϸ��� �ּ� (��⵵ ������ �д籸 ��)
    // setLanguate : ���ڵ� ����
    GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
    GeocodeResponse geocoderResponse;
    geocoderResponse = geocoder.geocode(geocoderRequest);
          if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
                GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
                LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
                                         
                Float[] coords = new Float[2];
                coords[0] = latitudeLongitude.getLat().floatValue();
                coords[1] = latitudeLongitude.getLng().floatValue();
                String coordStr = Float.toString(coords[0])+"&"+Float.toString(coords[1]);
                
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            response.getWriter().write(coordStr); // ��������ȯ
          }
  } // 
  
  
  ///////////////////////////////READ

  @RequestMapping(value="/review/cinemaRead.do", method=RequestMethod.GET)
  public ModelAndView cinemaRead(CinemaDTO dto) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("review/cinemaRead");
    dto = dao.cinemaRead(dto);
    ArrayList<ReviewStar> reviewstar = dao.reviewstar();
 /*   ReviewStarDTO reviewstardto = dao.reviewstardto();*/
//    mav.addObject("reviewstardto", reviewstardto);
    mav.addObject("reviewstar", reviewstar);
    mav.addObject("dto", dto);
    return mav;
    
  }
  

  
  //////////////////////////////////////// UPDATE
  
  //GET
  @RequestMapping(value="/review/cinemaUpdate.do", method = RequestMethod.GET)
  public ModelAndView cinemaUpdate(CinemaDTO dto) {
    ModelAndView mav = new ModelAndView();
    // dto�� ���� dto�� ������Ʈ
    dto = dao.cinemaRead(dto);
    mav.addObject("dto", dto);
    mav.setViewName("review/cinemaUpdate");
    return mav;
  }//userUpdateForm() end
  
  //POST
  @RequestMapping(value="/review/cinemaUpdate.do", method = RequestMethod.POST)
  public ModelAndView cinemaUpdateProc(CinemaDTO dto, HttpServletRequest req) {
   
      ModelAndView mav = new ModelAndView();
      mav.setViewName("msgView");
      
      String cineCode = req.getParameter("cineCode");
  
      CinemaDTO oldDTO = dao.cinemaRead(dto);
      
      dto.setCineCode(cineCode);

      // ���۵Ǵ� ������ ����Ǵ� ���� ���
      String basePath = req.getSession().getServletContext().getRealPath("/review/img");

      MultipartFile logoImgMF = dto.getLogoImgMF();
      MultipartFile oldlogoImgMF = oldDTO.getLogoImgMF();
      
      if (logoImgMF.getSize() > 0) {
        UploadSaveManager.deleteFile(basePath, oldlogoImgMF);
        // ���ο� ���� ���� ��� �����ϰ� �ϱ�
        // poster posterMF ???
        String logoImg = UploadSaveManager.saveFileSpring30(logoImgMF, basePath);
        dto.setLogoImg(logoImg);
      } else {
        // ���������� ���� ����
        dto.setLogoImg(oldDTO.getLogoImg());
      }

      int cnt = dao.cinemaUpdate(dto);

      if (cnt == 0) {
        mav.addObject("msg1", "<p>����</p>");
        mav.addObject("img", "<img src='../img/fail.png'>");
        mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
        mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./reviewlist.do?cineCode="
            + dto.getCineCode() + "\"'>");
      } else {
        mav.addObject("msg1", "<p>���� ����</p>");
        mav.addObject("img", "<img src='../img/success.jpg'>");
        mav.addObject("link1", "<input type='button' value='�׷���' onclick='location.href=\"./reviewlist.do?cineCode="
            + dto.getCineCode() + "\"'>");
      } // if end

      return mav;

    } // end

  
  ////////////////////////////////////// DELETE
  
  //GET
  @RequestMapping(value="/review/cinemaDelete.do", method = RequestMethod.GET)
  public ModelAndView cinemaDelete(HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    // dto�� ���� dto�� ������Ʈ
    String cineCode = req.getParameter("cineCode");
    
    mav.addObject("cineCode", cineCode);
    mav.addObject("msg1", "<p>�����Ͻðڽ��ϱ�?</p>");
    mav.addObject("img", "<img src='/img/default.png'>");
    mav.addObject("link2", "<input type='button' value='���' onclick='javascript:history.back()'>");
    mav.addObject("link1", "<input type='button' value='Ȯ��' onclick='location.href=\"./cinemaDeletePro.do?cineCode="
        + cineCode + "\"'>");
    
    mav.setViewName("msgView");
    return mav;
  }//userUpdateForm() end
  
  //DELETE PROCESS
  
  @RequestMapping(value="/review/cinemaDeletePro.do", method = RequestMethod.GET)
  public ModelAndView cinemaDeletePro(HttpServletRequest req) {
    ModelAndView mav = new ModelAndView();
    // dto�� ���� dto�� ������Ʈ
    String cineCode = req.getParameter("cineCode");
    
    int count = dao.cinemaDeletePro(cineCode);
    
    if (count==0) {
      mav.addObject("msg1", "<p>���� ����</p>");
      mav.addObject("img", "<img src='../img/fail.png'>");
      mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascript:history.back()'>");
      mav.addObject("link2", "<input type='button' value='���' onclick='location.href=\"./cinemaList.do'>");
    } else {
      mav.addObject("msg1", "<p>���� ����</p>");
      mav.addObject("img", "<img src='../img/success.jpg'>");
      mav.addObject("link1", "<input type='button' value='���' onclick='location.href=\"./cinemaList.do'>");
    } // if end

    mav.setViewName("msgView");
    
   return mav;
   
  }
  
  ////////////////////////////////////// LIST
  @RequestMapping(value="/review/cinemaList.do")
  public ModelAndView cinemaList() {
    
    ModelAndView mav= new ModelAndView();
    mav.setViewName("review/cinemaList");
    ArrayList<CinemaDTO> list = dao.cinemaList();
    ArrayList<ReviewStar> reviewstar = dao.reviewstar();
    mav.addObject("list", list);
    mav.addObject("reviewstar", reviewstar);
    return mav;
  } 

  
  @RequestMapping(value="/review/search.do", method=RequestMethod.GET) 
  public ModelAndView search(HttpServletRequest request) { 
  
    ModelAndView mav= new ModelAndView();
    mav.setViewName("review/cinemaList");
    
    System.out.println(request.getParameter("sch_type"));
    
    String sch_type = request.getParameter("sch_type"); 
    String sch_value = request.getParameter("sch_value"); 
    
    ArrayList<CinemaDTO> list = dao.search(sch_type, sch_value);
    ArrayList<ReviewStar> reviewstar = dao.reviewstar();
    
    
    mav.addObject("list", list); 
    mav.addObject("reviewstar", reviewstar); 
   
    return mav; 
    
  }
  
  //ī�װ�
  /*
  @RequestMapping(value="/review/cate.do", method=RequestMethod.GET) 
  public ModelAndView cate(HttpServletRequest request, @RequestParam("checkArr[]")List<String> list) { 
    
    System.out.println(list);
  
    /*ModelAndView mav= new ModelAndView();
    /*ModelAndView mav = new ModelAndView(new MappingJacksonJsonView());
  
    ArrayList<CinemaDTO> category = dao.cate(list);
    System.out.println(category.size());
    
  ArrayList<ReviewStar> reviewstar = dao.reviewstar();
   
    mav.addObject("list", category); 
    mav.addObject("reviewstar", reviewstar); 
   
    mav.setViewName("redirect:/review/reviewlist.do");
  
    return m;
      
  }
  */
  

  
  ////////////////////////////// REVIEW  ////////////////////////////////////////////////////////////////////////////
  

  /*0906 �������� �ű�� ��*/
  @RequestMapping(value="/review/reviewForm.do", method=RequestMethod.GET)
  public ModelAndView reviewForm() {
    ModelAndView mav= new ModelAndView();
    mav.setViewName("review/reviewForm");
    return mav;
  } // 
  
  /*0905*/
  @RequestMapping(value="/review/reviewForm.do", method=RequestMethod.POST)
  public ModelAndView reviewform(ReviewStar sdto) {
    ModelAndView mav= new ModelAndView();
    mav.setViewName("redirect:/review/cinemaList.do");
    int count = dao.reviewForm(sdto);
    mav.addObject("count", count);
    return mav;
  } // POST

} // class end
