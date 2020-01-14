package com.ruoyi.project.business.game.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 游戏对象 bl_game
 * 
 * @author ys
 * @date 2019-12-24
 */
public class Game extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 游戏ID */
    private Integer gameId;

    /** 游戏标题 */
    @Excel(name = "游戏标题")
    private String gameTitle;

    /** 游戏类型 */
    @Excel(name = "游戏类型")
    private String gameType;

    /** 游戏标签 */
    @Excel(name = "游戏标签")
    private String gameLabel;

    /** 描述图片 */
    @Excel(name = "描述图片")
    private String gameImg;

    /** 游戏描述 */
    @Excel(name = "游戏描述")
    private String gameDescribe;

    /** 游戏内容 */
    @Excel(name = "游戏内容")
    private String gameContent;

    /** 游戏文件外链 */
    @Excel(name = "游戏文件外链")
    private String gameLink;

    /** 游戏文件外链备注 */
    @Excel(name = "游戏文件外链备注")
    private String gameLinknotes;

    /** 游戏状态（0正常 1关闭） */
    @Excel(name = "游戏状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 唯一标识uuid */
    private String uuid;

    public void setGameId(Integer gameId) 
    {
        this.gameId = gameId;
    }

    public Integer getGameId() 
    {
        return gameId;
    }
    public void setGameTitle(String gameTitle) 
    {
        this.gameTitle = gameTitle;
    }

    public String getGameTitle() 
    {
        return gameTitle;
    }
    public void setGameType(String gameType) 
    {
        this.gameType = gameType;
    }

    public String getGameType() 
    {
        return gameType;
    }
    public void setGameLabel(String gameLabel) 
    {
        this.gameLabel = gameLabel;
    }

    public String getGameLabel() 
    {
        return gameLabel;
    }
    public void setGameImg(String gameImg) 
    {
        this.gameImg = gameImg;
    }

    public String getGameImg() 
    {
        return gameImg;
    }
    public void setGameDescribe(String gameDescribe) 
    {
        this.gameDescribe = gameDescribe;
    }

    public String getGameDescribe() 
    {
        return gameDescribe;
    }
    public void setGameContent(String gameContent) 
    {
        this.gameContent = gameContent;
    }

    public String getGameContent() 
    {
        return gameContent;
    }
    public void setGameLink(String gameLink) 
    {
        this.gameLink = gameLink;
    }

    public String getGameLink() 
    {
        return gameLink;
    }
    public void setGameLinknotes(String gameLinknotes) 
    {
        this.gameLinknotes = gameLinknotes;
    }

    public String getGameLinknotes() 
    {
        return gameLinknotes;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setUuid(String uuid) 
    {
        this.uuid = uuid;
    }

    public String getUuid() 
    {
        return uuid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("gameId", getGameId())
            .append("gameTitle", getGameTitle())
            .append("gameType", getGameType())
            .append("gameLabel", getGameLabel())
            .append("gameImg", getGameImg())
            .append("gameDescribe", getGameDescribe())
            .append("gameContent", getGameContent())
            .append("gameLink", getGameLink())
            .append("gameLinknotes", getGameLinknotes())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("uuid", getUuid())
            .toString();
    }
}
