package org.lucas.application.auth.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.lucas.application.auth.assembler.AuthSettingAssembler;
import org.lucas.application.auth.dto.AuthConfigDTO;
import org.lucas.application.auth.dto.AuthSettingDTO;
import org.lucas.application.auth.dto.LoginMethodDTO;
import org.lucas.application.auth.dto.UpdateAuthSettingRequest;
import org.lucas.domain.auth.constant.AuthFeatureKey;
import org.lucas.domain.auth.constant.FeatureType;
import org.lucas.domain.auth.model.AuthSettingEntity;
import org.lucas.domain.auth.service.AuthSettingDomainService;

/** 认证配置应用服务 */
@Service
public class AuthSettingAppService {

    private final AuthSettingDomainService authSettingDomainService;

    public AuthSettingAppService(AuthSettingDomainService authSettingDomainService) {
        this.authSettingDomainService = authSettingDomainService;
    }

    /** 获取前端认证配置
     * 
     * @return 认证配置DTO */
    public AuthConfigDTO getAuthConfig() {
        // 获取启用的登录方式
        List<AuthSettingEntity> loginSettings = authSettingDomainService.getEnabledFeatures(FeatureType.LOGIN);

        Map<String, LoginMethodDTO> loginMethods = new HashMap<>();
        for (AuthSettingEntity setting : loginSettings) {
            LoginMethodDTO method = new LoginMethodDTO();
            method.setEnabled(setting.getEnabled());
            method.setName(setting.getFeatureName());

            loginMethods.put(setting.getFeatureKey(), method);
        }

        // 检查注册是否启用
        boolean registerEnabled = authSettingDomainService.isFeatureEnabled(AuthFeatureKey.USER_REGISTER);

        AuthConfigDTO config = new AuthConfigDTO();
        config.setLoginMethods(loginMethods);
        config.setRegisterEnabled(registerEnabled);

        return config;
    }

    /** 获取所有认证配置
     * 
     * @return 认证配置列表 */
    public List<AuthSettingDTO> getAllAuthSettings() {
        List<AuthSettingEntity> entities = authSettingDomainService.getAllAuthSettings();
        return AuthSettingAssembler.toDTOs(entities);
    }

    /** 根据ID获取认证配置
     * 
     * @param id 配置ID
     * @return 认证配置DTO */
    public AuthSettingDTO getAuthSettingById(String id) {
        AuthSettingEntity entity = authSettingDomainService.getById(id);
        return AuthSettingAssembler.toDTO(entity);
    }

    /** 切换认证配置启用状态
     * 
     * @param id 配置ID
     * @return 更新后的配置DTO */
    public AuthSettingDTO toggleAuthSetting(String id) {
        AuthSettingEntity entity = authSettingDomainService.toggleEnabled(id);
        return AuthSettingAssembler.toDTO(entity);
    }

    /** 更新认证配置
     * 
     * @param id 配置ID
     * @param request 更新请求
     * @return 更新后的配置DTO */
    public AuthSettingDTO updateAuthSetting(String id, UpdateAuthSettingRequest request) {
        AuthSettingEntity entity = authSettingDomainService.getById(id);
        AuthSettingEntity updatedEntity = AuthSettingAssembler.updateEntity(entity, request);
        AuthSettingEntity savedEntity = authSettingDomainService.updateAuthSetting(updatedEntity);
        return AuthSettingAssembler.toDTO(savedEntity);
    }

    /** 删除认证配置
     * 
     * @param id 配置ID */
    public void deleteAuthSetting(String id) {
        authSettingDomainService.deleteAuthSetting(id);
    }

}