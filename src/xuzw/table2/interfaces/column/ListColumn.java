package xuzw.table2.interfaces.column;

import java.util.List;

import xuzw.table2.exception.ColumnParseFailedException;

/**
 * 列表列
 * 
 * @param <ElementType>
 *            元素类型
 */
public interface ListColumn<ElementType> {

    /**
     * 元素分隔符
     */
    static final String ELEMENT_SEPARATOR = ",";

    /**
     * 查询, index从0开始
     * 
     * @param index
     * @return
     */
    ElementType get(int index);

    /**
     * 查询全部
     * 
     * @return
     */
    List<ElementType> getAll();

    /**
     * 元素个数
     * 
     * @return
     */
    int size();

    /**
     * 解析列表列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @throws ColumnParseFailedException
     */
    void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException;
}
