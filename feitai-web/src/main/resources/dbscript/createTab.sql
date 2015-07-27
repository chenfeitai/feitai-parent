-----------------------------------
-- 表名称: 用户表 FT_USER
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_USER;
CREATE TABLE FT_USER(
  	USER_ID VARCHAR(20) NOT NULL COMMENT '用户ID',
  	USER_NAME VARCHAR(32) NOT NULL COMMENT '用户名,索引',
  	USER_PWD VARCHAR(32) NOT NULL COMMENT '密码',
 	USER_CREDITS INT COMMENT '积分', 
 	USER_TYPE VARCHAR(2) NOT NULL COMMENT '用户类型',
 	LOCKED VARCHAR(2) NOT NULL COMMENT '锁定标志',
 	LAST_VISIT_DT DATETIME COMMENT '最后登录时间',
 	LAST_IP VARCHAR(23) COMMENT '最后登录IP',
  	PRIMARY KEY (USER_ID)
)ENGINE=InnoDB;
ALTER TABLE FT_USER ADD INDEX IDX_TUSER_USER_NAME(USER_NAME); 


-----------------------------------
-- 表名称: 登录日志表 FT_LOGIN_LOG
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_LOGIN_LOG;
CREATE TABLE FT_LOGIN_LOG(
  	LOGIN_LOG_ID VARCHAR(20) NOT NULL COMMENT '登录日志ID',
  	USER_ID INT NOT NULL COMMENT '登录用户ID',
  	IP VARCHAR(32) NOT NULL COMMENT '登录IP',
 	LOGIN_DT DATETIME NOT NULL COMMENT '登录时间',
  	PRIMARY KEY (LOGIN_LOG_ID)
)ENGINE=InnoDB;

-----------------------------------
-- 表名称: 论坛版块表 FT_BOARD
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_BOARD;
CREATE TABLE FT_BOARD(
  	BOARD_ID VARCHAR(20) NOT NULL COMMENT '论坛版块ID',
  	BOARD_NAME VARCHAR(150) NOT NULL COMMENT '论坛版块名',
  	BOARD_DESC VARCHAR(255) COMMENT '论坛版块描述',
 	TOPIC_NUM INT NOT NULL COMMENT '帖子数目',
  	PRIMARY KEY (BOARD_ID)
)ENGINE=InnoDB;

-----------------------------------
-- 表名称: 用户管理版块关联表 FT_BOARD_MANAGER
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_BOARD_MANAGER;
CREATE TABLE FT_BOARD_MANAGER(
  	BOARD_ID VARCHAR(20) NOT NULL  COMMENT '论坛版块ID',
	USER_ID VARCHAR(20) NOT NULL  COMMENT '用户ID',
  	PRIMARY KEY (BOARD_ID,USER_ID)
)ENGINE=InnoDB;

-----------------------------------
-- 表名称: 论坛话题表 FT_TOPIC
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_TOPIC;
CREATE TABLE FT_TOPIC(
    TOPIC_ID VARCHAR(20) NOT NULL  COMMENT '帖子ID',
  	BOARD_ID VARCHAR(20) NOT NULL  COMMENT '所属版块ID',
  	TOPIC_TITLE VARCHAR(100) NOT NULL COMMENT '帖子标题,索引',
  	USER_ID VARCHAR(20) NOT NULL  COMMENT '发表用户ID,索引',
  	CREATE_TIME DATETIME NOT NULL COMMENT '发表时间',
  	LAST_POST_DT DATETIME NOT NULL COMMENT '最后回复时间',
  	TOPIC_VIEWS INT NOT NULL default 0 COMMENT '浏览数',
  	TOPIC_REPLIES INT NOT NULL default 0 COMMENT '回复数',
  	DIGEST VARCHAR(2) NOT NULL default 0 COMMENT '是否加精',
  	PRIMARY KEY (TOPIC_ID)
)ENGINE=InnoDB;
ALTER TABLE FT_TOPIC ADD INDEX IDX_TTOPIC_TOPIC_TITLE(TOPIC_TITLE);
ALTER TABLE FT_TOPIC ADD INDEX IDX_TTOPIC_USER_ID(USER_ID);
ALTER TABLE FT_TOPIC ADD INDEX IDXS_TTOPIC(TOPIC_TITLE,USER_ID);
-----------------------------------
-- 表名称: 论坛帖子表 FT_POST
-- 时间戳记: 2015-07-17
-----------------------------------
DROP TABLE IF EXISTS FT_POST;
CREATE TABLE FT_POST(
  	POST_ID VARCHAR(20) NOT NULL COMMENT '帖子ID',
  	BOARD_ID VARCHAR(20) NOT NULL COMMENT '版块ID',
  	TOPIC_ID VARCHAR(20) NOT NULL  COMMENT '话题ID,索引',
  	USER_ID VARCHAR(20) NOT NULL  COMMENT '发表用户ID',
  	POST_TYPE VARCHAR(2) NOT NULL COMMENT '帖类型,区分主贴和回复贴',
  	POST_TITLE VARCHAR(50) COMMENT '帖子标题',
  	POST_TEXT VARCHAR(1024) NOT NULL COMMENT '帖子内容',
  	CREATE_TIME DATETIME NOT NULL COMMENT '创建时间',
  	PRIMARY KEY (POST_ID)
)ENGINE=InnoDB;
ALTER TABLE FT_POST ADD INDEX IDX_TPOST_TOPIC_ID(TOPIC_ID);