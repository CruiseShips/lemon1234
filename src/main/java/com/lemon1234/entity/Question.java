package com.lemon1234.entity;

import java.util.Date;

/**
 * 面试问题表
 */
public class Question {

	private int id;
	// 属于那个面试题的问题
	private int viewId;
	// 问题
	private String question;
	// 1是选择题 2是判断题 3是简答题 和 Sql问题
	private int isChoose;
	// 选择题选项
	private String option;
	// 答案
	private String answer;
	// 解释
	private String explanation;
	
	// 创建问题的人(使用ip地址，默认admin)
	private String founder;
	// 其他人也可以添加问题（默认审核不通过，admin除外）0 未通过 1通过
	private int status;
	// 创建日期
	private Date createDt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getViewId() {
		return viewId;
	}
	public void setViewId(int viewId) {
		this.viewId = viewId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getIsChoose() {
		return isChoose;
	}
	public void setIsChoose(int isChoose) {
		this.isChoose = isChoose;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getFounder() {
		return founder;
	}
	public void setFounder(String founder) {
		this.founder = founder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
}
