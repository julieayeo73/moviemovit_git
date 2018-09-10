<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ include file="../header.jsp"%>
<%-- 본문시작 bookList.jsp --%>

<style>
.header {
  background-color: #fed513; /* 테이블 이름 배경 색상 (COUPON LIST) */
  color: black; /* 테이블 이름 글자 색상 (COUPON LIST) */
  font-weight: bold;
  font-size: 1.5em;
  padding: 1rem;
  text-align: center;
  text-transform: uppercase;
}
.table-users {
  border: 1px solid #327a81;
  border-radius: 10px;
  box-shadow: 3px 3px 0 rgba(0, 0, 0, 0.1);
  max-width: calc(100% - 2em);
  margin: 1em auto;
  overflow: hidden;
  width: 100%;
}
table {
  width: 100%;
}
table tr:nth-child(2n) {
  background-color: white;
}
table tr:nth-child(2n+1) {
  background-color: #feed9a; /* 테이블 격줄마다 색상 */
}
table th, table td {
  color: #2b686e;
  padding: 10px;
}
table th {
  background-color: #feed9a; /* 테이블 컬럼명 색상 (Picture, Name, ...) */
  font-weight: 300;
  color: black;
  font-weight: bold;
  text-align: center;
}
table td {
  text-align: center;
  vertical-align: middle;
}
table td:last-child {
  font-size: 0.95em;
  line-height: 1.4;
}
td:nth-child(3),
td:nth-child(4) {
  min-width: 100px;
}

@media screen and (max-width: 564px) {
  table, tr, td {
    display: block;
  }
  tbody {
    display: table;
    width: 100%;
    text-align: left;
  }
  tr {
    position: relative;
  }
  tr:first-child {
    display: none;
  }
  table td {
    position: relative;
    color: #2b686e;
    padding: 5px;
  }
  td:before {
    position: relative;
    color: #2b686e;
  }
  td:nth-child(1):before {
    content: '예매코드 : ';
  }
  td:nth-child(2):before {
    content: '예매날짜 : ';
  }
  td:nth-child(3):before {
    content: '회원아이디 : ';
  }
  td:nth-child(4):before {
    content: '상영코드 : ';
  }
  td:nth-child(5):before {
    content: '쿠폰코드 : ';
  }
  td:nth-child(6):before {
    content: '예매좌석위치 : ';
  }
  td:nth-child(7):before {
    content: '관람객유형 : ';
  }
  td:nth-child(8):before {
    content: '관리자영역 : ';
  }
}/* media(564px) end */

.cbp-mc-submit-wrap {
  text-align: right;
  padding-top: 0px;
  padding-right: 20px;
  clear: both;
}
.cbp-mc-submit-wrap input.cbp-mc-submit {
  background: #40bf80; /* 쿠폰 등록 버튼 색상 */
  border: 0px solid #327a81;
  color: #fff;
  font-weight: bold;
  width: auto;
  cursor: pointer;
  text-transform: uppercase;
  display: inline-block;
  padding: 15px 30px;
  font-size: 1.1em;
  border-radius: 2px;
  letter-spacing: 1px;
}
.cbp-mc-submit-wrap input.cbp-mc-submit:hover {
  background: #2b8256; /* 쿠폰 등록 버튼 마우스 오버시 색상 */
}

.listbutton {
  background: #40bf80; /* 공개 수정 삭제 버튼 색상 */
  border: 0px solid #feed9a;
  color: #fff;
  font-weight: bold;
  width: auto;
  cursor: pointer;
  text-transform: uppercase;
  display: inline-block;
  font-size: 1.1em;
  border-radius: 2px;
  letter-spacing: 1px;
}
.listbutton:hover {
  background: #2b8256; /* 공개 수정 삭제 버튼 마우스 오버시 색상 */
}
</style>


<!-- 회원페이지일 경우 제외부분 : 회원 아이디, 관리자 영역
                       수정부분 : 상영코드로부터 정보 가져오기
                                  쿠폰코드 있으면 사용여부 OX 로 변경
                                  관람객유형 "adu you sen kid" -> "성인 청소년 경로우대 아이"로 변경
     관리자페이지일 경우 제외부분 : 예매버튼 삭제
                         수정부분 : 상영코드로부터 정보 가져오기
                                    관람객유형 "adu you sen kid" -> "성인 청소년 경로우대 아이"로 변경
 -->

<div class="cbp-mc-submit-wrap">
  <input class="cbp-mc-submit" type="button" value="예매" onclick="location.href='create.do'"/>
</div>
<div class="table-users">
   <div class="header">예매 정보 목록</div>

   <table>
      <tr>
         <th>예매 코드</th>
         <th>예매 날짜</th>
         <th>회원 아이디</th>
         <th>상영 코드</th>
         <th>쿠폰 코드</th>
         <th>예매 좌석 위치</th>
         <th>관람객 유형</th>
         <th>관리자 영역</th>
      </tr>

    <c:forEach var="dto" items="${list }">
      <tr>
        <td>${dto.bCode }</td>
        <td>${dto.wdate }</td>
        <td>${dto.uid }</td>
        <td>${dto.sCode }</td>
        <td>${dto.cCode}</td>
        <td>${dto.seat}</td>
        <td>${dto.auditType}</td>
        <td>
           <input class="listbutton" type="button" value="수정" onclick="location.href='./update.do?cCode=${dto.bCode }'"/>
           <input class="listbutton" type="button" value="삭제" onclick="location.href='./delete.do?cCode=${dto.bCode }'"/>
        </td>
      </tr>
    </c:forEach>

   </table>
</div>


<%-- 본문끝 --%>
<%@ include file="../footer.jsp"%>