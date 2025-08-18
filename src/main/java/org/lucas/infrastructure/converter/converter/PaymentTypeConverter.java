package org.lucas.infrastructure.converter.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.lucas.domain.order.constant.PaymentType;

/** 支付类型枚举转换器 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(PaymentType.class)
public class PaymentTypeConverter extends BaseTypeHandler<PaymentType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PaymentType parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public PaymentType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String code = rs.getString(columnName);
        return PaymentType.fromCode(code);
    }

    @Override
    public PaymentType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String code = rs.getString(columnIndex);
        return PaymentType.fromCode(code);
    }

    @Override
    public PaymentType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String code = cs.getString(columnIndex);
        return PaymentType.fromCode(code);
    }
}