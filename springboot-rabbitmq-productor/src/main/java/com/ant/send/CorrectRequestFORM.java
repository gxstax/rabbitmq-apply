package com.ant.send;

import java.io.Serializable;

/**
 * <p>
 * 礼品卡交易冲正消息体
 * </p>
 *
 * @author GaoXin
 * @since 2020-01-06 15:03
 */

public class CorrectRequestFORM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 外部原始交易流水号
     */
    private String srcOutTradeNo;

    /**
     * 外部原始业务交易码：T00001 支付，T00002 充值，T00003 支付退款，T00004 冲正
     */
    private String srcTransCode;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 交易备注
     */
    private String remark;


    public String getSrcOutTradeNo() {
        return srcOutTradeNo;
    }

    public void setSrcOutTradeNo(String srcOutTradeNo) {
        this.srcOutTradeNo = srcOutTradeNo;
    }

    public String getSrcTransCode() {
        return srcTransCode;
    }

    public void setSrcTransCode(String srcTransCode) {
        this.srcTransCode = srcTransCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
