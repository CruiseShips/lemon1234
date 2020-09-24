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

import com.lemon1234.entity.Document;
import com.lemon1234.service.DocumentService;
import com.lemon1234.util.StringUtil;

@Controller
@RequestMapping("/admin/document")
public class DocumentAdminController {

	@Autowired
	private DocumentService documentService;
	@Value("${documentImgFile}")
	private String documentImgFile;
	
	@RequiresPermissions(value = {"查看所有文档"})
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(
			@RequestParam(value = "page", required = false)Integer page,
			@RequestParam(value = "limit", required = false)Integer limit,
			@RequestParam(value = "name", required = false)String name) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("page", this.getPageStart(page, limit));
		param.put("limit", limit);
		if(name != null) {
			param.put("name", name);
		}
		List<Document> documents = documentService.getlist(param);
		Integer count = documentService.getCount(param);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("count", count);
		result.put("data", documents);
		return result;
	}
	
	@RequiresPermissions(value = {"查看单个文档"})
	@ResponseBody
	@RequestMapping("/getdetail")
	public Map<String, Object> getdetail(Integer id) throws Exception {
		Document document = documentService.getById(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("document", document);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"修改Document"})
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
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(documentImgFile.replaceFirst("file:", "") + fileName));
			result.put("code", 0);
			result.put("msg", "上传成功");
			
			Map<String,Object> map2=new HashMap<>();
            map2.put("title",fileName);
            map2.put("src","/documentImg/"+fileName);
            result.put("data",map2);
            
            Document document = documentService.getById(id);
            document.setImg("/documentImg/"+fileName);
            documentService.update(document);
		}
		return result;
	}
	
	@RequiresPermissions(value={"添加Document"})
	@ResponseBody
	@RequestMapping("/addDocument")
	public Map<String, Object> addDocument(Document document) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		documentService.addDocument(document);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"删除Document"})
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		documentService.delete(id);
		result.put("success", true);
		return result;
	}
	
	@RequiresPermissions(value={"修改Document"})
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> updateDocument(Document document) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		if(document != null && document.getId() != 0) {
			documentService.update(document);
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		
		return result;
	}
	
	private int getPageStart(Integer page, Integer limit) {
		return (page-1) * limit;
	}
}
