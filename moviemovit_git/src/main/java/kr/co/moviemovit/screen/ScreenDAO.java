package kr.co.moviemovit.screen;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.moviemovit.review.CinemaDTO;

@Component
public class ScreenDAO {
	@Autowired
	SqlSession sqlSession;
	
	public ScreenDAO() {
		System.out.println("---ScreenDAO() ��ü ����");
	}//�⺻ ������
	
	/*����Ͻ� ����*/
	
	//��ȭ�� �귣�忡 �ش��ϴ� ������������
	public ArrayList<CinemaDTO> cineNameList(String brandName) {
		ScreenMapper mapper = sqlSession.getMapper(ScreenMapper.class);
		ArrayList<CinemaDTO> cineList = mapper.cineNameList(brandName);
		return cineList;
	}//ChoosecineName() end
}//class end
