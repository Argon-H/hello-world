package webMagic.processor;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.Date;

/**
 * Created by THINK on 2017/1/6.
 */
@TargetUrl("https://www\\.zhihu\\.com/search\\?type=people&q=[\\s\\S]+")
@HelpUrl("//ul[@class='list users']/li/div/div[@class='body']/div[@class='line']")
public class Test1RepoPageProcessor {

	private static String keyword = "JAVA";


	@ExtractBy(value = "//span[@class='ProfileHeader-name']/text()",notNull = true)
	private String name;


	public static void main(String[] args) {
//		Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();

		long startTime ,endTime;
		startTime = new Date().getTime();
		//入口为：【https://www.zhihu.com/search?type=people&q=xxx 】，其中xxx 是搜索关键词
		OOSpider.create(Site.me().setSleepTime(1000),new ConsolePageModelPipeline(), Test1RepoPageProcessor.class)
				.addUrl("https://www.zhihu.com/search?type=people&q="+keyword).thread(5).run();
		endTime = new Date().getTime();
		System.out.println("========知乎用户信息小爬虫【结束】喽！=========");
//		System.out.println(this.name);
	}

}

