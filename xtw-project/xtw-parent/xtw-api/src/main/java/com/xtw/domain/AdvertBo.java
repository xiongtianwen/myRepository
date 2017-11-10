package com.xtw.domain;




import com.xtw.common.BaseBo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhang on 2017/6/20.
 */
public class AdvertBo extends BaseBo implements Serializable  {
    private int id;     //主键
    private int ouId;       //采购组织ouid（ouid为0则是通用公告）
    private String adType;  //公告类型（1:轮播图2:平台公告3:优质供应商样品展示4:采购预告9：横幅广告,10:业务资讯）
    private String adTitle; //公告标题
    private String adCreater; //创建人
    private String adModifier;//修改人

    private String adFrom;  //公告来源(发布人)
    private String adLastDateStart; //采购预告截止时间开始(失效时间开始)
    private String adLastDateEnd;   //采购预告截止时间结束(失效时间结束)
    private String adCreateTimeStart;   //公告创建时间开始(生效时间开始)
    private String adCreateTimeEnd;     //公告创建时间结束(生效时间结束)
    private String martSign;    //市场标识（20：宝武 30：欧冶 40：化工宝）
    private String adStatus;    //状态（0：待发布、1：已发布、2：已取消、3：已删除；4：已禁用、5：已启用）

    private List adTypeList;//公告类型list
    private List adStatusList;//公告状态list

    private List martSignList;//市场标识list

    private String adCancelTime;//撤销时间
    private String adKeyWord; //关键字

    public String getAdModifier() {
        return adModifier;
    }

    public void setAdModifier(String adModifier) {
        this.adModifier = adModifier;
    }

    public List getMartSignList() {
        return martSignList;
    }

    public void setMartSignList(List martSignList) {
        this.martSignList = martSignList;
    }

    public List getAdStatusList() {
        return adStatusList;
    }

    public void setAdStatusList(List adStatusList) {
        this.adStatusList = adStatusList;
    }

    public String getAdKeyWord() {
        return adKeyWord;
    }

    public void setAdKeyWord(String adKeyWord) {
        this.adKeyWord = adKeyWord;
    }

    public List getAdTypeList() {
        return adTypeList;
    }

    public void setAdTypeList(List adTypeList) {
        this.adTypeList = adTypeList;
    }

    public String getAdCancelTime() {
        return adCancelTime;
    }

    public void setAdCancelTime(String adCancelTime) {
        this.adCancelTime = adCancelTime;
    }

    public String getAdCreater() {
        return adCreater;
    }

    public void setAdCreater(String adCreater) {
        this.adCreater = adCreater;
    }
    public String getMartSign() {
        return martSign;
    }

    public void setMartSign(String martSign) {
        this.martSign = martSign;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus;
    }

    public String getAdCreateTimeStart() {
        return adCreateTimeStart;
    }

    public void setAdCreateTimeStart(String adCreateTimeStart) {
        this.adCreateTimeStart = adCreateTimeStart;
    }

    public String getAdCreateTimeEnd() {
        return adCreateTimeEnd;
    }

    public void setAdCreateTimeEnd(String adCreateTimeEnd) {
        this.adCreateTimeEnd = adCreateTimeEnd;
    }

    public String getAdLastDateStart() {
        return adLastDateStart;
    }

    public void setAdLastDateStart(String adLastDateStart) {
        this.adLastDateStart = adLastDateStart;
    }

    public String getAdLastDateEnd() {
        return adLastDateEnd;
    }

    public void setAdLastDateEnd(String adLastDateEnd) {
        this.adLastDateEnd = adLastDateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOuId() {
        return ouId;
    }

    public void setOuId(int ouId) {
        this.ouId = ouId;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdFrom() {
        return adFrom;
    }

    public void setAdFrom(String adFrom) {
        this.adFrom = adFrom;
    }

//    @Override
//    public String toString() {
//        return "AdvertBo{" +
//                "adCreateTimeEnd='" + adCreateTimeEnd + '\'' +
//                ", id=" + id +
//                ", ouId=" + ouId +
//                ", adType='" + adType + '\'' +
//                ", adTitle='" + adTitle + '\'' +
//                ", adFrom='" + adFrom + '\'' +
//                ", adLastDateStart='" + adLastDateStart + '\'' +
//                ", adLastDateEnd='" + adLastDateEnd + '\'' +
//                ", adCreateTimeStart='" + adCreateTimeStart + '\'' +
//                ", martSign='" + martSign + '\'' +
//                ", adStatus='" + adStatus + '\'' +
//                '}';
//    }
}
