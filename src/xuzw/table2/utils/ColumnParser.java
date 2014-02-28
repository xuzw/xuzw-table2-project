package xuzw.table2.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.implement.column._ListColumn;
import xuzw.table2.implement.column._TupleColumn;
import xuzw.table2.implement.column._TupleListColumn;
import xuzw.table2.interfaces.column.ListColumn;
import xuzw.table2.interfaces.column.TupleColumn;
import xuzw.table2.interfaces.column.TupleListColumn;

/**
 * 列解析器
 * 
 */
public class ColumnParser {

    /**
     * 列类型
     * 
     */
    public enum ColumnType {
        /**
         * 简单列
         */
        SIMPLE,
        /**
         * 元组列
         */
        TUPLE,
        /**
         * 列表列
         */
        LIST,
        /**
         * 列表元组列
         */
        TUPLE_LIST;
    }

    /**
     * 解析int, 默认值为0
     * 
     * @param rawString
     * @return
     */
    public static Integer parseInt(String rawString) {
        String value = parseString(rawString);
        return value.trim().length() == 0 ? 0 : new Integer(value);
    }

    /**
     * 解析bool
     * 
     * @param rawString
     * @return
     */
    public static Boolean parseBool(String rawString) {
        return parseInt(rawString) == 1;
    }

    /**
     * 解析string, 忽略注释(#号和它之后的字符), 确保不为null
     * 
     * @param rawString
     * @return
     */
    public static String parseString(String rawString) {
        return (rawString == null) ? "" : rawString.replaceFirst("#.*$", "");
    }

    /**
     * 解析简单列
     * 
     * @param columnName
     * @param columnNameMaptoValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> ElementType parseSimpleColumn(String columnName, Map<String, String> columnNameMaptoValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        String columnValue = columnNameMaptoValue.get(columnName);
        return parseSimpleColumn(columnName, columnValue, elementType);
    }

    /**
     * 解析简单列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    @SuppressWarnings("unchecked")
    public static <ElementType> ElementType parseSimpleColumn(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        try {
            if (elementType.equals(Integer.class)) {
                return (ElementType) parseInt(columnValue);
            } else if (elementType.equals(Boolean.class)) {
                return (ElementType) parseBool(columnValue);
            } else if (elementType.equals(String.class)) {
                return (ElementType) parseString(columnValue);
            } else {
                throw new Exception("Not support element type " + elementType.getName());
            }
        } catch (Exception e) {
            throw new ColumnParseFailedException(ColumnType.SIMPLE, elementType, columnName, columnValue, e);
        }

    }

    /**
     * 解析元组列
     * 
     * @param columnName
     * @param columnNameMaptoValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> TupleColumn<ElementType> parseTupleColumn(String columnName, Map<String, String> columnNameMaptoValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        String columnValue = columnNameMaptoValue.get(columnName);
        return parseTupleColumn(columnName, columnValue, elementType);
    }

    /**
     * 解析元组列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    @SuppressWarnings("unchecked")
    public static <ElementType> TupleColumn<ElementType> parseTupleColumn(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        try {
            String[] values = null;
            int elementCount = 0;
            if (StringUtils.isNotBlank(columnValue)) {
                values = columnValue.split("[" + TupleColumn.ELEMENT_SEPARATOR + "]");
                elementCount = values.length;
            }
            ElementType[] elements = (ElementType[]) Array.newInstance(elementType, elementCount);
            for (int i = 0; i < elementCount; i++) {
                elements[i] = parseSimpleColumn(null, values[i], elementType);
            }
            return new _TupleColumn<ElementType>(elements);
        } catch (Exception e) {
            throw new ColumnParseFailedException(ColumnType.TUPLE, elementType, columnName, columnValue, e);
        }
    }

    /**
     * 解析列表列
     * 
     * @param columnName
     * @param columnNameMaptoValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> ListColumn<ElementType> parseListColumn(String columnName, Map<String, String> columnNameMaptoValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        String columnValue = columnNameMaptoValue.get(columnName);
        return parseListColumn(columnName, columnValue, elementType);
    }

    /**
     * 解析列表列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> ListColumn<ElementType> parseListColumn(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        try {
            List<ElementType> elements = new ArrayList<ElementType>();
            if (StringUtils.isNotBlank(columnValue)) {
                for (String value : columnValue.split("[" + ListColumn.ELEMENT_SEPARATOR + "]")) {
                    elements.add(parseSimpleColumn(null, value, elementType));
                }
            }
            return new _ListColumn<ElementType>(elements);
        } catch (Exception e) {
            throw new ColumnParseFailedException(ColumnType.LIST, elementType, columnName, columnValue, e);
        }
    }

    /**
     * 解析元组列表列
     * 
     * @param columnName
     * @param columnNameMaptoValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> TupleListColumn<ElementType> parseTupleListColumn(String columnName, Map<String, String> columnNameMaptoValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        String columnValue = columnNameMaptoValue.get(columnName);
        return parseTupleListColumn(columnName, columnValue, elementType);
    }

    /**
     * 解析元组列表列
     * 
     * @param columnName
     * @param columnValue
     * @param elementType
     * @return
     * @throws ColumnParseFailedException
     */
    public static <ElementType> TupleListColumn<ElementType> parseTupleListColumn(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        try {
            List<TupleColumn<ElementType>> elements = new ArrayList<TupleColumn<ElementType>>();
            if (StringUtils.isNotBlank(columnValue)) {
                for (String value : columnValue.split("[" + ListColumn.ELEMENT_SEPARATOR + "]")) {
                    elements.add(parseTupleColumn(null, value, elementType));
                }
            }
            return new _TupleListColumn<ElementType>(elements);
        } catch (Exception e) {
            throw new ColumnParseFailedException(ColumnType.TUPLE_LIST, elementType, columnName, columnValue, e);
        }
    }

}
