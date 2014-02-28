package xuzw.table2.test;

import java.io.File;

import xuzw.table2.implement.table._Table;
import xuzw.table2.interfaces.table.Table;

public class TableTest {

    public static void main(String[] args) throws Exception {
        System.out.println(getConfigTable());
    }

    public static Table<ConfigRow> getConfigTable() throws Exception {
        Table<ConfigRow> table = new _Table<ConfigRow>();
        File file = new File(TableTest.class.getResource("Config.tab").getFile());
        File file1 = new File(TableTest.class.getResource("ext/Config1.tab").getFile());
        table.parse(ConfigRow.class, file, file1);
        return table;
    }

}
