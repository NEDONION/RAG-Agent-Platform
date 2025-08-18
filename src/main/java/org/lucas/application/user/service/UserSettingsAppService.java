package org.lucas.application.user.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.lucas.application.user.assembler.UserSettingsAssembler;
import org.lucas.application.user.dto.UserSettingsDTO;
import org.lucas.domain.user.model.UserSettingsEntity;
import org.lucas.domain.user.model.config.FallbackConfig;
import org.lucas.domain.user.service.UserSettingsDomainService;
import org.lucas.interfaces.dto.user.request.UserSettingsUpdateRequest;

/** 用户设置应用服务 */
@Service
public class UserSettingsAppService {

    private final UserSettingsDomainService userSettingsDomainService;

    public UserSettingsAppService(UserSettingsDomainService userSettingsDomainService) {
        this.userSettingsDomainService = userSettingsDomainService;
    }

    /** 获取用户设置
     * @param userId 用户ID
     * @return 用户设置DTO */
    public UserSettingsDTO getUserSettings(String userId) {
        UserSettingsEntity entity = userSettingsDomainService.getUserSettings(userId);
        return UserSettingsAssembler.toDTO(entity);
    }

    /** 更新用户设置
     * @param request 更新请求
     * @param userId 用户ID
     * @return 更新后的用户设置DTO */
    public UserSettingsDTO updateUserSettings(UserSettingsUpdateRequest request, String userId) {
        UserSettingsEntity entity = UserSettingsAssembler.toEntity(request, userId);
        userSettingsDomainService.update(entity);

        return UserSettingsAssembler.toDTO(entity);
    }

    /** 获取用户默认模型ID
     * @param userId 用户ID
     * @return 默认模型ID */
    public String getUserDefaultModelId(String userId) {
        return userSettingsDomainService.getUserDefaultModelId(userId);
    }

    /** 获取用户降级链配置
     * @param userId 用户ID
     * @return 降级模型ID列表，如果未启用降级则返回null */
    public List<String> getUserFallbackChain(String userId) {
        UserSettingsEntity settings = userSettingsDomainService.getUserSettings(userId);
        if (settings == null || settings.getSettingConfig() == null) {
            return null;
        }

        FallbackConfig fallbackConfig = settings.getSettingConfig().getFallbackConfig();
        if (fallbackConfig == null || !fallbackConfig.isEnabled() || fallbackConfig.getFallbackChain().isEmpty()) {
            return null;
        }

        return fallbackConfig.getFallbackChain();
    }
}