drop table tblTodo;

create table tblTodo (
    seq number primary key,                 --번호
    todo varchar2(100) not null,            --할일
    state char(1) default 'n' not null,     --완료(y), 미완료(n)
    regdate date default sysdate not null   --등록일
);

create sequence seqTodo;

select * from tblTodo;

update tblTodo set state = 'y' where in seq(1,2);
