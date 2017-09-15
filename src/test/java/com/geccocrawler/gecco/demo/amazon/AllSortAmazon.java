package com.geccocrawler.gecco.demo.amazon;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

@Gecco(matchUrl="https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords={keywords}&page={page}", pipelines={"consolePipeline", "allSortPipeline3"})
public class AllSortAmazon implements HtmlBean {

	private static final long serialVersionUID = 665662335318691818L;
	
	@Request
	private HttpRequest request;


	@RequestParameter("keywords")
	private String keywords;//url中的{user}值

	@RequestParameter("page")
	private String page;//url中的{project}值

	@Text
	@HtmlField(cssPath=".s-item-container div:nth-child(2) div a span")
	private List<String> brand;//抽取页面中的fork

	public List<String> getProudut_name() {
		return proudut_name;
	}

	public void setProudut_name(List<String> proudut_name) {
		this.proudut_name = proudut_name;
	}

	@Text
	@HtmlField(cssPath=".s-item-container div:nth-child(3) div a h2")
	private List<String> proudut_name;//抽取页面中的fork

	//家用电器
//	@HtmlField(cssPath=".cg-main > div:nth-child(2) ")
	private List<Category> domestic;

	//母婴
//	@HtmlField(cssPath=".category-items > div:nth-child(2) > div:nth-child(2) > div.mc > div.items > dl")
	private List<Category> baby;


	public List<String> getBrand() {
		return brand;
	}

	public void setBrand(List<String> brand) {
		this.brand = brand;
	}

	public List<Category> getDomestic() {
		return domestic;
	}

	public void setDomestic(List<Category> domestic) {
		this.domestic = domestic;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}


	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public static void main(String[] args) {
//		//先获取分类列表
//		HttpGetRequest start = new HttpGetRequest("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=blanket+scarf&page=2");
//		start.setCharset("GBK");
//		GeccoEngine.create()
//		.classpath("com.geccocrawler.gecco.demo.amazon")
//		//开始抓取的页面地址
//		.start(start)
//		//开启几个爬虫线程
//		.thread(1)
//		//单个爬虫每次抓取完一个请求后的间隔时间
//		.interval(2000)
//		.run();
		
		
		//分类列表下的商品列表采用3线程抓取
		GeccoEngine.create()
				//工程的包路径
				.classpath("com.geccocrawler.gecco.demo.amazon")
				//开始抓取的页面地址
				.start("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=blanket+scarf&page=2")
				//开启几个爬虫线程
				.thread(3)
				//单个爬虫每次抓取完一个请求后的间隔时间
				.interval(2000)
				//循环抓取
//                .loop(true)
				//使用pc端userAgent
				.mobile(false)
				//非阻塞方式运行
				.start();
	}
}
