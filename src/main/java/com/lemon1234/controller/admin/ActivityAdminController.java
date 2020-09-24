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

import com.lemon1234.entity.Activity;
import com.lemon1234.service.ActivityService;
import com.lemon1234.start.Lemon1234ServerStart;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/admin/activity")
public class ActivityAdminController {

	@Autowired
	private ActivityService activityService;
	@Value("${activityImgFile}")
	private String activityImgFile;
	@Autowired
	private Lemon1234ServerStart start;
	
	@RequiresPermissions(value = {"查看所有活动"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		List<Activity> activityList = activityService.list(param);
		Integer count = activityService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", activityList);
		return result;
	}
	
	@RequiresPermissions(value = {"查看活动"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(id != 0) {
			Activity activity = activityService.getdetail(id);
			result.put("success", true);
			result.put("activity", activity);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"添加活动"})
	@ResponseBody
	@RequestMapping("/addActivity")
	public Map<String, Object> addActivity(Activity activity) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		activityService.addActivity(activity);
		result.put("success", true);
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"更新活动"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(Activity activity) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(activity != null && activity.getId() != 0) {
			activityService.update(activity);
			result.put("success", true);
			start.initData();
		} else {
			result.put("success", false);
			result.put("errorInfo", "服务器异常，请从新修改");
		}
		
		return result;
	}
	
	@RequiresPermissions(value = {"删除活动"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		activityService.delete(id);
		result.put("success", true);
		start.initData();
		return result;
	}
	
	@RequiresPermissions(value = {"上传活动图片"})
	@ResponseBody
    @RequestMapping("/uploadActivity")
	public Map<String, Object> uploadActivity(MultipartFile file, Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(file.getSize() < 0) {
			result.put("code", -1);
		}
		if(!file.isEmpty()) {
			String oldFileName = file.getOriginalFilename();
			String fileName = StringUtil.getFileName() + oldFileName.substring(oldFileName.lastIndexOf("."));
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(activityImgFile.replaceFirst("file:", "") + fileName));
			result.put("code", 0);
			result.put("msg", "上传成功");
			
			String img = "/activityImg/"+fileName;
			Map<String,Object> map2=new HashMap<>();
            map2.put("title",fileName);
            map2.put("src", img);
            result.put("data",map2);
            
            Activity activity = new Activity();
            activity.setId(id);
            activity.setImg(img);
            activityService.update(activity);
            start.initData();
		}
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
