# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table adminuser (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  mobile                    varchar(255),
  real_name                 varchar(255),
  privilege                 varchar(255),
  status                    tinyint(1) default 0,
  constraint pk_adminuser primary key (id))
;

create table advertising (
  id                        bigint auto_increment not null,
  ad_code                   varchar(255),
  ad_position               varchar(255),
  ad_script                 varchar(255),
  ad_url                    varchar(255),
  ad_image                  varchar(255),
  ad_text                   varchar(255),
  ad_type                   varchar(255),
  online                    tinyint(1) default 0,
  endate                    varchar(255),
  constraint pk_advertising primary key (id))
;

create table article (
  id                        bigint auto_increment not null,
  article_code              varchar(255),
  article_title             varchar(255),
  article_content           longtext,
  article_author            varchar(255),
  article_date              datetime,
  article_subject           varchar(255),
  article_category_code     varchar(255),
  article_auditstatus       tinyint(1) default 0,
  constraint pk_article primary key (id))
;

create table article_category (
  id                        bigint auto_increment not null,
  category_code             varchar(255),
  category_title            varchar(255),
  parent_category_code      varchar(255),
  is_channel                tinyint(1) default 0,
  create_at                 datetime,
  constraint pk_article_category primary key (id))
;

create table category (
  id                        bigint auto_increment not null,
  cg_code                   varchar(255),
  cg_name                   varchar(255),
  cg_desc                   varchar(255),
  cg_parent                 varchar(255),
  is_channel                tinyint(1) default 0,
  constraint pk_category primary key (id))
;

create table image (
  id                        bigint auto_increment not null,
  img_code                  varchar(255),
  url                       varchar(255),
  byte_code                 varchar(255),
  image_path                varchar(255),
  image_type                varchar(255),
  channel_code              varchar(255),
  category_code             varchar(255),
  article_code              varchar(255),
  constraint pk_image primary key (id))
;

create table message (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  mobile                    varchar(255),
  message                   varchar(255),
  status                    tinyint(1) default 0,
  constraint pk_message primary key (id))
;

create table news (
  id                        bigint auto_increment not null,
  news_code                 varchar(255),
  news_title                varchar(255),
  news_content              longtext,
  news_author               varchar(255),
  news_date                 datetime,
  news_subject              varchar(255),
  news_category_code        varchar(255),
  news_auditstatus          tinyint(1) default 0,
  constraint pk_news primary key (id))
;

create table news_category (
  id                        bigint auto_increment not null,
  category_code             varchar(255),
  category_title            varchar(255),
  parent_category_code      varchar(255),
  is_channel                tinyint(1) default 0,
  create_at                 datetime,
  constraint pk_news_category primary key (id))
;

create table question (
  id                        bigint auto_increment not null,
  qtitle                    varchar(255),
  qoption_a                 varchar(255),
  qoption_b                 varchar(255),
  qoption_c                 varchar(255),
  qoption_d                 varchar(255),
  qright                    varchar(255),
  constraint pk_question primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  is_admin                  tinyint(1) default 0,
  access_token              varchar(255),
  expires_in                bigint,
  login_at                  bigint,
  logout_at                 bigint,
  status                    tinyint(1) default 0,
  constraint pk_user primary key (id))
;

create table user_profile (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  name                      varchar(255),
  nickname                  varchar(255),
  cardid                    varchar(255),
  gender                    tinyint(1) default 0,
  email                     varchar(255),
  mobile                    varchar(255),
  tel                       varchar(255),
  company                   varchar(255),
  constraint pk_user_profile primary key (id))
;

create table verifycode (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  active_code               varchar(255),
  verify_email_code         varchar(255),
  verify_mobile_code        varchar(255),
  constraint pk_verifycode primary key (id))
;

create table website (
  id                        bigint auto_increment not null,
  website_code              varchar(255),
  website                   varchar(255),
  domain                    varchar(255),
  constraint pk_website primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table adminuser;

drop table advertising;

drop table article;

drop table article_category;

drop table category;

drop table image;

drop table message;

drop table news;

drop table news_category;

drop table question;

drop table user;

drop table user_profile;

drop table verifycode;

drop table website;

SET FOREIGN_KEY_CHECKS=1;

