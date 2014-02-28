package xuzw.table2.implement;

import java.util.Map;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.exception.RowParseFailedException;
import xuzw.table2.interfaces.Row;
import xuzw.table2.interfaces.table.Table;
import xuzw.table2.utils.ColumnParser;

public abstract class AbstractRow implements Row {

    private Table<?> table;
    private int id;

    @Override
    public Table<?> getTable() {
        return table;
    }

    @Override
    public void setTable(Table<?> table) {
        this.table = table;
    }

    @Override
    public int getId() {
        return id;
    }

    /**
     * 解析本行
     * 
     * @param map
     *            列名映射到列值 columnNameMaptoValue
     * @throws ColumnParseFailedException
     */
    public abstract void parseRow(Map<String, String> map) throws ColumnParseFailedException;

    @Override
    public final void parse(int rowNumber, Map<String, String> columnNameMaptoValue) throws RowParseFailedException {
        try {
            String idColumnName = table.getTableHeader().getIdColumnName();
            id = ColumnParser.parseSimpleColumn(idColumnName, columnNameMaptoValue, Integer.class);
            parseRow(columnNameMaptoValue);
        } catch (Exception e) {
            throw new RowParseFailedException(this, rowNumber, e);
        }
    }

}
