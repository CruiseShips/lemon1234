DROP TABLE IF EXISTS t_admin;							-- 管理员 表
DROP TABLE IF EXISTS t_project;							-- 项目管理 表
DROP TABLE IF EXISTS t_technology;						-- 项目技术表 表
DROP TABLE IF EXISTS t_project_technology;				-- 项目&技术关联表 

CREATE TABLE t_admin {
	id		int			
	private int id;
	
	// 管理员名称
	@Column(length=100)
	private String name;
	
	// 用户名
	@Column(length=50)
	private String userName;
	
	// 密码
	@Column(length=100)
	private String password;
	
	// 邮箱
	@Column(length=100)
	private String email;
	
	// 头像图片
	@Column(length=100)
	private String imageName;
	
	// 创建日期
	@Column(length = 0)
	private Date createDt;
	
	@Column(length = 0)
	private int roleLevel;
}
