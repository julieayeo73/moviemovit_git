package kr.co.moviemovit.user;

public interface UserMapper {
	/*
		MyBatis3�� ����(notice.xml)�� ��ϵǾ� �ִ� id�� �������̽��� �޼ҵ忡
		�ٽ� �ѹ� ��������ν� MyBatis3���� ȣ���ϱ� ���ϵ��� ������.
		
		��id�� method������ �;���.
	 */

	/*ȸ������*/
	//<insert id="joinMember">
	public int joinMember(UserDTO dto);
	
	/*���̵� �ߺ�Ȯ��*/
	//<select id="idCheck">
	public int idCheck(UserDTO dto);
	
	/*�̸��� �ߺ�Ȯ��*/
	//<select id="emailCheck">
	public int emailCheck(UserDTO dto);
	/***************************************************************************/
	/*�α���*/
	//<select id="loginProc">
	public UserDTO loginProc(UserDTO dto);
	
	/*ȸ������*/
	//<select id="getMemberInfo">
	public UserDTO getMemberInfo(UserDTO dto);
	
	/*ȸ��Ż��*/
	//<update id="userDelete">
	public int userDelete(UserDTO dto);
	
	/*ȸ������*/
	//<update id="updateMember">
	public int updateMember(UserDTO dto);
	
	/*���̵� ã��*/
	//<select id="findUserID">
	public UserDTO findUserID(UserDTO dto);
	
	/*��й�ȣ ã��*/
	//<select id="findUserPw">
	public UserDTO findUserPw(UserDTO dto);
	
	/*�ӽú�й�ȣ ������Ʈ*/
	//<update id="setRandomPw">
	public int setRandomPw(UserDTO dto);
}//interface end