package org.lucas.interfaces.api.portal.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.lucas.application.account.dto.AccountDTO;
import org.lucas.application.account.service.AccountAppService;
import org.lucas.infrastructure.auth.UserContext;
import org.lucas.interfaces.api.common.Result;

/** 账户管理控制层 提供用户账户管理的API接口 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountAppService accountAppService;

    public AccountController(AccountAppService accountAppService) {
        this.accountAppService = accountAppService;
    }

    /** 获取当前用户账户信息
     * 
     * @return 账户信息 */
    @GetMapping("/current")
    public Result<AccountDTO> getCurrentUserAccount() {
        String userId = UserContext.getCurrentUserId();
        AccountDTO account = accountAppService.getUserAccount(userId);
        return Result.success(account);
    }
}