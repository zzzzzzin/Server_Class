drop table tblUser cascade  constraints;

create table tblUser (
    id varchar2(30) primary key,    --아이디
    pw varchar2(30) not null,       --암호
    name varchar2(30) not null,     --이름
    lv number(1) not null           --등급 > 일반(1), 관리자(2)
);

insert into tblUser values ('hong', '1111', '홍길동', 1);
insert into tblUser values ('dog', '1111', '강아지', 1);
insert into tblUser values ('cat', '1111', '고양이', 2);

commit;


