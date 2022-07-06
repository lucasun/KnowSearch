package com.didichuxing.datachannel.arius.admin.biz.template.new_srv;

import com.didichuxing.datachannel.arius.admin.common.bean.common.PaginationResult;
import com.didichuxing.datachannel.arius.admin.common.bean.common.Result;
import com.didichuxing.datachannel.arius.admin.common.bean.dto.template.srv.TemplateQueryDTO;
import com.didichuxing.datachannel.arius.admin.common.bean.entity.cluster.ClusterPhy;
import com.didichuxing.datachannel.arius.admin.common.bean.entity.template.srv.TemplateSrv;
import com.didichuxing.datachannel.arius.admin.common.bean.entity.template.srv.UnavailableTemplateSrv;
import com.didichuxing.datachannel.arius.admin.common.bean.vo.cluster.ESClusterTemplateSrvVO;
import com.didichuxing.datachannel.arius.admin.common.bean.vo.template.srv.TemplateWithSrvVO;
import com.didichuxing.datachannel.arius.admin.common.exception.NotFindSubclassException;
import java.util.List;

/**
 * @author chengxiang
 * @date 2022/5/17
 */
public interface TemplateSrvManager {

    /**
     * 判断指定逻辑模板是否开启了该模板服务
     * @param logicTemplateId 逻辑模板id
     * @param templateSrvId 模板服务id
     * @return
     */
    boolean isTemplateSrvOpen(Integer logicTemplateId, Integer templateSrvId);


    /**
     * 获取指定模板「开启的」服务
     * @param logicTemplateId 逻辑模板id
     * @return
     */
    Result<List<TemplateSrv>> getTemplateOpenSrv(Integer logicTemplateId);

    /**
     * 获取指定模板「不可用的」服务
     * @param logicTemplateId 逻辑模板id
     * @return
     */
    List<UnavailableTemplateSrv> getUnavailableSrv(Integer logicTemplateId);

    /**
     * 分页模糊查询模板服务
     * @param condition
     * @return
     */
    PaginationResult<TemplateWithSrvVO> pageGetTemplateWithSrv(TemplateQueryDTO condition) throws NotFindSubclassException;

    /**
     * 开启模板服务
     *
     * @param srvCode        服务代码
     * @param templateIdList 模板id列表
     * @param operator
     * @param projectId
     * @return
     */
    Result<Void> openSrv(Integer srvCode, List<Integer> templateIdList, String operator, Integer projectId);

    /**
     * 关闭模板服务
     *
     * @param srvCode        服务代码
     * @param templateIdList 模板id列表
     * @param operator
     * @param projectId
     * @return
     */
    Result<Void> closeSrv(Integer srvCode, List<Integer> templateIdList, String operator, Integer projectId);
    
    List<Integer> getPhyClusterTemplateSrvIds(String clusterPhyName);
    
    Result<Boolean> replaceTemplateServes(String phyClusterName, List<Integer> clusterTemplateSrvIdList, String operator);
    
        /**
     * 清理所有索引服务
     * @param clusterPhy 物理集群名称
     * @param operator   操作人
     * @return {@link Result}<{@link Boolean}>
     */
    Result<Boolean> delAllTemplateSrvByClusterPhy(String clusterPhy, String operator);
    /**
     * 查询开启了某个索引服务的物理集群列表
     * @param clusterPhies
     * @param srvId
     * @return
     */
    List<String> getPhyClusterByOpenTemplateSrv(List<ClusterPhy> clusterPhies, int srvId);
    
    /**
     * 获取逻辑集群索引服务
     *
     * @param clusterLogicId
     * @return
     */
    Result<List<ESClusterTemplateSrvVO>> getClusterLogicTemplateSrv(Long clusterLogicId);
    
        /**
     * 为逻辑集群增加一个索引服务
     *
     * @param clusterLogicId 逻辑集群Id
     * @param templateSrvId  索引服务Id
     * @param operator       操作者
     * @param projectId
     * @return
     */
    Result<Boolean> addTemplateSrvForClusterLogic(Long clusterLogicId, String templateSrvId, String operator,
                                                  Integer projectId);

    /**
     * 获取逻辑集群可选择的索引服务
     * @param clusterLogicId 逻辑集群Id
     * @return
     */
    Result<List<ESClusterTemplateSrvVO>> getClusterLogicSelectableTemplateSrv(Long clusterLogicId);
    
        /**
     * 逻辑集群删除一个索引服务
     *
     * @param clusterLogicId
     * @param templateSrvId
     * @param operator
     * @param projectId
     * @return
     */
    Result<Boolean> delTemplateSrvForClusterLogic(Long clusterLogicId, String templateSrvId, String operator,
                                                  Integer projectId);
}