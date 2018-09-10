package kr.co.moviemovit;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * moviemovit ������Ʈ ù ������ ���
 * ���Ȯ�� http://localhost:9090/moviemovit/index.do
*/

@Controller
public class HomeController {
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		return "index"; // / + index + .jsp
	}//index() end
	
}//class end
