package xuzw.table2.implement.container;

import xuzw.table2.interfaces.container.HierarchyName;

public class HierarchyNameBuilder {

    public static HierarchyName build(String hierarchyName) {
        return new _HierarchyName(hierarchyName);
    }

}
