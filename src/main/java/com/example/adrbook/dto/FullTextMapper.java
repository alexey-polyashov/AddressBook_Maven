package com.example.adrbook.dto;

import com.example.adrbook.entity.FullTextIndex;
import com.example.adrbook.utility.DataType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DataType.class})
public interface FullTextMapper {
    @Mapping(target="objectTypePresentation", expression = "java(source.getDataType().getPresentation())")
    @Mapping(target="dataType", expression = "java(source.getDataType().ordinal())")
    FullTextResult FullTextIndexToResult(FullTextIndex source);
}
