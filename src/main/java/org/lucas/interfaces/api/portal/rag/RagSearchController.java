package org.lucas.interfaces.api.portal.rag;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.lucas.application.rag.dto.DocumentUnitDTO;
import org.lucas.application.rag.dto.RagSearchRequest;
import org.lucas.application.rag.dto.RagStreamChatRequest;
import org.lucas.application.rag.service.RagQaDatasetAppService;
import org.lucas.infrastructure.auth.UserContext;
import org.lucas.interfaces.api.common.Result;

/** RAG搜索控制器
 * @author shilong.zang
 * @date 2024-12-09 */
@RestController
@RequestMapping("/rag/search")
public class RagSearchController {

    private final RagQaDatasetAppService ragQaDatasetAppService;

    public RagSearchController(RagQaDatasetAppService ragQaDatasetAppService) {
        this.ragQaDatasetAppService = ragQaDatasetAppService;
    }

    /** RAG搜索文档
     * 
     * @param request RAG搜索请求
     * @return 搜索结果 */
    @PostMapping
    public Result<List<DocumentUnitDTO>> ragSearch(@RequestBody @Validated RagSearchRequest request) {
        String userId = UserContext.getCurrentUserId();
        List<DocumentUnitDTO> searchResults = ragQaDatasetAppService.ragSearch(request, userId);
        return Result.success(searchResults);
    }

    /** RAG流式问答
     * 
     * @param request 流式问答请求
     * @return 流式响应 */
    @PostMapping("/stream-chat")
    public SseEmitter ragStreamChat(@RequestBody @Validated RagStreamChatRequest request) {
        String userId = UserContext.getCurrentUserId();
        return ragQaDatasetAppService.ragStreamChat(request, userId);
    }

    /** 基于已安装知识库的RAG搜索
     * 
     * @param userRagId 已安装的知识库ID
     * @param request RAG搜索请求
     * @return 搜索结果 */
    @PostMapping("/user-rag/{userRagId}")
    public Result<List<DocumentUnitDTO>> ragSearchByUserRag(@PathVariable String userRagId,
            @RequestBody @Validated RagSearchRequest request) {
        String userId = UserContext.getCurrentUserId();
        List<DocumentUnitDTO> searchResults = ragQaDatasetAppService.ragSearchByUserRag(request, userRagId, userId);
        return Result.success(searchResults);
    }

    /** 基于已安装知识库的RAG流式问答
     * 
     * @param userRagId 已安装的知识库ID
     * @param request 流式问答请求
     * @return 流式响应 */
    @PostMapping("/user-rag/{userRagId}/stream-chat")
    public SseEmitter ragStreamChatByUserRag(@PathVariable String userRagId,
            @RequestBody @Validated RagStreamChatRequest request) {
        String userId = UserContext.getCurrentUserId();
        return ragQaDatasetAppService.ragStreamChatByUserRag(request, userRagId, userId);
    }

}