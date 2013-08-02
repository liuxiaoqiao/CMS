package com.foxconn.pojo;

/**
 * @author F3219058
 * @version 1.0
 * @Title 菜单/新闻目录
 * @Date 2013/7/5
 * */
public class Menu {
	private String id;
	private String pId;
	private String name;
	private String url;
	private String target;
	private String open;

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}


}
