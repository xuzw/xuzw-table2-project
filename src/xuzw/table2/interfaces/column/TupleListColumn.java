package xuzw.table2.interfaces.column;

import java.util.List;

import xuzw.table2.exception.ColumnParseFailedException;

/**
 * 元组列表列
 * 
 * @param <ElementType>
 *            元素类型
 */
public interface TupleListColumn<ElementType> {

    /**
     * 查询, index从0开始
     * 
     * @param index
     * @return
     */
    TupleColumn<ElementType> get(int index);

    /**
     * 查询全部
     * 
     * @return
     */
    List<TupleColumn<ElementType>> getAll();

    /**
     * 元组个数
     * 
     * @return
     */
    int size();

    /**
     * equals {@link #query(boolean, Object...) query(false, conditions)}
     * 
     * @param conditions
     * @return
     */
    TupleColumn<ElementType> query(@SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * 查询
     * 
     * @param reverse
     *            是否逆序
     * @param conditions
     * @return
     */
    TupleColumn<ElementType> query(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * equals {@link #queryAll(boolean, Object...) queryAll(false, conditions)}
     * 
     * @param conditions
     * @return
     */
    List<TupleColumn<ElementType>> queryAll(@SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * 查询全部
     * 
     * @param reverse
     *            是否逆序
     * @param conditions
     * @return
     */
    List<TupleColumn<ElementType>> queryAll(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions);

    /**
     * 收集元组中的元素
     * 
     * @param indexInTuple
     * @return
     */
    List<ElementType> collect(int indexInTuple);

    /**
     * 对元组中的元素求和, 该方法假定元素类型为{@link Integer}
     * 
     * @param indexInTuple
     * @return
     */
    long sum(int indexInTuple);

    /**
     * 解析元组列表列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @throws ColumnParseFailedException
     */
    void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException;
}
