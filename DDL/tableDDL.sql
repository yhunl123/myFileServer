-- auto-generated definition
create table BOARD_ITEM
(
    BOARD_ITEM_ID       BIGINT auto_increment
        primary key,
    BOARD_ITEM_TITLE    VARCHAR(255),
    BOARD_ITEM_CONTENTS TEXT,
    MEMBER_ID           BIGINT,
    BOARD_ITEM_CT_DATE  TIMESTAMP default NOW()
);

-- auto-generated definition
create table FILE
(
    FILE_ID        BIGINT auto_increment,
    FILE_NAME      VARCHAR(255) not null,
    FILE_PATH      VARCHAR(255) not null,
    BOARD_ITEM_ID  BIGINT       not null,
    FILE_ORIG_NAME VARCHAR(255) not null,
    FILE_SIZE      VARCHAR(255),
    FILE_UP_DATE   TIMESTAMP default NOW(),
    constraint FILE_PK
        primary key (FILE_ID)
);

create unique index FILE_FILE_NAME_UINDEX
    on FILE (FILE_NAME);

-- auto-generated definition
create table MEMBER
(
    MEMBER_ID      BIGINT auto_increment
        primary key,
    MEMBER_NAME    VARCHAR(255) not null,
    MEMBER_PW      VARCHAR(255) not null,
    MEMBER_CT_DATE TIMESTAMP default NOW(),
    USER_TOKEN     VARCHAR(255),
    MEMBER_AUTH    VARCHAR(1)   not null
);

create unique index MEMBER_ACCESS_TOKEN_UINDEX
    on MEMBER (USER_TOKEN);

create unique index MEMBER_MEMBERNAME_UINDEX
    on MEMBER (MEMBER_NAME);

