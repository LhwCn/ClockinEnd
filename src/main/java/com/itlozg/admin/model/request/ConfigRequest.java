package com.itlozg.admin.model.request;


/**
 * @create 2017-12-15
 */
public class ConfigRequest extends BaseRequest {

    /**
     * 类型
     */
    private Integer category;


    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 是否固定， 0默认为不固定，1=固定；固定就不能再去修改
     */
    private Integer isFixed;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public ConfigRequest() {
    }

    public ConfigRequest(Integer category) {
        this.category = category;
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public static final Integer ABOUT_USE = 1;//关于我们
    public static final Integer START_TIME = 2;//订餐开始时间
    public static final Integer END_TIME = 3;//订餐结束时间
    public static final Integer DEVICE_NUM_PHONE = 4;//设备绑定数量  移动
    public static final Integer DEVICE_NUM_PC = 5;//设备绑定数量   PC
    public static final Integer SAVE_URL = 6;//聊天记录文件保存路径
    public static final Integer START_WORK_TIME_AM = 7;//上午上班时间
    public static final Integer END_WORK_TIME_AM = 8;//上午下班时间
    public static final Integer START_WORK_TIME_PM = 9;//下午上班时间
    public static final Integer END_WORK_TIME_PM = 10;//下午下班时间
    public static final Integer IS_OPEN_CHECK = 99;//是否开启检查
    public static final Integer OPEN_CHECK_WHITE = 100;//越权白名单
    public static final Integer CHARGE_OFF_START_TIME = 11;//订餐核销开始时间
    public static final Integer CHARGE_OFF_END_TIME = 12;//订餐核销结束时间
    public static final Integer CHARGE_CANCLE_END_TIME = 13;//订餐撤销结束时间
    public static final Integer REPLENISHMENT_CARD_NUM_LIMIT = 14;//补卡次数限制
    public static final Integer REVOKE_DATE_NUM_LIMIT = 16;//请假撤销配置
    public static final Integer IS_ALONE_USER = 15;//是否根据用户查询请假等数据
}
