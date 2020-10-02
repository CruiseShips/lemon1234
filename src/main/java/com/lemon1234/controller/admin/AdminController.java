package com.lemon1234.controller.admin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lemon1234.entity.Admin;
import com.lemon1234.entity.Register;
import com.lemon1234.service.AdminService;
import com.lemon1234.service.DocumentService;
import com.lemon1234.service.MemberService;
import com.lemon1234.service.RegisterService;
import com.lemon1234.service.WxUserInfoService;
import com.lemon1234.util.CryptographyUtil;
import com.lemon1234.util.JavaMailUtil;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private WxUserInfoService wxUserInfoService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private RegisterService registerService;
	
	@Value("${ckeditorImgFile}")
	private String ckeditorImgFile;
	
	@Value("${adminImgFile}")
	private String adminImgFile;
	
	@ResponseBody
	@RequestMapping("/login")
	public Map<String,Object> login(Admin admin, HttpServletRequest request) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		if(StringUtil.isEmpty(admin.getUserName())) {
			map.put("success", false);
		}
		if(StringUtil.isEmpty(admin.getPassword())) {
			map.put("success", false);
		} else {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(admin.getUserName(), CryptographyUtil.md5(admin.getPassword(), CryptographyUtil.SALT));
			
			try {
				subject.login(token);
				
				String userName = (String) SecurityUtils.getSubject().getPrincipal();
				Admin currentUser = adminService.findByUserName(userName);
				HttpSession session = request.getSession();
				// 设置session超时时间，1小时 (60分钟)
				session.setMaxInactiveInterval(3600);
				session.setAttribute("currentUser", currentUser);
				map.put("success", true);
				
			}catch(Exception e){
				e.printStackTrace();
				map.put("success", false);
				map.put("errorInfo", "用户名或者密码错误！");
			}
			
		}
		
		return map;
	}
	
	@RequiresPermissions(value={"登录权限正确"})
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if(request.getSession().getAttribute("currentUser") == null) {
			mav.setViewName("login");
			return mav;
		}
		
		mav.setViewName("admin/admin");
		return mav;
	}
	

	@RequiresPermissions(value = {"安全退出"})
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request)throws Exception{
		request.getSession().removeAttribute("currentUser");
		SecurityUtils.getSubject().logout();
	}
	
	@RequiresPermissions(value = {"获取Admin信息"})
	@ResponseBody
	@RequestMapping("/getInfo")
	public Map<String, Object> getInfo(HttpServletRequest request)throws Exception{
		Admin admin = adminService.findByUserName("admin");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("admin", admin);
		return result;
	}
	
	@RequiresPermissions(value={"修改头像"})
	@ResponseBody
	@RequestMapping("/uploadAdminImg")
	public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(file.getSize() < 0) {
			result.put("code", -1);
		}
		if(!file.isEmpty()) {
			Admin admin = adminService.findByUserName("admin");
			
			String oldFileName = file.getOriginalFilename();
			String fileName = StringUtil.getFileName() + oldFileName.substring(oldFileName.lastIndexOf("."));
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(adminImgFile.replaceFirst("file:", "") + fileName));
			result.put("code", 0);
			result.put("msg", "上传成功");
			
			Map<String,Object> map2=new HashMap<>();
            map2.put("title",fileName);
            map2.put("src","/adminImg/"+fileName);
            result.put("data",map2);
            
            admin.setImageName("/adminImg/"+fileName);
            adminService.update(admin);
		}
		return result;
	}
	
	@RequiresPermissions(value={"修改Admin信息"})
	@ResponseBody
	@RequestMapping("/updateAdmin")
	public Map<String, Object> updateAdmin(HttpServletRequest request, Admin admin) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Admin adminOld = adminService.findByUserName("admin");
		adminOld.setName(admin.getName());
		if(StringUtil.isNotEmpty(admin.getPassword())) {
			adminOld.setPassword(CryptographyUtil.md5(admin.getPassword(), CryptographyUtil.SALT));
		}
		adminOld.setEmail(admin.getEmail());
		adminService.update(adminOld);
		request.getSession().setAttribute("currentUser", adminOld);
		result.put("success", true);
		if(StringUtil.isNotEmpty(admin.getPassword())) {
			// 创建一个其他的线程进行发送
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					String email = admin.getEmail();
					if(StringUtil.isNotEmpty(email)) {
						JavaMailUtil.sendMail(email, admin.getPassword());
					}
					if(adminOld.getEmail() != null && !adminOld.getEmail().equals(email)) {
						JavaMailUtil.sendMail(adminOld.getEmail(), admin.getPassword());
					}
				}
			});
			
			thread.start();
		}
		return result;
	}
	
	
	@RequiresPermissions(value = {"上传图片"})
	@ResponseBody
    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file, String CKEditorFuncNum)throws Exception{
		String oldFileName = file.getOriginalFilename();
		String fileName = StringUtil.getFileName() + oldFileName.substring(oldFileName.lastIndexOf("."));
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(ckeditorImgFile.replaceFirst("file:", "") + fileName));

        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/ckeditorImg/"+ fileName + "','')");
        sb.append("</script>");

        return sb.toString();
    }
	
	@RequiresPermissions(value={"获取一些列数字"})
	@ResponseBody
	@RequestMapping("/getNum")
	public Map<String, Object> getNum() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Integer memberCount = memberService.getCount(null);
		Integer wxUserCount = wxUserInfoService.getCount(null);
		Integer docCount = documentService.getCount(null);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", Register.NOTDO);
		Integer registerCount = registerService.getCount(param);
		
		result.put("m", memberCount);
		result.put("w", wxUserCount);
		result.put("d", docCount);
		result.put("r", registerCount);
		
		result.put("success", true);
		return result;
	}
	
}
