package com.java.lemon.model.entity;

/**
 * 学习阶段
 * 第一阶段、第二阶段。。
 */
public class StudyRoadMap {
	
	// 时间轴
	// https://www.layui.com/demo/timeline.html
	
	/**
	 * <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>简约时间线：大事记</legend>
</fieldset> 
<ul class="layui-timeline">
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis"></i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">2018年，layui 5.0 发布。并发展成为中国最受欢迎的前端UI框架（期望）</div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis"></i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">2017年，layui 里程碑版本 2.0 发布</div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis"></i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">2016年，layui 首个版本发布</div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-timeline-axis"></i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">2015年，layui 孵化</div>
    </div>
  </li>
  <li class="layui-timeline-item">
    <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>
    <div class="layui-timeline-content layui-text">
      <div class="layui-timeline-title">更久前，轮子时代。维护几个独立组件：layer等</div>
    </div>
  </li>
</ul>
	 */

	private int id;
	
	// 阶段名称
	private String name;
	
	// 学习目标
	private String aims;
	
	// 排序
	private int sort;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAims() {
		return aims;
	}

	public void setAims(String aims) {
		this.aims = aims;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
