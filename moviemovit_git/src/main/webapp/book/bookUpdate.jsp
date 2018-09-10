<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%-- 본문시작 bookUpdate.jsp : 예매 수정 폼 --%>

<!-- 예매는 수정이 안되지 않나 ??? 회의하기!! -->

<head>
<link href="../css/joinFormStyle.css" rel="stylesheet" type="text/css">
<style>
.cbp-mc-column th {
  text-align: right;
}
.cbp-mc-column td {
  text-align: left;
  padding: 10px;
  margin: auto;
}
.container-fluid .in {
  position: absolute;
  top: 200;
  z-index: 20000;
}
.modal-backdrop {
  z-index: 9999;
}

/* placeholder 속성 */
::-webkit-input-placeholder {  /* 크롬 */
  font-size: 18px;
  font-style: normal;
}
:-moz-placeholder {    /* 파이어폭스 */
  font-size: 18px;
  font-style: normal;
}
::-moz-placeholder {
  font-size: 18;
  font-style: normal;
}
:-ms-input-placeholder {   /* 익스 */
  font-size: 18px;
  font-style: normal;
}
</style>
</head>



<form class="cbp-mc-form" name="regForm" method='POST' action='./update.do' onsubmit="return couponCheck(this)">
  <span style="color: red; font-weight: bold">* 필수입력</span><br>
  <input type="hidden" name="cCode" value="${dto.cCode }">
  <table class="cbp-mc-column">
  <tr>
    <th>* 회원 아이디</th>
    <td>
      <input type="text" id="uid" name="uid" placeholder=${dto.uid } required readonly> &nbsp;
      <input class="cbp-mc-button" type="button" value="ID검색" onclick="openPopup(this.form);">
    </td>
  </tr>
  <tr>
    <th>쿠폰 발행 날짜</th>
    <td>
      <input type="date" id="startDate" name="startDate" value=${dto.startDate }>
    </td>
  </tr>
  <tr>
    <th>쿠폰 만료 날짜</th>
    <td>
      <input type="date" id="expDate" name="expDate" value=${dto.expDate }>
    </td>
  </tr>
  </table>
  
  <br>
  <!-- 하단 버튼 -->
  <div class="cbp-mc-submit-wrap">
    <input class="cbp-mc-submit" type="submit" value="수정" />
    <input class="cbp-mc-submit" type="reset" value="다시입력" />
    <input class="cbp-mc-submit" type="button" value="취소" onclick="javascript:history.back()" />
  </div>
  
</form>


<script>
function openPopup(frm){
  //사용자 모니터 해상도 값
  var sx=parseInt(screen.width);
  var sy=parseInt(screen.height);
  var x=sx/3;
  var y=sy/2;
  
  // 팝업창 오픈 및 위치 이동
  var url = "uidSearch.do";
  var win=window.open(url,"uidSearch","width="+x+",height="+y);
  var movex = x;
  var movey = y/3;
  win.moveTo(movex, movey);
}

// 쿠폰 등록 전 유효성 검사
function couponCheck(f) {
  // 회원아이디 글자갯수가 5~10 사이인지?
  var uid = f.uid.value;
  uid = uid.trim(); //공백 지우기
  
  if(uid.length<5 || uid.length>10) {
   alert("아이디를 확인해주세요.");
   f.uid.value="";  //초기화
   f.uid.focus();   //커서 생성
   return false;
  }//if end
  
  return true;  // 유효성 검사 모두 통과
}//couponCheck
</script>


<%-- 본문끝 --%>
<%@ include file="../footer.jsp"%>