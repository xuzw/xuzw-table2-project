package xuzw.table2.implement.column;

import org.apache.commons.lang.StringUtils;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.interfaces.column.TupleColumn;
import xuzw.table2.utils.ColumnParser;

public class _TupleColumn<ElementType> implements TupleColumn<ElementType> {

    private ElementType[] elements;

    public _TupleColumn() {
    }

    public _TupleColumn(ElementType[] _elements) {
        elements = _elements;
    }

    @Override
    public ElementType get(int index) {
        return elements[index];
    }

    @Override
    public ElementType[] getAll() {
        return elements;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean match(@SuppressWarnings("unchecked") ElementType... conditions) {
        return match(false, conditions);
    }

    @Override
    public boolean match(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions) {
        int elementCount = (elements == null) ? 0 : elements.length;
        if (elementCount == 0) {
            return true;
        }
        int conditionCount = (conditions == null) ? 0 : conditions.length;
        if (conditionCount == 0) {
            return true;
        }
        int loopCount = Math.min(elementCount, conditionCount);
        for (int i = 0; i < loopCount; i++) {
            ElementType condition = conditions[i];
            if (condition == null) { // 跳过该位置
                continue;
            }
            ElementType element = reverse ? elements[elementCount - i - 1] : elements[i];
            if (element == null || !element.equals(condition)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        elements = ColumnParser.parseTupleColumn(columnName, columnValue, elementType).getAll();
    }

    @Override
    public String toString() {
        return "Tuple[" + StringUtils.join(elements, TupleColumn.ELEMENT_SEPARATOR) + "]";
    }
}
