-- Place > script.sql

create table tblCategory (
    seq number primary key,         --PK
    name varchar2(100) not null,    --카테고리명
    marker varchar2(50) not null    --마커이미지
);

create table tblPlace (
    seq number primary key,                                 --PK
    name varchar2(100) not null,                            --장소명
    lat number not null,                                    --위도
    lng number not null,                                    --경도
    description varchar2(1000) null,                        --설명
    address varchar2(1000) not null,                        --주소
    category number not null references tblCategory(seq)    --분류
);

create sequence seqCategory;
create sequence seqPlace;


-- 카테고리
insert into tblCategory values (seqCategory.nextVal, '식당', 'restaurant.png');
insert into tblCategory values (seqCategory.nextVal, '술집', 'bar.png');
insert into tblCategory values (seqCategory.nextVal, '약국', 'pharmacy.png');
insert into tblCategory values (seqCategory.nextVal, '공원', 'forest.png');
insert into tblCategory values (seqCategory.nextVal, '마트', 'shopping.png');
insert into tblCategory values (seqCategory.nextVal, '카페', 'bakery.png');
insert into tblCategory values (seqCategory.nextVal, '주차장', 'parking.png');

commit;

select * from tblPlace order by seq;














