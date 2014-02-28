package xuzw.table2.implement.column;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.interfaces.column.ListColumn;
import xuzw.table2.interfaces.column.TupleColumn;
import xuzw.table2.interfaces.column.TupleListColumn;
import xuzw.table2.utils.ColumnParser;

public class _TupleListColumn<ElementType> implements TupleListColumn<ElementType> {

    private List<TupleColumn<ElementType>> elements;

    public _TupleListColumn() {
    }

    public _TupleListColumn(List<TupleColumn<ElementType>> _elements) {
        elements = _elements;
    }

    @Override
    public TupleColumn<ElementType> get(int index) {
        return elements.get(index);
    }

    @Override
    public List<TupleColumn<ElementType>> getAll() {
        return elements;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public TupleColumn<ElementType> query(@SuppressWarnings("unchecked") ElementType... conditions) {
        return query(false, conditions);
    }

    @Override
    public TupleColumn<ElementType> query(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions) {
        for (TupleColumn<ElementType> element : elements) {
            if (element.match(reverse, conditions)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public List<TupleColumn<ElementType>> queryAll(@SuppressWarnings("unchecked") ElementType... conditions) {
        return queryAll(false, conditions);
    }

    @Override
    public List<TupleColumn<ElementType>> queryAll(boolean reverse, @SuppressWarnings("unchecked") ElementType... conditions) {
        List<TupleColumn<ElementType>> list = new ArrayList<TupleColumn<ElementType>>();
        for (TupleColumn<ElementType> element : elements) {
            if (element.match(reverse, conditions)) {
                list.add(element);
            }
        }
        return list;
    }

    @Override
    public List<ElementType> collect(int indexInTuple) {
        List<ElementType> list = new ArrayList<ElementType>();
        for (TupleColumn<ElementType> element : elements) {
            list.add(element.get(indexInTuple));
        }
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public long sum(int indexInTuple) {
        long total = 0;
        List<Integer> list = (List<Integer>) collect(indexInTuple);
        for (Integer integer : list) {
            total += integer;
        }
        return total;
    }

    @Override
    public void parse(String columnName, String columnValue, Class<ElementType> elementType) throws ColumnParseFailedException {
        elements = ColumnParser.parseTupleListColumn(columnName, columnValue, elementType).getAll();
    }

    @Override
    public String toString() {
        return "TupleList[" + StringUtils.join(elements, ListColumn.ELEMENT_SEPARATOR) + "]";
    }
}
