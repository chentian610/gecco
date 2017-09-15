package com.geccocrawler.gecco.demo.amazon;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HrefBean;

import java.util.ArrayList;
import java.util.List;

@PipelineName("allSortPipeline3")
public class AllSortPipeline implements Pipeline<AllSortAmazon> {
	
	public static List<HttpRequest> sortRequests = new ArrayList<HttpRequest>();

	@Override
	public void process(AllSortAmazon allSort) {
		System.out.println("amazon process1.....");
		List<String> brandList = allSort.getBrand();
		List<String> proudutList = allSort.getProudut_name();
		for (int i =0;i<brandList.size();i++) {
			String brand =  brandList.get(i);
			if ("soul young".equals(brand))
				System.out.println("徐荣良的内裤品牌:["+proudutList.get(i)+"]排名在第"+allSort.getPage()+"页"+(i+1)+"位");
		}
	}

	private void process(AllSortAmazon allSort, List<Category> categorys) {
		System.out.println("amazon process2.....");
		if(categorys == null) {
			return;
		}
		for(Category category : categorys) {
			List<HrefBean> hrefs = category.getCategorys();
			for(HrefBean href : hrefs) {
				String url = href.getUrl()+"&smToken=a42068ecda4a420eb085721f39222410&smSign=1hA5R0VgXw1pQITXx0yg1w%3D%3D";
				HttpRequest currRequest = allSort.getRequest();
				//SchedulerContext.into(currRequest.subRequest(url));
				//将分类的商品列表地址暂存起来
				sortRequests.add(currRequest.subRequest(url));
			}
		}
	}

}