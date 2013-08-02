package com.foxconn.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 用于绑定下拉菜单
 * @author F3220900
 *
 */
public class SelectBean {
	
	private String label;
	private String value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.toString(this);
	}
}
