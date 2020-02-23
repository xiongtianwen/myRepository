package com.xtw.rmi.rpc;

import java.io.Serializable;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/23 20:02
 */
public class RpcRequestModel implements Serializable {

    private static final long serialVersionUID = -8836332887614117897L;
    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] args;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
