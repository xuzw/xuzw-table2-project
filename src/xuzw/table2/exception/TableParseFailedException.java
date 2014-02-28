package xuzw.table2.exception;

import java.io.File;

import xuzw.table2.interfaces.table.Table;

/**
 * 表解析失败异常
 * 
 */
public class TableParseFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public TableParseFailedException(Table<?> table, File file) {
        this(table, file, null);
    }

    public TableParseFailedException(Table<?> table, File file, Throwable cause) {
        super(new StringBuffer("Can't Parse Table {") //
        .append("filePath: \"").append(file.getPath()).append("\"") //
        .append(", tableShortName: \"").append(table.getName()).append("\"") //
        .append("}").toString(), cause);
    }
}
