package com.netcommon.exception;

/**
* 统一提示
* @author bing
* @create 2018/1/26
* @version 1.0
**/
public enum ErrorMsg {
    SYSTEM_ERROR(500,"系统异常"),
    PARAM_ERROR(201,"入参数有误"),
    NON_DATA(302,"没有返回值"),
    ROLE_MODEL_ERROR(201,"角色模型不可以为null"),
    OPERATOR_ERROR(201,"操作人不可以为空，为null"),
    ROLEID_ERROR(201,"角色ID不可以为空，为null"),

    ROLEID_FIND_ERROR(201,"根据角色名称，组织业务ID，组织业务类型获取角色没有获取到"),
    ROLEID_SOME_FIND_ERROR(201,"根据角色Id，组织业务ID，组织业务类型获取角色有部分没有获取到"),
    ROLEID_ISNOTSET_ERROR(201,"根据角色名称，组织业务ID，组织业务类型获取到了角色ID"),
    USERID_ERROR(201,"用户ID不可以为空，为null"),
    DELETE_ROLE_ERROR(201,"请删除这个角色与用户的关联关系"),
    ROLEID_LIST_ERROR(201,"角色ID列表不可以为空，为null"),
    USERID_LIST_ERROR(201,"用户ID列表不可以为空，为null"),
    MAKE_ROLEID_ERROR(201,"主键生成器生成角色ID失败"),
    MAKE_PRIVID_ERROR(201,"主键生成器生成权限ID失败"),
    ORG_BIZ_TYPE_ERROR(201,"组织业务类型不可以为空，为null"),
    ORG_BIZ_ID_ERROR(201,"组织业务id不可以为空，为null"),
    OLD_ROLE_ID_ERROR(201,"旧角色Id不可以为空，为null"),
    NEW_ROLE_NAME_ERROR(201,"新角色名称不可以为空，为null"),
    ROLE_NAME_ERROR(201,"角色名称不可以为空，为null"),
    ROLE_DESC_ERROR(201,"角色描述不可以为空，为null"),
    OPERATIONLOG_OPERATORNAME(201,"查询操作日志接口入参缺少操作人"),
    PRIVDESC_ERROR(201,"权限描述不可以为空，为null"),
    PROVILEGE_MODEL_ERROR(201,"权限模型不可以为空，为null"),
    PROVILEGE_MODEL_COUNT_ERROR(201,"权限模型数量不可以超过100"),
    PRIVCODE_ERROR(201,"权限编码不可以为空，为null"),
    PRIVCODE_ISSET_ERROR(201,"权限编码在数据库中不存在"),
    FIND_ROLEID_ERROR(201,"查不到角色"),
    FIND_USERID_ERROR(201,"查不到用户"),
    PRIVID_ERROR(201,"权限ID不可以为空，为null"),
    PRIV_CODE_ERROR(201,"权限编码不可以为空，为null"),
    PRIVID_NOT_EXISTS_ERROR(201,"权限Id不存在"),
    ISSET_PRIVCODE_ERROR(201,"权限编码已存在"),
    PRIVTYPE_ERROR(201,"权限类型不可以为空，为null"),
    PRIVTYPE_MESSAGE_ERROR(201,"权限类型错误，或不存在"),
    PRIVTITLE_ERROR(201,"权限简称不可以为空，为null"),
    MAKE_PROVID_ERROR(201,"主键生成器生成角色ID失败"),
    PARENT_PROVID_ERROR(201,"父权限ID不存在"),
    PARENT_PROVCODE_ERROR(201,"父权限CODE不存在"),
    ORGBIZID_ERROR(201,"组织业务id不可以为空，为null"),
    PRIVILEGESEARCHMODEL_ERROR(201,"权限查询模型不可以为null"),
    PRIVILEGERELATIONPARAM_ERROR(201,"权限关系表查询入参不可以为null"),

    PRIVILEGEUPDATEPARAM_ERROR(201,"权限更新入参不可以为null"),
    ROLECREATEPARAM_ERROR(201,"创建角色入参不可以为null"),
    ROLEUPDATEPARAM_ERROR(201,"更新角色入参不可以为null"),
    ROLEDELETEPARAM_ERROR(201,"权限关系表查询入参不可以为null"),
    ADDROLESTOUSERPARAM_ERROR(201,"为用户添加角色入参不可以为null"),
    ADDROLETOUSERSPARAM_ERROR(201,"为角色添加用户入参不可以为null"),
    ORGBIZTYPE_ERROR(201,"组织业务类型不可以为空，为null"),
    ORGID_FIND_ERROR(201,"根据入参查询组织ID，组织ID找不到"),
    PRIVCODE_FIND_ERROR(201,"根据子权限编码查询不到权限ID"),
    PRIVCODE_SET_ERROR(201,"子权限编码已存在"),
    PARENT_PRIVCODE_FIND_ERROR(201,"根据父权限编码查询不到权限ID"),
    BATCH_DELETE_PRIVILEGE_ERROR(201,"删除的权限中有存在多个权限父子关系"),
    BATCH_DELETE_PRIVILEGE_ROLE_ERROR(201,"删除的权限中有存在多个权限与角色的关联关系"),
	ORG_ID_ERROR(201, "orgId不合法"),
    PRIVILEGE_ID_ERROR(201, "privilegeId不合法"),
    PRIVILEGE_ID_LIST_ERROR(201, "privilegeIdList不合法"),
    ORG_ID_ORG_TYPE_ERROR(201, "组织业务ID或组织业务类型不合法"),
    USER_ID_ERROR(201, "userId不合法"),
    USER_ID_LIST_ERROR(201, "userIdList不合法"),
    ROLE_ID_LIST_ERROR(201, "roleIdList不合法"),
    ROLE_ID_ERROR(201, "角色Id不合法"),
    PARENT_CHILD_ID_NONE(201,"父子id都为必填数据"),
    ORG_BIZ_TYPE_NONE(201,"组织类型为必填数据"),
    PRIVILEGE_CIRCLE(201,"已构成环形数据不合法（子权限集中包含了父集权限）"),
    PRIVILEGE_EXIST(201,"父子权限已存在"),
    PRIVILEGE_TYPE_NULL(201,"权限类型不为空"),
    NON_FUNCTION_PRIVILEGE(302,"该店铺管理员没有功能权限，请联系sys_admin工作人员"),
    ;
    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    ErrorMsg(int errorCode, String errorMsg) {

        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
