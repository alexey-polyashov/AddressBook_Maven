package com.example.adrbook.utility;


import java.util.stream.Stream;

public enum DataType {
    DEPARTMENT("DEP", "Подразделение"), PERSON("P", "Сотрудник");

    private String code;
    private String presentation;

    private DataType(String code, String presentation){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getPresentation() {
        return presentation;
    }

    public static DataType of(String code){
        return Stream.of(DataType.values())
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
