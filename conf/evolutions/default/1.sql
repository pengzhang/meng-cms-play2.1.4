# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table adminuser (
  id                        bigint auto_increment not null,
  admin_code                varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  mobile                    varchar(255),
  real_name                 varchar(255),
  privilege                 varchar(255),
  status                    tinyint(1) default 0,
  login_time                datetime,
  logout_time               datetime,
  create_date               datetime,
  constraint pk_adminuser primary key (id))
;

create table admin_website (
  id                        bigint auto_increment not null,
  aw_code                   varchar(255),
  admin_code                varchar(255),
  web_site_code             varchar(255),
  constraint pk_admin_website primary key (id))
;

create table advertising (
  id                        bigint auto_increment not null,
  ad_code                   varchar(255),
  ad_name                   varchar(255),
  ad_position               varchar(255),
  ad_script                 varchar(255),
  ad_url                    varchar(255),
  ad_image                  varchar(255),
  ad_text                   varchar(255),
  ad_type                   varchar(255),
  online                    tinyint(1) default 0,
  web_site_code             varchar(255),
  endate                    date,
  create_date               datetime,
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
  web_site_code             varchar(255),
  is_channel                tinyint(1) default 0,
  create_at                 datetime,
  constraint pk_article_category primary key (id))
;

create table download (
  id                        bigint auto_increment not null,
  soft_code                 varchar(255),
  soft_name                 varchar(255),
  soft_desc                 longtext,
  soft_size                 varchar(255),
  soft_lang                 varchar(255),
  soft_sys                  varchar(255),
  soft_ver                  varchar(255),
  soft_type                 varchar(255),
  soft_url                  varchar(255),
  soft_status               tinyint(1) default 0,
  down_category_code        varchar(255),
  update_date               date,
  create_date               datetime,
  constraint pk_download primary key (id))
;

create table download_category (
  id                        bigint auto_increment not null,
  dc_code                   varchar(255),
  dc_name                   varchar(255),
  dc_desc                   varchar(255),
  parent_dc_code            varchar(255),
  web_site_code             varchar(255),
  create_date               datetime,
  constraint pk_download_category primary key (id))
;

create table download_url (
  id                        bigint auto_increment not null,
  soft_code                 varchar(255),
  dl_url_code               varchar(255),
  soft_down_site            varchar(255),
  soft_url                  varchar(255),
  constraint pk_download_url primary key (id))
;

create table exam (
  id                        bigint auto_increment not null,
  e_code                    varchar(255),
  exam_name                 varchar(255),
  exam_desc                 varchar(255),
  web_site_code             varchar(255),
  create_date               datetime,
  constraint pk_exam primary key (id))
;

create table faq (
  id                        bigint auto_increment not null,
  code                      varchar(255),
  question                  varchar(255),
  answer                    longtext,
  web_site_code             varchar(255),
  status                    tinyint(1) default 0,
  create_date               datetime,
  constraint pk_faq primary key (id))
;

create table image (
  id                        bigint auto_increment not null,
  img_code                  varchar(255),
  image_name                varchar(255),
  image_desc                varchar(255),
  image_url                 varchar(255),
  image_path                varchar(255),
  image_type                varchar(255),
  category_code             varchar(255),
  article_code              varchar(255),
  image_status              tinyint(1) default 0,
  create_date               datetime,
  constraint pk_image primary key (id))
;

create table image_category (
  id                        bigint auto_increment not null,
  ic_code                   varchar(255),
  category_name             varchar(255),
  category_desc             varchar(255),
  web_site_code             varchar(255),
  parent_category_code      varchar(255),
  is_channel                tinyint(1) default 0,
  create_at                 datetime,
  constraint pk_image_category primary key (id))
;

create table message (
  id                        bigint auto_increment not null,
  msg_code                  varchar(255),
  name                      varchar(255),
  email                     varchar(255),
  mobile                    varchar(255),
  message                   varchar(255),
  web_site_code             varchar(255),
  status                    tinyint(1) default 0,
  create_date               datetime,
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
  web_site_code             varchar(255),
  parent_category_code      varchar(255),
  is_channel                tinyint(1) default 0,
  create_at                 datetime,
  constraint pk_news_category primary key (id))
;

create table question (
  id                        bigint auto_increment not null,
  q_code                    varchar(255),
  qtitle                    varchar(255),
  qoption_a                 varchar(255),
  qoption_b                 varchar(255),
  qoption_c                 varchar(255),
  qoption_d                 varchar(255),
  qright                    varchar(255),
  exam_code                 varchar(255),
  status                    tinyint(1) default 0,
  create_date               datetime,
  constraint pk_question primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  password                  varchar(255),
  is_admin                  tinyint(1) default 0,
  access_token              varchar(255),
  expires_in                bigint,
  login_time                datetime,
  logout_time               datetime,
  create_date               datetime,
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
  web_code                  varchar(255),
  web_name                  varchar(255),
  web_url                   varchar(255),
  domain                    varchar(255),
  web_desc                  varchar(255),
  create_date               datetime,
  constraint pk_website primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table adminuser;

drop table admin_website;

drop table advertising;

drop table article;

drop table article_category;

drop table download;

drop table download_category;

drop table download_url;

drop table exam;

drop table faq;

drop table image;

drop table image_category;

drop table message;

drop table news;

drop table news_category;

drop table question;

drop table user;

drop table user_profile;

drop table verifycode;

drop table website;

SET FOREIGN_KEY_CHECKS=1;

