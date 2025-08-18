package org.lucas.domain.rag.straegy.context;

import jakarta.annotation.Resource;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.lucas.domain.rag.model.enums.RagDocSyncOcrEnum;
import org.lucas.domain.rag.straegy.RagDocSyncOcrStrategy;

/** @author shilong.zang
 * @date 09:39 <br/>
 */
@Service
public class RagDocSyncOcrContext {

    @Resource
    private Map<String, RagDocSyncOcrStrategy> taskExportStrategyMap;

    public RagDocSyncOcrStrategy getTaskExportStrategy(String strategy) {
        return taskExportStrategyMap.get(RagDocSyncOcrEnum.getLabelByValue(strategy));
    }
}
