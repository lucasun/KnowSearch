package com.didichuxing.datachannel.arius.admin.biz.template.srv.expire;

import com.didichuxing.datachannel.arius.admin.common.bean.common.Result;
import com.didichuxing.datachannel.arius.admin.common.bean.entity.template.IndexTemplatePhy;
import com.didichuxing.datachannel.arius.admin.common.exception.ESOperateException;

/**
 * @author chengxiang
 * @date 2022/5/12
 */
public interface ExpireManager {

    /**
     * 删除过期索引
     * @param logicTemplateId 逻辑模板id
     * @return true/false
     */
    Result<Void> deleteExpireIndex(Integer logicTemplateId);
    /////////////srv

    /**
     * 删除过期索引
     *
     * @param cluster 集群
     * @return true/false
     * @throws
     */
    @Deprecated
    boolean deleteExpireIndex(String cluster);

    /**
     * 删除模板过期索引
     *  1、可以是当前集群存在的物理模板
     *  2、可以是已经从当前集群迁移走的模板,但是还有数据在当前集群
     * @param physicalId 物理模板id
     * @param retryCount 重试次数
     * @return true/false
     */
    boolean deleteExpireIndices(Long physicalId, int retryCount);

    /**
     * 删除已经被删除的模板的索引
     * @param physical 物理模板信息
     * @param retryCount 重试次数
     * @throws ESOperateException e
     * @return true/false
     */
    boolean deleteTemplateDeletedIndices(IndexTemplatePhy physical, int retryCount) throws ESOperateException;

}