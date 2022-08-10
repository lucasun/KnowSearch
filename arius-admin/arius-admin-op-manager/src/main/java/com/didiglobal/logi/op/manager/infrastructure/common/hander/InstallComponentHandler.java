package com.didiglobal.logi.op.manager.infrastructure.common.hander;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.didiglobal.logi.op.manager.domain.component.entity.Component;
import com.didiglobal.logi.op.manager.domain.component.event.ComponentEvent;
import com.didiglobal.logi.op.manager.domain.component.service.ComponentDomainService;
import com.didiglobal.logi.op.manager.domain.packages.entity.Package;
import com.didiglobal.logi.op.manager.domain.packages.service.PackageDomainService;
import com.didiglobal.logi.op.manager.domain.script.service.impl.ScriptDomainService;
import com.didiglobal.logi.op.manager.domain.task.service.TaskDomainService;
import com.didiglobal.logi.op.manager.infrastructure.common.bean.GeneralInstallComponent;
import com.didiglobal.logi.op.manager.infrastructure.common.enums.OperationEnum;
import com.didiglobal.logi.op.manager.infrastructure.common.enums.PackageTypeEnum;
import com.didiglobal.logi.op.manager.infrastructure.common.hander.base.BaseComponentHandler;
import com.didiglobal.logi.op.manager.infrastructure.common.hander.base.ComponentHandler;
import com.didiglobal.logi.op.manager.infrastructure.common.hander.base.DefaultHandler;
import com.didiglobal.logi.op.manager.infrastructure.exception.ComponentHandlerException;
import com.didiglobal.logi.op.manager.infrastructure.util.ConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author didi
 * @date 2022-07-16 2:15 下午
 */
@org.springframework.stereotype.Component
@DefaultHandler
public class InstallComponentHandler extends BaseComponentHandler implements ComponentHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstallComponentHandler.class);

    private static final String REX = ",";

    @Autowired
    private TaskDomainService taskDomainService;

    @Autowired
    private PackageDomainService packageDomainService;

    @Autowired
    private ScriptDomainService scriptDomainService;

    @Autowired
    private ComponentDomainService componentDomainService;

    @Override
    public void eventProcess(ComponentEvent componentEvent) throws ComponentHandlerException {
        try {
            GeneralInstallComponent installComponent = (GeneralInstallComponent) componentEvent.getSource();
            String associationId = installComponent.getAssociationId();

            Package pk = packageDomainService.queryPackage(Package.builder().id(installComponent.getPackageId()).build()).
                    getData().get(0);
            if (pk.getType() == PackageTypeEnum.CONFIG_DEPENDENT.type) {
                installComponent.setDependConfigComponentId(installComponent.getDependComponentId());
            }
            installComponent.setTemplateId(scriptDomainService.getScriptById(pk.getScriptId()).getData().getTemplateId());
            String content = JSONObject.toJSON(installComponent).toString();

            Map<String, List<String>> groupToIpList = new LinkedHashMap<>(16);
            installComponent.getGroupConfigList().forEach(config ->
            {
                if (!StringUtils.isEmpty(config.getHosts())) {
                    groupToIpList.put(config.getGroupName(), Arrays.asList(config.getHosts().split(REX)));
                }
            });
            taskDomainService.createTask(content, componentEvent.getOperateType(),
                    componentEvent.getDescribe(), associationId, groupToIpList);
        } catch (Exception e) {
            LOGGER.error("event process error.", e);
            throw new ComponentHandlerException(e);
        }
    }

    @Override
    public void taskFinishProcess(String content) throws ComponentHandlerException {
        try {
            GeneralInstallComponent installComponent = JSON.parseObject(content, GeneralInstallComponent.class);
            Component component = ConvertUtil.obj2Obj(installComponent, Component.class);
            componentDomainService.createComponent(component);
        } catch (Exception e) {
            LOGGER.error("component[{}] handler error.", content, e);
            throw new ComponentHandlerException(e);
        }
    }

    @Override
    public Integer getOperationType() throws ComponentHandlerException {
        return OperationEnum.INSTALL.getType();
    }


}
