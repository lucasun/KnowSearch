package com.didichuxing.datachannel.arius.admin.rest.controller.v3.app;

import static com.didichuxing.datachannel.arius.admin.common.constant.ApiVersion.V3;

import com.didichuxing.datachannel.arius.admin.biz.app.ESUserManager;
import com.didichuxing.datachannel.arius.admin.common.bean.common.Result;
import com.didichuxing.datachannel.arius.admin.common.bean.entity.app.ESUser;
import com.didichuxing.datachannel.arius.admin.common.constant.AdminConstant;
import com.didichuxing.datachannel.arius.admin.common.util.HttpRequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ V3 + "/project/es-user/" })
@Api(tags = "Project关联es user (REST)")
public class ProjectESUserV3Controller {
    
    @Autowired
    private ESUserManager esUserManager;
    
    @GetMapping("all")
    @ResponseBody
    @ApiOperation(value = "管理员获取所有项目的es user")
    public Result<List<ESUser>> listESUserByALLProject(HttpServletRequest request) {
        final String operator = HttpRequestUtils.getOperator(request);
        if (operator.equals(AdminConstant.SUPER_USER_NAME)) {
            return Result.buildFail("当前用户权限不足");
        }
        return esUserManager.listESUsersByAllProject();
    }
    
    @GetMapping("")
    @ResponseBody
    @ApiOperation(value = "获取项目下所有的es user")
    public Result<List<ESUser>> listESUserByProjectId(HttpServletRequest request) {
        return esUserManager.listESUsersByProjectId(HttpRequestUtils.getProjectId(request),
                HttpRequestUtils.getOperator(request));
    }
    
    @DeleteMapping("{esUser}")
    @ResponseBody
    @ApiOperation(value = "删除项目下指定的es user")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "esUser", value = "es user",
            required = true) })
    public Result<Void> deleteESUserByProject(HttpServletRequest request, @PathVariable("esUser") Integer esUserName) {
        return esUserManager.deleteESUserByProject(esUserName, HttpRequestUtils.getProjectId(request),
                HttpRequestUtils.getOperator(request));
    }
    
}