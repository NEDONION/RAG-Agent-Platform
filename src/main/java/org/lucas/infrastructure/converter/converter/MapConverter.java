package org.lucas.infrastructure.converter.converter;

import java.util.Map;
import org.apache.ibatis.type.MappedTypes;

/** Map对象JSON转换器 */
@MappedTypes(Map.class)
public class MapConverter extends JsonToStringConverter<Map> {

    public MapConverter() {
        super(Map.class);
    }
}