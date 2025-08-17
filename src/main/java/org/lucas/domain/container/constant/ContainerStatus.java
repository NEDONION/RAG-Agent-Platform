package org.lucas.domain.container.constant;

/** 容器状态枚举 */
public enum ContainerStatus {
    /** 创建中 */
    CREATING(1, "Creating"),
    /** 运行中 */
    RUNNING(2, "Running"),
    /** 已停止 */
    STOPPED(3, "Stopped"),
    /** 错误状态 */
    ERROR(4, "In Error"),
    /** 删除中 */
    DELETING(5, "Deleting"),
    /** 已删除 */
    DELETED(6, "Deleted"),
    /** 已暂停 */
    SUSPENDED(7, "Suspended 已暂停");

    private final Integer code;
    private final String description;

    ContainerStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ContainerStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ContainerStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown container status code: " + code);
    }
}