package kr.co.moviemovit.notice;

import java.util.ArrayList;


public interface NoticeMapper {
	/*
		MyBatis3�� ����(notice.xml)�� ��ϵǾ� �ִ� id�� �������̽��� �޼ҵ忡
		�ٽ� �ѹ� ��������ν� MyBatis3���� ȣ���ϱ� ���ϵ��� ������.
		
		��id�� method������ �;���.
	*/
	
	//<insert id="create">
	public int create(NoticeDTO dto);
	
	//<select id="list" resultType="NoticeDTO">
	public ArrayList<NoticeDTO> list(NoticePage noticepage);

	public int listCnt();
	
	//<select id="read" parameterType="NoticeDTO" resultType="NoticeDTO">
	public NoticeDTO read(NoticeDTO dto);

	//<delete id="delete" parameterType="NoticeDTO">
	public int delete(NoticeDTO dto);
	
	//<update id="update" parameterType="NoticeDTO">
	public int update(NoticeDTO dto);
}//interface end
