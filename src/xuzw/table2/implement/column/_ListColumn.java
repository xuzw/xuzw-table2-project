package xuzw.table2.implement.column;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.interfaces.column.ListColumn;
import xuzw.table2.utils.ColumnParser;

public class _ListColumn<ElementType> implements ListColumn<ElementType> {

    private List<ElementType> elements;

    public _ListColumn() {
    }

    public _ListColumn(List<ElementType> _elements) {
        elements = _elements;
    }

    @Override
    public ElementType get(int index) {
        return elements.get(index);
    }

    @Override
    public List<ElementType> getAll() {
        return elements;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        elements = ColumnParser.parseListColumn(columnName, columnValue, elementType).getAll();
    }

    @Override
    public String toString() {
        return "List[" + StringUtils.join(elements, ListColumn.ELEMENT_SEPARATOR) + "]";
    }
}
