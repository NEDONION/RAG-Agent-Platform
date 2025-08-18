package org.lucas.infrastructure.converter;

import java.util.List;
import org.apache.ibatis.type.MappedTypes;

/** 字符串列表JSON转换器 */
@MappedTypes(List.class)
public class ListStringConverter extends JsonToStringConverter<List<String>> {

    public ListStringConverter() {
        super((Class<List<String>>) (Class<?>) List.class);
    }
}