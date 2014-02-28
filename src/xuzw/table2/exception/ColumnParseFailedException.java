package xuzw.table2.exception;

import xuzw.table2.utils.ColumnParser;

/**
 * 列解析失败异常
 * 
 */
public class ColumnParseFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public ColumnParseFailedException(ColumnParser.ColumnType columnType, Class<?> elementType, String columnName, String columnValue) {
        this(columnType, elementType, columnName, columnValue, null);
    }

    public ColumnParseFailedException(ColumnParser.ColumnType columnType, Class<?> elementType, String columnName, String columnValue, Throwable cause) {
        super(new StringBuffer("Can't Parse Column {") //
        .append("columnType: \"").append(columnType.toString()).append("\"") //
        .append(", elementType: \"").append(elementType.getName()).append("\"") //
        .append(", columnName: \"").append(columnName).append("\"") //
        .append(", columnValue: \"").append(columnValue).append("\"") //
        .append("}").toString(), cause);
    }
}
