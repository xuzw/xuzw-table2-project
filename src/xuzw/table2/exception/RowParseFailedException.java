package xuzw.table2.exception;

import xuzw.table2.interfaces.Row;

/**
 * 行解析失败异常
 * 
 */
public class RowParseFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public RowParseFailedException(Row row, int rowNumber) {
        this(row, rowNumber, null);
    }

    public RowParseFailedException(Row row, int rowNumber, Throwable cause) {
        super(new StringBuffer("Can't Parse Row {") //
        .append("rowNumber: \"").append(rowNumber).append("\"") //
        .append(", rowClass: \"").append(row.getClass().getName()).append("\"") //
        .append(", rowId: \"").append(row.getId()).append("\"") //
        .append("}").toString(), cause);
    }
}
