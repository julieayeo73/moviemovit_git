package kr.co.moviemovit.screen;

import java.util.ArrayList;

import kr.co.moviemovit.review.CinemaDTO;

public interface ScreenMapper {
	/*		
		��id�� method������ �;���.
	 */
	//<select id="cineNameList">
	public ArrayList<CinemaDTO> cineNameList(String brandName);
}//interface end