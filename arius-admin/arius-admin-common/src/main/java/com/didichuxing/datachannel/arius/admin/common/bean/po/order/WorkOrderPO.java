package com.didichuxing.datachannel.arius.admin.common.bean.po.order;

import java.util.Date;

import com.didichuxing.datachannel.arius.admin.common.constant.workorder.WorkOrderTypeEnum;
import com.didichuxing.datachannel.arius.admin.common.bean.po.BasePO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fengqiongfeng
 * @since 2020-08-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderPO extends BasePO {

    /**
     * ID
     */
    private Long    id;

    /**
     * @see WorkOrderTypeEnum
     */
    private String  type;

    /**
     * 标题
     */
    private String  title;

    /**
     * 扩展字段
     */
    private String  extensions;

    /**
     * 申请人
     */
    private String  applicant;

    /**
     * 申请人projectid
     */
    private Integer applicantProjectId;

    /**
     * 备注信息
     */
    private String  description;

    /**
     * 审批人
     */
    private String  approver;

    /**
     * 结束时间
     */
    private Date    finishTime;

    /**
     * 审批信息
     */
    private String  opinion;

    /**
     * 工单状态, 0:待审批, 1:通过, 2:拒绝, 3:取消
     */
    private Integer status;

    /**
     * 审批人projectId
     */
    private Integer approverProjectId;
}