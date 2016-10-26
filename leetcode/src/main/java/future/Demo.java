package future;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acync.guava.BaseRequest;
import acync.guava.ConcreateWapper;
import acync.guava.FutureTpDao;
import acync.guava.IEnum;

public class Demo {

    public static void main(String[] args) {
        ConcreateWapper wapper = new ConcreateWapper();
        Map request = new HashMap<>();
        Class<?> clazz = new PaymentActivityTpResponse().getClass();
        BaseRequest userRequest = new UserRequest();
        request.put(userRequest, clazz);
        wapper.setParams(UserEnum.ADD, null, request);

        FutureTpDao futureTpDaof = new FutureTpDao();
        Map<IEnum, Object> httpData = futureTpDaof.getHttpData(wapper);
        System.err.println(httpData.get(UserEnum.ADD));
        
    }
}


enum UserEnum implements IEnum{
    ADD,
    UPDATE,
    DELTE,
    MODIFY
}

class UserRequest implements BaseRequest {
    private static final String REQ_URL = "http://yuanxian.letv.com/letv/activity.ldo?userId=1&svip=1&from=141004";
    
    @Override
    public String build() {
        return REQ_URL;
    }
}


class PaymentActivityTpResponse {

    private String code; // 状态码，0--成功

    // 活动信息
    private PaymentActivityMap values;

    public boolean isSucceed() {
        return (!"0".equals(this.code) || this.values != null);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PaymentActivityMap getValues() {
        return this.values;
    }

    public void setValues(PaymentActivityMap values) {
        this.values = values;
    }

    /**
     * @author
     */
    public static class PaymentActivityMap {

        /**
         * 活动信息映射，key--活动类型，1--套餐活动，2--支付渠道活动，value--活动详细信息列表
         */
        private Map<String, List<PaymentActivityInfo>> activitys;

        public Map<String, List<PaymentActivityInfo>> getActivitys() {
            return this.activitys;
        }

        public void setActivitys(Map<String, List<PaymentActivityInfo>> activitys) {
            this.activitys = activitys;
        }

    }


   static class PaymentActivityInfo {

        private String activityId; // 活动id，唯一标识本次活动
        private List<String> terminals; // 活动适用终端列表
        private Integer type; // 活动类型，1，套餐活动 2:支付活动
        private Integer monthType; // 套餐包id，type为1时有意义
        private List<Integer> payTypeList; // 活动支持的支付渠道，type为2时有意义
        private Boolean allowPayLepoint; // 是否允许乐点支付参与活动
        private Boolean hasUserQuota; // 当前用户是否有参加活动次数限制，true--有，false--没有
        private Integer payTypeGroupId;
        private String iconText; // 角标文案
        private String lableText; // 标签文案
        private String bodyText; // 主体文案
        private Integer priority; // 优先级
        private Long beginTime; // 活动开始时间
        private Long endTime; // 活动结束时间
        private Integer discount; // 折扣,代表减少多少钱 单位元
        private Integer quota; // 活动预设总数，-1表示不限量
        private Integer prolongDays;
        private Integer userQuota; // 当前用户剩余可参与活动次数，-1表示无限制
        private Boolean needLogin; // 是否需要登录
        private String intervalBegin; // 在活动期间，每天的开始时间
        private String intervalEnd; // 在活动期间，每天的结束时间
        private Integer leftQuota; // 活动剩余可用数，-1表示无限制

        public PaymentActivityInfo() {
            super();
        }

        public String getActivityId() {
            return this.activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public List<String> getTerminals() {
            return this.terminals;
        }

        public void setTerminals(List<String> terminals) {
            this.terminals = terminals;
        }

        public Integer getType() {
            return this.type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getMonthType() {
            return this.monthType;
        }

        public void setMonthType(Integer monthType) {
            this.monthType = monthType;
        }

        public List<Integer> getPayTypeList() {
            return this.payTypeList;
        }

        public void setPayTypeList(List<Integer> payTypeList) {
            this.payTypeList = payTypeList;
        }

        public Boolean getAllowPayLepoint() {
            return this.allowPayLepoint;
        }

        public void setAllowPayLepoint(Boolean allowPayLepoint) {
            this.allowPayLepoint = allowPayLepoint;
        }

        public Boolean getHasUserQuota() {
            return this.hasUserQuota;
        }

        public void setHasUserQuota(Boolean hasUserQuota) {
            this.hasUserQuota = hasUserQuota;
        }

        public Integer getPayTypeGroupId() {
            return this.payTypeGroupId;
        }

        public void setPayTypeGroupId(Integer payTypeGroupId) {
            this.payTypeGroupId = payTypeGroupId;
        }

        public String getIconText() {
            return this.iconText;
        }

        public void setIconText(String iconText) {
            this.iconText = iconText;
        }

        public String getLableText() {
            return this.lableText;
        }

        public void setLableText(String lableText) {
            this.lableText = lableText;
        }

        public String getBodyText() {
            return this.bodyText;
        }

        public void setBodyText(String bodyText) {
            this.bodyText = bodyText;
        }

        public Integer getPriority() {
            return this.priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Long getBeginTime() {
            return this.beginTime;
        }

        public void setBeginTime(Long beginTime) {
            this.beginTime = beginTime;
        }

        public Long getEndTime() {
            return this.endTime;
        }

        public void setEndTime(Long endTime) {
            this.endTime = endTime;
        }

        public Integer getDiscount() {
            return this.discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

        public Integer getQuota() {
            return this.quota;
        }

        public void setQuota(Integer quota) {
            this.quota = quota;
        }

        public Integer getProlongDays() {
            return this.prolongDays;
        }

        public void setProlongDays(Integer prolongDays) {
            this.prolongDays = prolongDays;
        }

        public Integer getUserQuota() {
            return this.userQuota;
        }

        public void setUserQuota(Integer userQuota) {
            this.userQuota = userQuota;
        }

        public Boolean getNeedLogin() {
            return this.needLogin;
        }

        public void setNeedLogin(Boolean needLogin) {
            this.needLogin = needLogin;
        }

        public String getIntervalBegin() {
            return this.intervalBegin;
        }

        public void setIntervalBegin(String intervalBegin) {
            this.intervalBegin = intervalBegin;
        }

        public String getIntervalEnd() {
            return this.intervalEnd;
        }

        public void setIntervalEnd(String intervalEnd) {
            this.intervalEnd = intervalEnd;
        }

        public Integer getLeftQuota() {
            return this.leftQuota;
        }

        public void setLeftQuota(Integer leftQuota) {
            this.leftQuota = leftQuota;
        }
    }
}