package xuzw.table2.interfaces;

import java.util.Map;

import xuzw.table2.exception.RowParseFailedException;
import xuzw.table2.interfaces.table.Table;

/**
 * 行
 * 
 */
public interface Row {

    /**
     * 查询表
     * 
     * @return
     */
    Table<?> getTable();

    /**
     * 设置表
     * 
     * @param table
     */
    void setTable(Table<?> table);

    /**
     * 查询Id
     * 
     * @return
     */
    int getId();

    /**
     * 解析行
     * 
     * @param rowNumber
     *            行号
     * @param columnNameMaptoValue
     *            列名映射至列值
     * @throws RowParseFailedException
     */
    void parse(int rowNumber, Map<String, String> columnNameMaptoValue) throws RowParseFailedException;
}
