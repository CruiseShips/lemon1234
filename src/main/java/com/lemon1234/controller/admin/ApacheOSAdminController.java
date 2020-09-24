package com.lemon1234.controller.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lemon1234.entity.ApacheOS;
import com.lemon1234.service.ApacheOSService;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/admin/apacheOS")
public class ApacheOSAdminController {

	@Autowired
	private ApacheOSService apacheOSService;
	@Value("${apacheOSImgFile}")
	private String apacheOSImgFile;
	
	@RequiresPermissions(value={"查看所有apache开源框架"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> getlist(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit,
			@RequestParam(value = "name", required = false)String name
									  ) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		if(name != null) {
			param.put("name", name);
		}
		List<ApacheOS> apacheOSList = apacheOSService.getlist(param);
		Integer count = apacheOSService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", apacheOSList);
		return result;
	}
	
	@RequiresPermissions(value={"查看apache开源框架信息"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(@RequestParam(value = "id", required = false)Integer id) throws Exception {
		ApacheOS apacheOS = apacheOSService.getById(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("apacheOS", apacheOS);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"修改Apacheimg"})
	@ResponseBody
	@RequestMapping("/uploadImage")
	public Map<String, Object> uploadImage(MultipartFile file, Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(file.getSize() < 0) {
			result.put("code", -1);
		}
		if(!file.isEmpty()) {
			String oldFileName = file.getOriginalFilename();
			String fileName = StringUtil.getFileName() + oldFileName.substring(oldFileName.lastIndexOf("."));
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(apacheOSImgFile.replaceFirst("file:", "") + fileName));
			result.put("code", 0);
			result.put("msg", "上传成功");
			
			Map<String,Object> map2=new HashMap<>();
            map2.put("title",fileName);
            map2.put("src","/apacheOSImg/"+fileName);
            result.put("data",map2);
            
            ApacheOS apacheOS = apacheOSService.getById(id);
            apacheOS.setImg("/apacheOSImg/"+fileName);
            apacheOSService.updateApacheOS(apacheOS);
		}
		return result;
	}
	
	@RequiresPermissions(value = {"删除apache"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		apacheOSService.deleteApacheOS(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"添加apache"})
	@ResponseBody
	@RequestMapping("/addApacheOS")
	public Map<String, Object> addApacheOS(ApacheOS apacheOS) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		apacheOS.setImg("default.jpg");
		apacheOSService.addApacheOS(apacheOS);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改apache"})
	@ResponseBody
	@RequestMapping("/updateApacheOS")
	public Map<String, Object> updateApacheOS(ApacheOS apacheOS) throws Exception {
		if(apacheOS.getId() != 0) {
			apacheOSService.updateApacheOS(apacheOS);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
