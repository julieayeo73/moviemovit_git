package kr.co.moviemovit.user;

import javax.jws.soap.SOAPBinding.Use;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public UserDAO() {
		System.out.println("---UserDAO() ��ü����");
	}//�⺻������ 

	/*����Ͻ� ����*/
	
	//1) ȸ������ insert
	public int joinMember(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int result = mapper.joinMember(dto);
		return result;
	}//joinMember() end
	
	//2) ���̵� �ߺ�Ȯ��
	public int idCheck(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//id_result�� ���� 1�̸� �ߺ����̵�
		int id_result = mapper.idCheck(dto);
		return id_result;
	}//idCheck() end
	
	//3) �̸��� �ߺ�Ȯ��
	public int emailCheck(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//em_result�� ���� 1�̸� �ߺ����̵�
		int em_result = mapper.emailCheck(dto);
		return em_result;
	}//emailCheck() end

	/***************************************************************************/
	//1) �α���
	public UserDTO loginProc(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//loginGrade�� ���� ȸ�����
		UserDTO loginGrade = mapper.loginProc(dto);
		return loginGrade;
	}//loginProc() end
	
	//2) ȸ������ read
	public UserDTO getMemberInfo(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		dto = mapper.getMemberInfo(dto);
		return dto;
	}//getMemberInfo() end
	
	//3) ȸ�� Ż��
	public int userDelete(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//check�� ���� 1�̸� ������ ȸ�� ����
		int check = mapper.userDelete(dto);
		return check;
	}//userDelete() end
	
	//4) ȸ������
	public int updateMember(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int result = mapper.updateMember(dto);
		return result;
	}//updateMember() end
	
	//5) ���̵� ã��
	public UserDTO findUserID(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		dto = mapper.findUserID(dto);
		return dto;
	}//findUserID() end
	
	//6) ��й�ȣ ã��
	public UserDTO findUserPw(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		dto = mapper.findUserPw(dto);
		return dto;
	}//findUserPw() end
	
	//7) ������ �ӽú�й�ȣ ������Ʈ
	public int setRandomPw(UserDTO dto) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int pw_result = mapper.setRandomPw(dto);
		return pw_result;
	}//setRandomPw() end
}//class end
