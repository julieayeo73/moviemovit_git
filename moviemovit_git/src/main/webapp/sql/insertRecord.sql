-- ------------------------- insert record
-- userTable ���ڵ� ����
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member1', 'me1234!', '����', 'W', '19940426', 'cheerup_km@naver.com', '01012345678',now(), 'ADMIN');
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member2', 's1234!', '�����', 'W', '19911020', 'sh@naver.com', '01012345678',now(), 'SELLER');
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member3', 's1234!', '�����', 'W', '19911020', 'sh@naver.com', '01012345678',now(), 'VIP');
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member4', 's1234!', '�����', 'W', '19911020', 'sh@naver.com', '01012345678',now(), 'GOLD');
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member5', 's1234!', '�����', 'W', '19911020', 'sh@naver.com', '01012345678',now(), 'SILVER');
INSERT INTO userTable(uid,upw,uname,gender,birth,email,phone,wdate,grade)
VALUES('member6', 's1234!', '�����', 'W', '19911020', 'sh@naver.com', '01012345678',now(), 'F');

-- movieTable ���ڵ� ����
INSERT INTO movieTable(mCode, poster, mName, genre, screen, age, s_e, country, director, actor, DD, runningTime, s_date, e_date)
VALUES ((SELECT ifnull(MAX(mCode),0)+1 FROM movieTable as movie), 'moving/images/poster02.jpg', '�Ű��Բ�', 'comedy', 'ing', '0', '���¿� ���� ��ȭ', 'Korea', '7,8', '1,2,3', '3D', '140', '2018-06-15', '2018-06-30');

-- peopleTable ���ڵ� ����
insert into peopleTable(peoCode, peoName, country, peoBirth, gender, peoPic)
values((SELECT IFNULL(MAX(peocode),0)+1 FROM peopleTable as peo),'������','korea','1988', 'M', 'moving/images/peo0001.jpg'); 

-- cinemaTable ���ڵ� ����
-- ���� 2018/08/31 ���� 
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('C001','CGV','����','1544-1122','SEO','����� ���ı� ��η� 66','�������̺������','37.477633','127.1249435','img/cWang.jpg',now(),'��ð� : 8:00~2:00','http://www.cgv.co.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('L001','LOTTE','����Ÿ����','1544-8855','SEO','����� ���ı� �ø��ȷ� 300','�Ե������ 5F','37.513689','127.1044541','img/lWord.jpg',now(),'��ð� : 8:00~2:00','http://www.lottecinema.co.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('M001','MEGABOX','������ũ�Ϻ��','1544-0070','SEO','����� ���ı� ���Ĵ�� 111','�Ϻ��Ǫ������','37.4776339','127.124934','img/mSongpa.jpg',now(),'��ð� : 8:00~2:00','http://www.megabox.co.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('C002','CGV','������','1544-1122','GGD','��⵵ �����ν� ��ȭ�� 525','�����ι��ڿ���','37.7334609','127.6805888','img/default.png',now(),'��ð� : 8:00~2:00','http://www.cgv.co.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('D001','INDEP','��ȭ�����־�','032-427-6777','ICH','��õ ����Ȧ�� ����Ȧ���','716','37.461241','126.680588','img/default.png',now(),'��ð� : 8:00~2:00','http://www.indiespace.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('C003','CGV','����','1544-1122','GWD','������ ���ֽ�','������ 171','37.3448535','127.9308469','img/default.png',now(),'��ð� : 8:00~2:00','http://www.cgv.co.kr');
insert into cinemaTable(cineCode,brandName,cineName,tel,addr1,addr2,addr3,wido,kyengdo,logoImg,wdate,etc,home)
values('M002','MEGABOX','����','1544-0070','JJD','���ֵ� ���ֽ� �߾ӷ�14��','18','33.5116003','126.5226707','img/default.png',now(),'��ð� : 8:00~2:00','http://www.megabox.co.kr');

-- roomTable ���ڵ� ����
insert into roomTable(roomCode,cineCode,roomName,seatCnt,DD,seatImg)
values('C001_1','C001',1,50,'2D, 3D','moving/images/seat1.jpg');
insert into roomTable(roomCode,cineCode,roomName,seatCnt,DD,seatImg)
values('C001_2','C001',2,100,'2D','moving/images/seat2.jpg');
insert into roomTable(roomCode,cineCode,roomName,seatCnt,DD,seatImg)
values('C001_3','C001',3,150,'imax','moving/images/seat3.jpg');
insert into roomTable(roomCode,cineCode,roomName,seatCnt,DD,seatImg,etc)
values('L001_4','L001',4,100,'4D','moving/images/seat4.jpg','���座�� �Ű����');

-- reviewStarTable ���ڵ� ����
insert into reviewStarTable(no,cineCode,uid,pixel,sound,clean,service,seat,around,snack,trans,wdate)
values((SELECT IFNULL(MAX(no),0)+1 FROM reviewStarTable as rst),'C001','member1',5,3,1,2,3,3,3,5,now());
insert into reviewStarTable(no,cineCode,uid,pixel,sound,service,seat,around,snack,trans,wdate)
values((SELECT IFNULL(MAX(no),0)+1 FROM reviewStarTable as rst),'L001','member2',4,2,2,3,2,2,5,now());

-- reviewTable ���ڵ� ����
insert into reviewTable(no,cineCode,uid,s_e,wdate,ip,heart)
values((SELECT IFNULL(MAX(no),0)+1 FROM reviewTable as rt),'C001','member1','�¼��� ���������ϰ� �о ����մϴ�',now(),'172.168.0.20',10);
insert into reviewTable(no,cineCode,uid,s_e,wdate,ip,heart)
values((SELECT IFNULL(MAX(no),0)+1 FROM reviewTable as rt),'L001','member2','�ڸ��տ� ���� �������� ������ �ִ��󱸿�',now(),'152.168.0.18',2);
insert into reviewTable(no,cineCode,uid,s_e,wdate,ip,heart)
values((SELECT IFNULL(MAX(no),0)+1 FROM reviewTable as rt),'M001','member3','�� Ű�� ���� ���ε��� �ٸ� ���� �ֱ� ������',now(),'128.168.0.20',0);

-- noticeTable ���ڵ� ����
insert into  noticeTable(noticeno,title,s_e,uid,wdate,open)
values((SELECT ifnull(MAX(noticeno),0)+1 FROM noticeTable as notice),'����Ʈ�� ó�� �̿��Ͻô� �е�',' ','admin1',now(),'Y');

-- QnATable ���ڵ� ����
insert into  QnATable(qCode,qType,uid,title,content,wdate,ip,groupNo,indent)
values((SELECT ifnull(MAX(qCode),0)+1 FROM QnATable as q),'q1','member1','���ž�� �ؿ�','�����ϴ� ����� �𸣰ڽ��ϴ�. �Ф�',now(),'172.168.0.20',0,0);

-- starTable
insert into starTable(sCode, mCode, uid, star, comment, wdate)
values((SELECT IFNULL(MAX(sCode),0)+1 FROM starTable as star), '2', 'member1', '5', '�� �λ����� �� ����Ʈ ��ȭ', now());

-- couponTable ���ڵ� ����
insert into couponTable(cCode,uid,startDate,expDate)
values((SELECT IFNULL(MAX(cCode),0)+1 FROM couponTable as coupon), 'member1', '2018-08-10', '2019-08-10');

-- screenTable ���ڵ� ����
insert into screenTable(sCode,roomCode,sdate,stime,mCode)
values((SELECT IFNULL(MAX(sCode),0)+1 FROM screenTable as screen), 'C001_1', '2018-08-20', '1030', '1');
insert into screenTable(sCode,roomCode,sdate,stime,mCode)
values((SELECT IFNULL(MAX(sCode),0)+1 FROM screenTable as screen), 'C001_2', '2018-08-20', '1030', '1');

-- bookTable ���ڵ� ����
insert into bookTable(bCode,wdate,uid,sCode,seat,auditType,cCode)
values('2018080711330601',now(),'member1',1,'A1,A2,A3,A4','adu,you,sen,kid',1);

insert into bookTable(bCode,wdate,uid,sCode,seat,auditType,cCode)
values('2018080711330602',now(),'member2',2,'D10,D11','you,sen',8);


