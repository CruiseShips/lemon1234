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

import com.lemon1234.entity.OpenOS;
import com.lemon1234.service.OpenOSService;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/admin/openOS")
public class OpenOSAdminController {

	@Autowired
	private OpenOSService openOSService;
	@Value("${openOSImgFile}")
	private String openOSImgFile;
	
	@RequiresPermissions(value={"查看所有open开源框架"})
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
		List<OpenOS> openOSList = openOSService.getlist(param);
		Integer count = openOSService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", openOSList);
		return result;
	}
	
	@RequiresPermissions(value={"查看open开源框架信息"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(@RequestParam(value = "id", required = false)Integer id) throws Exception {
		OpenOS openOS = openOSService.getById(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("openOS", openOS);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"修改openImg"})
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
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(openOSImgFile.replaceFirst("file:", "") + fileName));
			result.put("code", 0);
			result.put("msg", "上传成功");
			
			Map<String,Object> map2=new HashMap<>();
            map2.put("title",fileName);
            map2.put("src","/openOSImg/"+fileName);
            result.put("data",map2);
            
            OpenOS openOS = openOSService.getById(id);
            openOS.setImg("/openOSImg/"+fileName);
            openOSService.updateOpenOS(openOS);
		}
		return result;
	}
	
	@RequiresPermissions(value = {"删除open"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		openOSService.deleteOpenOS(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"添加open"})
	@ResponseBody
	@RequestMapping("/addOpenOS")
	public Map<String, Object> addOpenOS(OpenOS openOS) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		openOS.setImg("default.jpg");
		openOSService.addOpenOS(openOS);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value = {"修改open"})
	@ResponseBody
	@RequestMapping("/updateOpenOS")
	public Map<String, Object> updateOpenOS(OpenOS openOS) throws Exception {
		if(openOS.getId() != 0) {
			openOSService.updateOpenOS(openOS);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
