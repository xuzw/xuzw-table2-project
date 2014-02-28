package xuzw.table2.implement.container;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import xuzw.table2.interfaces.Row;
import xuzw.table2.interfaces.container.HierarchyName;
import xuzw.table2.interfaces.container.TableContainer;
import xuzw.table2.interfaces.table.Table;

public class _TableContainer implements TableContainer {

    private String name;
    private TableContainer owner;
    private Map<Class<? extends Row>, Table<? extends Row>> rowTypeMaptoAnonymousTable = new HashMap<Class<? extends Row>, Table<? extends Row>>();
    private Map<String, Table<? extends Row>> nameMaptoTable = new HashMap<String, Table<? extends Row>>();
    private Map<String, TableContainer> nameMaptoContainer = new HashMap<String, TableContainer>();

    public _TableContainer() {
    }

    public _TableContainer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public TableContainer getOwner() {
        return owner;
    }

    @Override
    public void setOwner(TableContainer tableContainer) {
        this.owner = tableContainer;
    }

    @Override
    public <RowType extends Row> boolean hasAnonymousTable(Class<RowType> rowType) {
        return rowTypeMaptoAnonymousTable.containsKey(rowType);
    }

    @Override
    public <RowType extends Row> boolean has(Class<RowType> rowType) {
        return has(rowType, true);
    }

    @Override
    public <RowType extends Row> boolean has(Class<RowType> rowType, boolean recursive) {
        return get(rowType, recursive) != null;
    }

    @Override
    public boolean has(String hierarchyTableName) {
        return has(null, hierarchyTableName);
    }

    @Override
    public <RowType extends Row> boolean has(Class<RowType> rowType, String hierarchyTableName) {
        return get(rowType, hierarchyTableName) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RowType extends Row> Table<RowType> getAnonymousTable(Class<RowType> rowType) {
        return (Table<RowType>) rowTypeMaptoAnonymousTable.get(rowType);
    }

    @Override
    public <RowType extends Row> Table<RowType> get(Class<RowType> rowType) {
        return get(rowType, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RowType extends Row> Table<RowType> get(Class<RowType> rowType, boolean recursive) {
        if (hasAnonymousTable(rowType)) {
            return getAnonymousTable(rowType);
        }
        for (Table<? extends Row> table : nameMaptoTable.values()) {
            if (table.getRowClass().equals(rowType)) {
                return (Table<RowType>) table;
            }
        }
        if (recursive) {
            for (TableContainer container : nameMaptoContainer.values()) {
                if (container.has(rowType)) {
                    return container.get(rowType);
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RowType extends Row> Table<RowType> get(Class<RowType> rowType, String hierarchyTableName) {
        HierarchyName name = HierarchyNameBuilder.build(hierarchyTableName);
        if (!name.isHierarchy()) {
            return (Table<RowType>) nameMaptoTable.get(name.getFirstName());
        } else {
            String remainingName = name.getRemainingName(true);
            String lastName = name.getFirstName(true);
            TableContainer subContainer = getSubContainer(remainingName);
            if (subContainer == null) {
                return null;
            } else {
                return subContainer.get(rowType, lastName);
            }
        }
    }

    @Override
    public void addAnonymousTable(Table<? extends Row> table) {
        rowTypeMaptoAnonymousTable.put(table.getRowClass(), table);
    }

    @Override
    public void addNamedTable(Table<? extends Row> table) {
        addNamedTable(null, table);
    }

    @Override
    public void addNamedTable(String hierarchySubContainerName, Table<? extends Row> table) {
        HierarchyName name = HierarchyNameBuilder.build(hierarchySubContainerName);
        if (name.isAnonymous()) {
            nameMaptoTable.put(table.getName(), table);
        } else {
            buildSubContainer(hierarchySubContainerName);
            getSubContainer(hierarchySubContainerName).addNamedTable(table);
        }
    }

    @Override
    public void addTable(Table<? extends Row> table) {
        HierarchyName name = HierarchyNameBuilder.build(table.getName());
        if (name.isAnonymous()) {
            addAnonymousTable(table);
        } else {
            addNamedTable(table);
        }
    }

    @Override
    public boolean hasSubContainer(String hierarchySubContainerName) {
        return getSubContainer(hierarchySubContainerName) != null;
    }

    @Override
    public TableContainer getSubContainer(String hierarchySubContainerName) {
        HierarchyName name = HierarchyNameBuilder.build(hierarchySubContainerName);
        if (!name.isHierarchy()) {
            String containerName = name.getFirstName();
            return nameMaptoContainer.get(containerName);
        } else {
            String remainingName = name.getRemainingName(true);
            String lastName = name.getFirstName(true);
            TableContainer subContainer = getSubContainer(remainingName);
            if (subContainer == null) {
                return null;
            } else {
                return subContainer.getSubContainer(lastName);
            }
        }
    }

    @Override
    public void addSubContainer(String hierarchySubContainerName, TableContainer subContainer) {
        HierarchyName name = HierarchyNameBuilder.build(hierarchySubContainerName);
        if (!name.isAnonymous()) {
            subContainer.setOwner(this);
            nameMaptoContainer.put(subContainer.getName(), subContainer);
        } else {
            buildSubContainer(hierarchySubContainerName);
            getSubContainer(hierarchySubContainerName).addSubContainer(null, subContainer);
        }
    }

    @Override
    public void buildSubContainer(String hierarchySubContainerName) {
        HierarchyName name = HierarchyNameBuilder.build(hierarchySubContainerName);
        if (!name.isHierarchy()) {
            String containerName = name.getFirstName();
            if (!nameMaptoContainer.containsKey(containerName)) {
                TableContainer subContainer = new _TableContainer(containerName);
                subContainer.setOwner(this);
                nameMaptoContainer.put(containerName, subContainer);
            }
        } else {
            String firstName = name.getFirstName();
            String remainingName = name.getRemainingName();
            if (!nameMaptoContainer.containsKey(firstName)) {
                TableContainer subContainer = new _TableContainer(firstName);
                subContainer.setOwner(this);
                nameMaptoContainer.put(firstName, subContainer);
            }
            getSubContainer(firstName).buildSubContainer(remainingName);
        }
    }

    @Override
    public int subContainerSize() {
        return subContainerSize(false);
    }

    @Override
    public int subContainerSize(boolean recursive) {
        int size = nameMaptoContainer.size();
        if (recursive) {
            for (TableContainer subContainer : nameMaptoContainer.values()) {
                size += subContainer.subContainerSize(true);
            }
        }
        return size;
    }

    @Override
    public int anonymousTableSize() {
        return anonymousTableSize(false);
    }

    @Override
    public int anonymousTableSize(boolean recursive) {
        int size = rowTypeMaptoAnonymousTable.size();
        if (recursive) {
            for (TableContainer subContainer : nameMaptoContainer.values()) {
                size += subContainer.anonymousTableSize(true);
            }
        }
        return size;
    }

    @Override
    public int size() {
        return size(false);
    }

    @Override
    public int size(boolean recursive) {
        int size = nameMaptoTable.size() + anonymousTableSize(recursive);
        if (recursive) {
            for (TableContainer subContainer : nameMaptoContainer.values()) {
                size += subContainer.size(true);
            }
        }
        return size;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("name", name);
        builder.append("owner", owner);
        builder.append("anonymousTableSize", anonymousTableSize() + "/" + anonymousTableSize(true));
        builder.append("size", size() + "/" + size(true));
        builder.append("subContainerSize", subContainerSize() + "/" + subContainerSize(true));
        return builder.toString();
    }

}
