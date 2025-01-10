package com.vn.education.utils.common.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColumnMetadata {

    private String columnName;

    private String dataType;

    private int columnSize;

    private boolean isNullable;

    private boolean isPrimaryKey;
}
