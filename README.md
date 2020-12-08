# lemon1234
lemon1234 官网（官网地址：[lemon1234.com](https://www.lemon1234.com "悬停显示")  ）

仅供学习，切勿商用
	
	v2.3.0
	1. HttpRequestUtil类中的获取ip地址为服务器nginx中容器的地址
	2. AOP切面同样修改了获取ip地址的方式
	2. 添加Java面试题功能模块（新增加三个实体类）
	3. 添加会员时名字、姓名不对

	v2.2.1
	1. 将所有的沙粒修改成回答
	2. 回答模块后端管理使用倒叙排列
	3. 添加新的答案时检测重复

	v2.2.0
	1. 新增加留言板模块，并且增加敏感词系统，防止用户随意提交
	2. 增加敏感词模块，用于用户提交留言板内容检测，防止提交恶意内容
	3. 更新后端当前所在地天气无法获取问题

	v2.1.2
	1. 修改密码之后发送邮件，时间太长，导致无法返回success报错

	v2.1.1
	1. OpenOS 无法添加
	2. 主页foot加载问题

	v2.1
	1. 新增加过期连接处理的问题，失效连接登记
	2. 修改主页最下方样式，之前平铺，现在改成部分内容悬浮

	v2.0 将apahce开源 模块修改成 open开源模块

	v1.0 该版本已经被替换，因为数据库设计的有问题，废弃


# DockerFile 部署文件

	##基础镜像使用
	FROM java:8
	##作者
	MAINTAINER lemon1234<18500399506@163.com>
	##定义lable
	LABEL version="v1.0" \
		  user_info="lemon1234 SpringBoot Jar" \
		  build_date="2020-09-23"
	COPY lemon1234.jar app.jar
	RUN bash -c "touch /app.jar"
	EXPOSE 90
	ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# Nginx 配置文件

    server {
        listen 80;
	    server_name www.lemon1234.com;
		rewrite ^(.*) https://$server_name$1 permanent;
	}
	server {
	    listen 443;
		server_name www.lemon1234.com;
		ssl on;
		ssl_certificate /etc/nginx/cert/4203403_www.lemon1234.com.pem;
		ssl_certificate_key /etc/nginx/cert/4203403_www.lemon1234.com.key;
		ssl_session_timeout 5m;
		ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
		ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:ECDHE:ECDH:AES:HIGH:!NULL:!aNULL:!MD5:!ADH:!RC4;
		ssl_prefer_server_ciphers on;
		location / {
	         proxy_pass   http://172.17.0.3:90;
	         proxy_set_header	X-real-ip $remote_addr;
		}
	}

# 数据库
## Table structure for t_activity
DROP TABLE IF EXISTS `t_activity`;<br>
CREATE TABLE `t_activity`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_admin
DROP TABLE IF EXISTS `t_admin`;<br>
CREATE TABLE `t_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imageName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleLevel` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_announcement
DROP TABLE IF EXISTS `t_announcement`;<br>
CREATE TABLE `t_announcement`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_document
DROP TABLE IF EXISTS `t_document`;<br>
CREATE TABLE `t_document`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `baiduUrl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_grit
DROP TABLE IF EXISTS `t_grit`;<br>
CREATE TABLE `t_grit`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_language
DROP TABLE IF EXISTS `t_language`;<br>
CREATE TABLE `t_language`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_link
DROP TABLE IF EXISTS `t_link`;<br>
CREATE TABLE `t_link`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_member
DROP TABLE IF EXISTS `t_member`; <br>
CREATE TABLE `t_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parentId` int(0) NOT NULL,
  `realName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qqNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wxNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payDt` datetime(0) NULL DEFAULT NULL,
  `from` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `parentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_menu
DROP TABLE IF EXISTS `t_menu`;<br>
CREATE TABLE `t_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `color` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_openos
DROP TABLE IF EXISTS `t_openos`;<br>
CREATE TABLE `t_openos`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `languageId` int(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `typeId` int(0) NULL DEFAULT NULL,
  `studyUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `demoUrl` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `view` int(0) NULL DEFAULT NULL,
  `share` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_openostype
DROP TABLE IF EXISTS `t_openostype`;<br>
CREATE TABLE `t_openostype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_register
DROP TABLE IF EXISTS `t_register`;<br>
CREATE TABLE `t_register`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `phoneNum` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qqNum` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `processDt` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `docId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_shout
DROP TABLE IF EXISTS `t_shout`;<br>
CREATE TABLE `t_shout`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `openId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_wxuserinfo
DROP TABLE IF EXISTS `t_wxuserinfo`;<br>
CREATE TABLE `t_wxuserinfo`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(0) NULL DEFAULT NULL,
  `openId` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `lastUpdateDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_message
DROP TABLE IF EXISTS `t_message`;<br>
CREATE TABLE `t_message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `reply` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `replyDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_badwords
DROP TABLE IF EXISTS `t_badwords`;<br>
CREATE TABLE `t_badwords`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `word` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_interviewquestions
DROP TABLE IF EXISTS ` t_interviewquestions`;
CREATE TABLE ` t_interviewquestions`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hard` int(0) NULL DEFAULT NULL,
  `qCount` int(0) NULL DEFAULT NULL,
  `star` int(0) NULL DEFAULT NULL,
  `share` int(0) NULL DEFAULT NULL,
  `view` int(0) NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_doubt
DROP TABLE IF EXISTS `t_doubt`;
CREATE TABLE `t_doubt`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `qId` int(0) NULL DEFAULT NULL,
  `doubt` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  `processDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_question
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `viewId` int(0) NULL DEFAULT NULL,
  `question` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `isChoose` int(0) NULL DEFAULT NULL,
  `option` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `explanation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `founder` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `createDt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

## Table structure for t_wxopenid
DROP TABLE IF EXISTS `t_wxopenid`;
CREATE TABLE `t_wxopenid`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `openId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `batNo` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
