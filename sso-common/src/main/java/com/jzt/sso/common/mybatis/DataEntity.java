package com.jzt.sso.common.mybatis;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by on 2017/2/15.
 */
//
//@Getter @Setter
public abstract class DataEntity implements Serializable {

    private static final long serialVersionUID = -219830449865498504L;
    // /**
    // * 创建人
    // */
    //
    // @TableField(value = "create_by", fill = FieldFill.INSERT)
    // private String createBy;
    //

    /**
     * 创建时间
     */
    @TableField(value = "createtime", fill = FieldFill.INSERT) private Date createTime;

    // /**
    // * 更新人
    // */
    //
    // @TableField(value = "update_by", fill = FieldFill.UPDATE)
    // private String updateBy;
    //
    /**
     * 更新时间
     */

    @TableField(value = "updatetime", fill = FieldFill.UPDATE) private Date updateTime;

    /**
     * 逻辑删除标志
     */
    @TableField(value = "del_flag") @TableLogic private Integer delFlag;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
