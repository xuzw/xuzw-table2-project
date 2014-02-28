package xuzw.table2.exception;

import java.io.File;

import xuzw.table2.interfaces.table.TableHeader;

/**
 * 表头解析失败异常
 * 
 */
public class TableHeaderParseFailedException extends Exception {

    private static final long serialVersionUID = 1L;

    public TableHeaderParseFailedException(TableHeader header, File file) {
        this(header, file, null);
    }

    public TableHeaderParseFailedException(TableHeader header, File file, Throwable cause) {
        super(new StringBuffer("Can't Parse Table Header {") //
        .append("filePath: \"").append(file.getPath()).append("\"") //
        .append("}").toString(), cause);
    }
}
