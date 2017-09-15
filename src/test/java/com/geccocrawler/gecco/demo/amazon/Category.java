package com.geccocrawler.gecco.demo.amazon;

import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HrefBean;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

public class Category implements HtmlBean {

	private static final long serialVersionUID = 3018760488621382659L;

	@Text
	@HtmlField(cssPath="div > div:nth-child(2) > div > a > span")
	private String parentName;
	
	@HtmlField(cssPath="div div ul li a")
	private List<HrefBean> categorys;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<HrefBean> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<HrefBean> categorys) {
		this.categorys = categorys;
	}
	
}
