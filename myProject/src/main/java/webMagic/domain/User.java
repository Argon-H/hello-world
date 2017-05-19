package webMagic.domain;


import lombok.Data;

/**
 * Created by THINK on 2016/12/26.
 */
@Data
public class User {
	private String key;//keyword
	private String name;//用户名
	private String identity;//身份
	private String location;//所在地
	private String profession;//行业
	private int sex;//性别
	private String school;//学校
	private String major;//专业
	private String recommend;//个人简介
	private String picUrl;//头像url
	private int agree;//赞同
	private int thanks;//感谢
	private int ask;//提问数
	private int answer;//回答数
	private int article;//文章数
	private int collection;//收藏数
}
