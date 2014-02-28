package xuzw.table2.test;

import xuzw.table2.implement.container._TableContainer;
import xuzw.table2.interfaces.container.TableContainer;

public class TableContainerTest {

    public static void main(String[] args) throws Exception {
        TableContainer tableContainer = new _TableContainer();
        tableContainer.addNamedTable("share/config", TableTest.getConfigTable());
        System.out.println(tableContainer);
        System.out.println(tableContainer.getSubContainer("share"));
        System.out.println(tableContainer.get(ConfigRow.class));
    }

}
