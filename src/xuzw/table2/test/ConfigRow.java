package xuzw.table2.test;

import java.util.Map;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.implement.AbstractRow;
import xuzw.table2.utils.ColumnParser;

public class ConfigRow extends AbstractRow {

    private String value;

    @Override
    public void parseRow(Map<String, String> map) throws ColumnParseFailedException {
        value = ColumnParser.parseSimpleColumn("ConfigValue", map, String.class);
    }

    public String getValue() {
        return value;
    }

}
