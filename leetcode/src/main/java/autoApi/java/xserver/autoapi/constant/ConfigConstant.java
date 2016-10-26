package autoApi.java.xserver.autoapi.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用常量类
 * @author liqiangqiang
 *
 */
public class ConfigConstant {

	/**
	 * 初始化参数
	 * @param scanPath 扫描的路径
	 * @param localPath 存档文档的根目录
	 * @param filterPath 过滤的路径
	 * @param choose 区分tv和mobile
	 */
	public static void init(String scanPath, String localPath, String filterPath, String choose){
		rootpath = null;
		rootpath = System.getProperty("staticPath.dir");
		path = scanPath;
		rootpath = rootpath + "/" + localPath + "/";
		filterpath = filterPath;
		mobile2Tv = choose;
		localCatalog = localPath;

		if(choose.equalsIgnoreCase("mobile")){
			IntersMaps = Mobilemaps;
		}else if(choose.equalsIgnoreCase("tv")){
			IntersMaps = TVmaps;
		}else if(choose.equalsIgnoreCase("live")){
			IntersMaps = Livemaps;
		}else{
			IntersMaps = Mobilemaps;
		}
	}

    /**
     * 配置
     */
    public static String localCatalog = "apidocs";

	public static String mobile2Tv = "mobile";

	public static Map<String, List<String>> IntersMaps = new LinkedHashMap<String, List<String>>();

    public static String path = "";

    public static String rootpath = System.getProperty("staticPath.dir");

    public static String filterpath = "/branch1";

    public static final String CON = "Controller";

    public static final String DTO = "Dto";

    public static final String DOCJAVA = ".java";

    public static final String DOCHTML = ".html";

    public static final String RESPONSE = "Response";

    public static final String COMMENTPARAM = "CommonParam.java";

    public static final String LEFT = "left.html";

    public static final String RIGHT = "right.html";

    public static final String INDEX = "index.html";

    public static final String BASERESPONSE_JAVA = "Response.java";

    public static final String DTO_JAVA = "Dto.java";

	public static final Map<String, List<String>> TVmaps = new LinkedHashMap<String, List<String>>();

    static {
    	TVmaps.put("音乐",Arrays.asList(new String[]{"MusicController"}));
    	TVmaps.put("通知",Arrays.asList(new String[]{"NotificationController"}));
        TVmaps.put("推荐",Arrays.asList(new String[]{"RecommendationController"}));
//        TVmaps.put("三屏",Arrays.asList(new String[]{"ThreeScreenController"}));
        TVmaps.put("频道",Arrays.asList(new String[]{"ChannelController", "ChannelController2"}));
        TVmaps.put("直播",Arrays.asList(new String[]{"LiveController"}));
        TVmaps.put("用户",Arrays.asList(new String[]{"PlayFavoriteController", "PlayLogController",
        		"UserController"})); 
        TVmaps.put("搜索",Arrays.asList(new String[]{"SearchController"}));
        TVmaps.put("视频",Arrays.asList(new String[]{"VideoController", "DownloadController"}));
        TVmaps.put("会员",Arrays.asList(new String[]{"VipController"}));
        TVmaps.put("桌面",Arrays.asList(new String[]{"DeskController"}));
        TVmaps.put("终端",Arrays.asList(new String[]{"BootStrapController", "TerminalApiController"}));
        TVmaps.put("其它", Arrays.asList(new String[]{"ErrorResponseController", "IndexController",
        		"MemController", "ShareController"}));
    }

    public static final Map<String, List<String>> Mobilemaps = new LinkedHashMap<String, List<String>>();

    static {
    	Mobilemaps.put("预约",Arrays.asList(new String[]{"AppointmentController", "SubscribeController"}));
    	Mobilemaps.put("关注",Arrays.asList(new String[]{"AttentionController"}));
    	Mobilemaps.put("频道",Arrays.asList(new String[]{"ChannelController", "ChannelController2"}));
    	Mobilemaps.put("评论",Arrays.asList(new String[]{"CommentController"}));
    	Mobilemaps.put("直播",Arrays.asList(new String[]{"LiveController"}));
    	Mobilemaps.put("播放",Arrays.asList(new String[]{"PlayController"}));
    	Mobilemaps.put("用户",Arrays.asList(new String[]{"PlayFavoriteController", "PlayLogController",
    			"UserController"})); 
    	Mobilemaps.put("搜索",Arrays.asList(new String[]{"SearchController"}));
    	Mobilemaps.put("专题",Arrays.asList(new String[]{"SubjectController"}));
    	Mobilemaps.put("live",Arrays.asList(new String[]{"SuperLiveChatRoomController",
    			"SuperLiveController", "SuperLivePlayLogController", "SuperLiveV2Controller"}));
    	Mobilemaps.put("视频",Arrays.asList(new String[]{"VideoController"}));
    	Mobilemaps.put("会员",Arrays.asList(new String[]{"VipController"}));
    	Mobilemaps.put("点赞",Arrays.asList(new String[]{"VoteController"}));
    	Mobilemaps.put("弹幕", Arrays.asList(new String[] {"DanmuController"}));
    	Mobilemaps.put("儿童", Arrays.asList(new String[] {"ChildController"}));
    	Mobilemaps.put("其它", Arrays.asList(new String[]{"BootStrapController", "ErrorResponseController",
    			"IndexController", "MemController", "ShareController"}));
    }

    //TODO
    public static final Map<String, List<String>> Livemaps = new LinkedHashMap<String, List<String>>();
    static {
    	Livemaps.put("预约",Arrays.asList(new String[]{"AppointmentController", "SubscribeController"}));
    	Livemaps.put("关注",Arrays.asList(new String[]{"AttentionController"}));
    	Livemaps.put("频道",Arrays.asList(new String[]{"ChannelController", "ChannelController2"}));
    	Livemaps.put("评论",Arrays.asList(new String[]{"CommentController"}));
    	Livemaps.put("直播",Arrays.asList(new String[]{"LiveController"}));
    	Livemaps.put("播放",Arrays.asList(new String[]{"PlayController"}));
    	Livemaps.put("用户",Arrays.asList(new String[]{"PlayFavoriteController", "PlayLogController",
    			"UserController"})); 
    	Livemaps.put("搜索",Arrays.asList(new String[]{"SearchController"}));
    	Livemaps.put("专题",Arrays.asList(new String[]{"SubjectController"}));
    	Livemaps.put("live",Arrays.asList(new String[]{"SuperLiveChatRoomController",
    			"SuperLiveController", "SuperLivePlayLogController", "SuperLiveV2Controller"}));
    	Livemaps.put("视频",Arrays.asList(new String[]{"VideoController"}));
    	Livemaps.put("会员",Arrays.asList(new String[]{"VipController"}));
    	Livemaps.put("点赞",Arrays.asList(new String[]{"VoteController"}));
//    	Livemaps.put("弹幕", Arrays.asList(new String[] {"DanmuController"}));
//    	Livemaps.put("儿童", Arrays.asList(new String[] {"ChildController"}));
    	Livemaps.put("其它", Arrays.asList(new String[]{"BootStrapController", "ErrorResponseController",
    			"IndexController", "MemController", "ShareController"}));
    }

    public static final String OTHER_CONT = "其它";
    public static final String[] Controllers = new String[] {
    		"ChildController",
    		"DanmuController",
    		"MusicController",
    		"NotificationController",
    		"RecommendationController",
    		"ThreeScreenController",
    		"ErrorResponseController", 
    		"TerminalApiController",
    		"AppointmentController",
    		"SubscribeController",
    		"AttentionController",
    		"ChannelController", 
    		"ChannelController2",
    		"CommentController",
    		"LiveController",
    		"PlayController",
    		"PlayFavoriteController", 
    		"PlayLogController",
    		"UserController",
    		"SearchController",
    		"SubjectController",
    		"SuperLiveChatRoomController",
    		"SuperLiveController", 
    		"SuperLivePlayLogController", 
    		"SuperLiveV2Controller",
    		"VideoController",
    		"VipController",
    		"VoteController",
    		"BootStrapController",
    		"IndexController", 
    		"MemController",
    		"ShareController"
    };

    public static final String BASERESPONSE = "BaseResponse";
    public static final String APIDOCS = "apidocs";
    public static final String PACKAGE_INFO = "package-info.java";
    public static final String COMMONPARAM = "CommonParam";
}
