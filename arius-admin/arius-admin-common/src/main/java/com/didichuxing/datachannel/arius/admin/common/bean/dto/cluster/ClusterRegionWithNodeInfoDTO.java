package com.didichuxing.datachannel.arius.admin.common.bean.dto.cluster;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by lyn on 2022/5/17
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "集群带节点信息的Region实体")
public class ClusterRegionWithNodeInfoDTO extends ClusterRegionDTO {
    @ApiModelProperty("绑定节点id列表")
    private List<Integer> bindingNodeIds;

    @ApiModelProperty("解绑节点id列表")
    private List<Integer> unBindingNodeIds;
}