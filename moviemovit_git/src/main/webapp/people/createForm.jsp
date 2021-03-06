<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<%-- 본문시작 people/createForm.jsp --%>
<head>

<link href="../css/joinFormStyle.css" rel="stylesheet" type="text/css">

<style>
/*180827 경민 list 클래스 추가*/
.list {
	text-align:left;
	padding:10px;
	margin:auto;
}

.header2 {
    text-align:center;
    font-size:20px;
}


</style>
</head>
<div class="header2">인물 등록</div>
<form class="cbp-mc-form" name="regForm" method="POST" action="./create.do"
      enctype="multipart/form-data" onsubmit="return peopleCheck(this)" >
    <span style="color: red; font-weight: bold">* 필수입력</span><br>
    <input type='hidden' name='peoCode' value='${peoCode }'>
	<!-- 인물등록 -->
	<table class="cbp-mc-column">	
	<tr>
		<th>*이름</th>
		<td class="list">
			<input type="text" id="peoName" name="peoName" placeholder="이름" required>
		</td>
	</tr>
	<tr>
		<th>*국적</th>
		<td class="list">
		  <select id="country" name="country" required>
            <option value="가나">가나</option>
            <option value="가봉">가봉</option>
            <option value="가이아나">가이아나</option>
            <option value="감비아">감비아</option>
            <option value="과테말라">과테말라</option>
            <option value="그레나다">그레나다</option>
            <option value="그루지야">그루지야</option>
            <option value="기니">기니</option>
            <option value="나미비아">나미비아</option>
            <option value="나이지리아">나이지리아</option> 
            <option value="남아프리카공화국">남아프리카공화국</option> 
            <option value="네팔">네팔</option>
            <option value="노르웨이">노르웨이</option>
            <option value="뉴질랜드">뉴질랜드</option>
            <option value="니제르">니제르</option>
            <option value="니카라과">니카라과</option>
            <option selected value="대한민국">대한민국</option>
            <option value="덴마크">덴마크</option>
            <option value="도미니카연방">도미니카연방</option>
            <option value="도미니카공화국">도미니카공화국</option> 
            <option value="독일">독일</option>
            <option value="라오스">라오스</option>
            <option value="라트비아">라트비아</option>
            <option value="러시아">러시아</option>
            <option value="레바논">레바논</option>
            <option value="레소토">레소토</option>
            <option value="루마니아">루마니아</option>
            <option value="르완다">르완다</option>
            <option value="룩셈부르크">룩셈부르크</option>
            <option value="라이베리아">라이베리아</option>
            <option value="리비아">리비아</option>
            <option value="리투아니아">리투아니아</option>
            <option value="리첸쉬테인">리첸쉬테인</option>
            <option value="마다가스카르">마다가스카르</option>
            <option value="마케도니아">마케도니아</option>
            <option value="말라위">말라위</option>
            <option value="말레이지아">말레이지아</option> 
            <option value="말리">말리</option>
            <option value="몰타">몰타</option>
            <option value="멕시코">멕시코</option>
            <option value="모로코">모로코</option>
            <option value="모리셔스">모리셔스</option>
            <option value="모리타니">모리타니</option>
            <option value="모잠비크">모잠비크</option>
            <option value="몰도바">몰도바</option>
            <option value="몰디브">몰디브</option>
            <option value="몽고">몽고</option>
            <option value="미국">미국</option>
            <option value="미얀마">미얀마</option>
            <option value="바누아투">바누아투</option>
            <option value="바레인">바레인</option>
            <option value="바베이도스">바베이도스</option> 
            <option value="바하마">바하마</option>
            <option value="방글라데시">방글라데시</option> 
            <option value="벨라루스">벨라루스</option>
            <option value="베네수엘라">베네수엘라</option> 
            <option value="베넹">베넹</option>
            <option value="베트남">베트남</option>
            <option value="벨기에">벨기에</option>
            <option value="보스니아헤르체코비나">보스니아헤르체코비나</option>
            <option value="보츠와나">보츠와나</option>
            <option value="볼리비아">볼리비아</option>
            <option value="부르키나파소">부르키나파소</option>
            <option value="부탄">부탄</option>
            <option value="불가리아">불가리아</option>
            <option value="브라질">브라질</option>
            <option value="브룬디">브룬디</option>
            <option value="사우디아라비아">사우디아라비아</option>
            <option value="사이프러스">사이프러스</option>
            <option value="산마리노">산마리노</option>
            <option value="세네갈">세네갈</option>
            <option value="세이셸">세이셸</option>
            <option value="세인트루시아">세인트루시아</option>
            <option value="세인트빈센트그레나딘">세인트빈센트그레나딘 </option>
            <option value="세인트키츠네비스">세인트키츠네비스</option>
            <option value="솔로몬아일란드">솔로몬아일란드 </option>
            <option value="수리남">수리남</option>
            <option value="스리랑카">스리랑카</option>
            <option value="스와질랜드">스와질랜드</option>
            <option value="스웨덴">스웨덴</option>
            <option value="스페인">스페인</option>
            <option value="스위스">스위스</option>
            <option value="슬로바키아">슬로바키아</option>
            <option value="슬로베니아">슬로베니아</option>
            <option value="시에라리온">시에라리온 </option>
            <option value="싱가포르">싱가포르</option>
            <option value="아랍에미레이트연합국">아랍에미레이트연합국</option>
            <option value="아르메니아">아르메니아</option>
            <option value="아르헨티나">아르헨티나</option>
            <option value="아이슬란드">아이슬란드</option>
            <option value="아이티">아이티</option>
            <option value="아프가니스탄">아프가니스탄</option>
            <option value="안도라">안도라</option>
            <option value="앤티과바부다">앤티과바부다</option>
            <option value="알바니아">알바니아</option>
            <option value="알제리">알제리</option>
            <option value="앙골라">앙골라</option>
            <option value="에스토니아">에스토니아</option>
            <option value="에콰도르">에콰도르</option>
            <option value="엘살바도르">엘살바도르</option>
            <option value="영국">영국</option>
            <option value="예멘">예멘</option>
            <option value="오만">오만</option>
            <option value="오스트리아">오스트리아</option>
            <option value="온두라스">온두라스</option>
            <option value="요르단">요르단</option>
            <option value="우간다">우간다</option>
            <option value="우루과이">우루과이</option>
            <option value="우즈베크">우즈베크</option>
            <option value="우크라이나">우크라이나</option>
            <option value="이디오피아">이디오피아</option>
            <option value="이라크">이라크</option>
            <option value="이란">이란</option>
            <option value="이스라엘">이스라엘</option>
            <option value="이탈리아">이탈리아</option>
            <option value="이집트">이집트</option>
            <option value="인도네시아">인도네시아</option>
            <option value="일본">일본</option>
            <option value="자메이카">자메이카</option>
            <option value="잠비아">잠비아</option>
            <option value="중국">중국</option>
            <option value="중앙아프리카">중앙아프리카</option>
            <option value="지부티">지부티</option>
            <option value="짐바브웨">짐바브웨</option>
            <option value="차드">차드</option>
            <option value="체코">체코</option>
            <option value="체코슬로바키아">체코슬로바키아</option>
            <option value="칠레">칠레</option>
            <option value="카나다">카나다</option>
            <option value="카메룬">카메룬</option>
            <option value="카보베르데">카보베르데</option>
            <option value="카자흐">카자흐</option>
            <option value="카타르">카타르</option>
            <option value="캄보디아">캄보디아</option>
            <option value="케냐">케냐</option>
            <option value="코스타리카">코스타리카</option>
            <option value="코트디봐르">코트디봐르</option>
            <option value="콜롬비아">콜롬비아</option>
            <option value="콩고">콩고</option>
            <option value="쿠바">쿠바</option>
            <option value="쿠웨이트">쿠웨이트</option>
            <option value="크로아티아">크로아티아</option>
            <option value="키르키즈스탄">키르키즈스탄</option>
            <option value="키리바티">키리바티</option>
            <option value="타지키스탄">타지키스탄</option>
            <option value="탄자니아">탄자니아</option>
            <option value="터키">터키</option>
            <option value="토고">토고</option>
            <option value="통가">통가</option>
            <option value="튀니지">튀니지</option>
            <option value="트리니다드토바고">트리니다드토바고</option>
            <option value="파나마">파나마</option>
            <option value="파라과이">파라과이</option>
            <option value="파키스탄">파키스탄</option>
            <option value="파푸아뉴기니">파푸아뉴기니</option>
            <option value="페루">페루</option>
            <option value="포르투갈">포르투갈</option>
            <option value="폴란드">폴란드</option>
            <option value="프랑스">프랑스</option>
            <option value="피지">피지</option>
            <option value="필란드">필란드</option>
            <option value="필리핀">필리핀</option>
            <option value="헝가리">헝가리</option>
		  </select>
		</td>
	</tr>
	<tr>
		<th>*출생년도</th>
		<td class="list">
		  <input type="date" id="peoBirth" name="peoBirth" required>
		</td>
	</tr>
	<tr>
		<th>*성별</th>
		<td class="list">
			<select id="gender" name="gender" required>
            <option value="M">남성</option>
            <option value="W">여성</option>
            </select>
		</td>
	</tr>
	<tr>
		<th>*사진</th>
		<td>
	      <input type="file" id="posterMF" name="posterMF">
	    </td>
	</tr>
	
	</table>
	
	<!-- 하단 버튼 -->
	<div class="cbp-mc-submit-wrap">
		<input class="cbp-mc-button" type="submit" value="등록" />
		<input class="cbp-mc-button" type="reset" value="다시입력" />
		<input class="cbp-mc-button" type="button" value="인물목록" onclick="location.href='./peopleList.do'"/>
	</div>
	
	<!-- 인물 중복 검사 -->
	<!-- 아이디, 이메일 중복검사 -->
	<script src="js/jquery.js"></script>
	<script>
		$("#peopleCheck").click(function() {
			//1단계 : 컨트롤요소의 값 가져와서 서버로 전달하기
			var peoName = "peoName=" + $("peoName").val();
			$.get("/people/peoplecheck.do", peoName, CheckPeople, "text");
		}//click end
		
		function CheckPeople(result) {
			//2단계 : 서버에서 응답받은 값 받기
			var people_result = eval(result.people_result); //숫자형 변환
			if(id_result==0) {
				$("panel_id").css("color","blue");
				$("panel_id").html("사용 가능한 아이디입니다");
			}else {
				$("#panel_id").css("color","red");
				$("#panel_id").html("중복 아이디입니다");
				$("#uid").focus();
			}//if end
			
		}//CheckID() end
	</script>
	
	
</form>

<%-- 본문끝 --%>
<%@ include file="../footer.jsp"%>