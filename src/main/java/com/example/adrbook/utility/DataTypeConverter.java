package com.example.adrbook.utility;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DataTypeConverter implements AttributeConverter<DataType, String> {
    @Override
    public String convertToDatabaseColumn(DataType dataType) {
        if (dataType == null) {
            return null;
        }
        return dataType.getCode();
    }

    @Override
    public DataType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return DataType.of(code);
    }
}
