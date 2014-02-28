package xuzw.table2.interfaces.column;

import xuzw.table2.exception.ColumnParseFailedException;

/**
 * 元组列
 * 
 * @param <ElementType>
 *            元素类型
 */
public interface TupleColumn<ElementType> {

    /**
     * 元素分隔符
     */
    static final String ELEMENT_SEPARATOR = ":";
    /**
     * order 1
     */
    static final int FIRST = 0;
    /**
     * order 2
     */
    static final int SECOND = 1;
    /**
     * order 3
     */
    static final int THIRD = 2;
    /**
     * order 4
     */
    static final int FOUR = 3;
    /**
     * order 5
     */
    static final int FIVE = 4;
    /**
     * order 6
     */
    static final int SIX = 5;
    /**
     * order 7
     */
    static final int SEVEN = 6;
    /**
     * order 8
     */
    static final int EIGHT = 7;
    /**
     * order 9
     */
    static final int NINE = 8;

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
    ElementType[] getAll();

    /**
     * 元素个数
     * 
     * @return
     */
    int size();

    /**
     * equals {@link #match(boolean, Object...) match(false, conditions)}
     * 
     * @param conditions
     * @return
     */
    boolean match(@SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * 是否匹配
     * 
     * @param reverse
     *            是否逆序
     * @param conditions
     * @return
     */
    boolean match(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * 解析元组列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @throws ColumnParseFailedException
     */
    void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException;
}
