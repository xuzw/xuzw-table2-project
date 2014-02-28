package xuzw.table2.interfaces.table;

import java.io.File;
import java.util.Map;
import java.util.Set;

import xuzw.table2.exception.TableHeaderParseFailedException;

/**
 * 表头
 * 
 */
public interface TableHeader {

    /**
     * 查询Id列列名
     * 
     * @return
     */
    String getIdColumnName();

    /**
     * 查询全部列名
     * 
     * @return
     */
    Set<String> getAllColumnNames();

    /**
     * 查询列名与列类型映射
     * 
     * @return
     */
    Map<String, String> getColumnNameMaptoType();

    /**
     * 查询列名与列描述映射
     * 
     * @return
     */
    Map<String, String> getColumnNameMaptoDesc();

    /**
     * 查询列数
     * 
     * @return
     */
    int getColumnSize();

    /**
     * 解析.tab文件
     * 
     * @param file
     * @throws TableHeaderParseFailedException
     */
    void parse(File file) throws TableHeaderParseFailedException;
}
