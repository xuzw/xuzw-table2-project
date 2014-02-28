package xuzw.table2.interfaces.table;

import java.io.File;
import java.util.List;

import xuzw.table2.exception.TableHeaderParseFailedException;
import xuzw.table2.exception.TableParseFailedException;
import xuzw.table2.interfaces.Row;

/**
 * 表
 * 
 * @param <RowType>
 */
public interface Table<RowType extends Row> {

    /**
     * 表文件名的后缀名
     */
    static final String TABLE_FILE_NAME_SUFFIX = ".tab";

    /**
     * 查询名字
     * 
     * @return
     */
    String getName();

    /**
     * 是否包含指定id的行
     * 
     * @param id
     * @return
     */
    boolean has(int id);

    /**
     * 查询行
     * 
     * @param id
     * @return
     */
    RowType get(int id);

    /**
     * 查询行数
     * 
     * @return
     */
    int size();

    /**
     * 查询全部
     * 
     * @return
     */
    List<RowType> values();

    /**
     * 查询行类型
     * 
     * @return
     */
    Class<RowType> getRowClass();

    /**
     * 查询表头
     * 
     * @return
     */
    TableHeader getTableHeader();

    /**
     * 是否为有效.tab文件
     * 
     * @param file
     * @return
     */
    boolean isValidFile(File file);

    /**
     * 是否为有效.tab多文件
     * 
     * @param files
     * @return
     */
    boolean isValidFiles(File... files);

    /**
     * 解析.tab单文件、或多文件
     * 
     * @param rowClass
     * @param files
     * @throws TableParseFailedException
     * @throws TableHeaderParseFailedException
     */
    void parse(Class<RowType> rowClass, File... files) throws TableParseFailedException, TableHeaderParseFailedException;
}
