package webMagic.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class JDPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(10).setSleepTime(1000).setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
	private static int num = 0;
	private static OkHttpClient client = new OkHttpClient.Builder().readTimeout(15,
			TimeUnit.SECONDS)// 设置读取超时时间
			.writeTimeout(15, TimeUnit.SECONDS)// 设置写的超时时间
			.connectTimeout(15, TimeUnit.SECONDS).build();
	//搜索关键词
//	private static String keyword = "JAVA";
	//数据库持久化对象，用于将用户信息存入数据库

	@Override
	public void process(Page page) {
		//自动启动浏览器
//		System.getProperties().setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
//		WebDriver webDriver = new ChromeDriver();
//		webDriver.get("https://item.jd.com/2461934.html");
//		WebElement webElement = webDriver.findElement(By.xpath("/html"));
//		Html hhh = new Html(webElement.getAttribute("outerHTML"));
		System.out.println(page.getUrl().replace("https://item.jd.com/|.html",""));
		String dataName = page.getUrl().replace("https://item.jd.com/|.html","").get();
//		System.out.println(page.getUrl().regex("https://item.jd.com/[.?].html"));
		boolean isFemale = page.getHtml().xpath("//span[@class='item gender']/i[@class='icon icon-profile-female']").match();
		String picUrl = page.getHtml().xpath("//div[@class='body clearfix']/img[@class='Avatar Avatar--l']/@src").get();

		String heart = "http://img14.360buyimg.com/n1/s450x450_";

		List<Selectable> sc2 = page.getHtml().$("#spec-list").$("ul").$("img","data-url").nodes();


		System.out.println("sec:"+sc2);//输出对象
		int num = 0;
		for (Selectable s : sc2) {
			try {
				URL u = new URL(heart+s.get());
				File f = new File("D://123/"+dataName+"/"+String.format("%03d", num)+".jpg");
//				DownloadImageTest.download(heart+s.get(),String.format("%03d", num)+".jpg","D://123");
				FileUtils.copyURLToFile(u,f);
				num++;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Request request = new Request.Builder().url("https://dx.3.cn/desc/"+dataName+"?cdn=2&callback=showdesc").build();
		String result = "";
		JSONObject jsons = null;
		try {
			Response response =client.newCall(request).execute();
			result = response.body().string();
			jsons = (JSONObject) JSON.parse(result.replaceAll("showdesc\\("," ").replaceAll("}\\)","}"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		Html hhh = new Html(jsons.get("content").toString());
		System.out.println(hhh.toString());
		List<Selectable> sc3 = hhh.$("img","data-lazyload").nodes();
		num = 0;
		System.out.println("sc3"+sc3);
		for (Selectable s : sc3) {
			try {
				URL ul = new URL("http:"+s.get());
				System.out.println(ul.toString());
				File fl = new File("D://123/"+dataName+"/list_"+String.format("%03d", num)+".jpg");
				FileUtils.copyURLToFile(ul,fl);
				num++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		webDriver.close();
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {


		Spider.create(new JDPageProcessor()).addUrl("https://item.jd.com/1371193.html").thread(1).run();
	}
}