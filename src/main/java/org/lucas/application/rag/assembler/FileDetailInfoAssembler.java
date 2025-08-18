package org.lucas.application.rag.assembler;

import org.springframework.beans.BeanUtils;
import org.lucas.application.rag.dto.FileDetailInfoDTO;
import org.lucas.domain.rag.model.FileDetailEntity;

/** 文件详细信息转换器
 * 
 * @author shilong.zang */
public class FileDetailInfoAssembler {

    /** Convert FileDetailEntity to FileDetailInfoDTO */
    public static FileDetailInfoDTO toDTO(FileDetailEntity entity) {
        if (entity == null) {
            return null;
        }
        FileDetailInfoDTO dto = new FileDetailInfoDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setFileId(entity.getId());
        return dto;
    }
}