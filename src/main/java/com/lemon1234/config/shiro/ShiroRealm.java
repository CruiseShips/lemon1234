package com.lemon1234.config.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lemon1234.entity.Admin;
import com.lemon1234.service.AdminService;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminService adminService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		Admin admin = adminService.findByUserName(userName);
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Set<String> roles=new HashSet<String>();
		int adminRole = admin.getRoleLevel();
		if(adminRole == 1){
			roles.add(String.valueOf(adminRole));
			info.addStringPermission("登录权限正确");
			info.addStringPermission("安全退出");
			info.addStringPermission("获取Admin信息");
			info.addStringPermission("修改头像");
			info.addStringPermission("修改Admin信息");
			info.addStringPermission("获取男女比例");
			info.addStringPermission("按照每天获取前5的记录");
			info.addStringPermission("获取一些列数字");
			
			info.addStringPermission("查看所有open开源框架");
			info.addStringPermission("查看open开源框架信息");
			info.addStringPermission("修改openImg");
			info.addStringPermission("删除open");
			info.addStringPermission("添加open");
			info.addStringPermission("修改open");
			
			info.addStringPermission("查看所有opentype");
			info.addStringPermission("删除opentype");
			info.addStringPermission("通过id获取openType");
			info.addStringPermission("修改openType");
			info.addStringPermission("添加openType");
			
			info.addStringPermission("查看所有language");
			info.addStringPermission("添加Language");
			info.addStringPermission("删除Language");
			info.addStringPermission("获取Language");
			info.addStringPermission("修改Language");
			
			info.addStringPermission("查看所有文档");
			info.addStringPermission("查看单个文档");
			info.addStringPermission("修改Document");
			info.addStringPermission("添加Document");
			info.addStringPermission("删除Document");
			
			info.addStringPermission("查看所有菜单");
			info.addStringPermission("添加菜单");
			info.addStringPermission("修改菜单");
			info.addStringPermission("删除菜单");
			info.addStringPermission("获取菜单");
			
			info.addStringPermission("查看所有链接");
			info.addStringPermission("查看链接");
			info.addStringPermission("增加链接");
			info.addStringPermission("更新链接");
			info.addStringPermission("删除链接");
			
			info.addStringPermission("查看所有公告");
			info.addStringPermission("查看公告");
			info.addStringPermission("添加公告");
			info.addStringPermission("更新公告");
			info.addStringPermission("删除公告");
			
			info.addStringPermission("查看所有会员");
			info.addStringPermission("修改会员");
			info.addStringPermission("添加会员");
			
			info.addStringPermission("查看所有活动");
			info.addStringPermission("查看活动");
			info.addStringPermission("添加活动");
			info.addStringPermission("更新活动");
			info.addStringPermission("删除活动");
			info.addStringPermission("上传活动图片");
			
			info.addStringPermission("查看所有失效连接");
			info.addStringPermission("删除失效连接");
			info.addStringPermission("修改失效连接状态");
			
			info.addStringPermission("上传图片");
			info.addStringPermission("查看所有小程序用户");
			info.addStringPermission("查看所有小程序qa");
			info.addStringPermission("修改Grit状态"); // status
			info.addStringPermission("修改Grit名称"); // name
			info.addStringPermission("删除Grit");
			info.addStringPermission("添加Grit");
			
			info.addStringPermission("查看所有留言");
			info.addStringPermission("删除留言");
			info.addStringPermission("获取留言");
			info.addStringPermission("修改留言");
			
			info.addStringPermission("查看所有敏感词汇");
			info.addStringPermission("删除敏感词汇");
			info.addStringPermission("修改敏感词汇");
			info.addStringPermission("获取敏感词汇");
			info.addStringPermission("添加敏感词汇");
		} else {
			return null;
		}
		info.setRoles(roles);
		return info;
	}

	/**
	 * 权限认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		Admin admin = adminService.findByUserName(userName);
		if(admin != null) {
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(admin.getUserName(), admin.getPassword(), String.valueOf(admin.getRoleLevel()));
			return authcInfo;
		}else{
			return null;			
		}
	}

}
