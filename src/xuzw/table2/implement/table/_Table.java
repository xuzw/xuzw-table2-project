package xuzw.table2.implement.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import xuzw.table2.exception.TableHeaderParseFailedException;
import xuzw.table2.exception.TableParseFailedException;
import xuzw.table2.interfaces.Row;
import xuzw.table2.interfaces.table.Table;
import xuzw.table2.interfaces.table.TableHeader;

public class _Table<RowType extends Row> implements Table<RowType> {

    private String name;
    private Map<Integer, RowType> idMaptoRow = new HashMap<Integer, RowType>();
    private Class<RowType> rowClass;
    private TableHeader tableHeader = new _TableHeader();

    public _Table() {
    }

    public _Table(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean has(int id) {
        return idMaptoRow.containsKey(id);
    }

    @Override
    public RowType get(int id) {
        return idMaptoRow.get(id);
    }

    @Override
    public int size() {
        return idMaptoRow.size();
    }

    @Override
    public List<RowType> values() {
        return new ArrayList<RowType>(idMaptoRow.values());
    }

    @Override
    public Class<RowType> getRowClass() {
        return rowClass;
    }

    @Override
    public TableHeader getTableHeader() {
        return tableHeader;
    }

    @Override
    public boolean isValidFile(File file) {
        return (file != null) && file.isFile() && file.getName().endsWith(TABLE_FILE_NAME_SUFFIX);
    }

    @Override
    public boolean isValidFiles(File... files) {
        if (files == null) {
            return false;
        }
        for (File file : files) { // 首先逐一验证单文件
            if (!isValidFile(file)) {
                return false;
            }
        }
        try { // 然后验证表头一致性
            TableHeader tableHeader = null;
            for (File file : files) {
                TableHeader _tableHeader = new _TableHeader();
                _tableHeader.parse(file);
                if (tableHeader != null && !tableHeader.equals(_tableHeader)) {
                    return false;
                } else {
                    tableHeader = _tableHeader;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(Class<RowType> _rowClass, File... files) throws TableParseFailedException, TableHeaderParseFailedException {

        if (!isValidFiles(files)) {
            return;
        }

        tableHeader.parse(files[0]);
        rowClass = _rowClass;

        File file = null;
        try {
            for (int i = 0; i < files.length; i++) {
                file = files[i];
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);

                br.readLine(); // 舍弃第一行
                String[] columnNames = br.readLine().split("\t"); // 第二行是列名
                br.readLine(); // 舍弃第三行

                Map<String, String> columnNameMaptoValue = new HashMap<String, String>();
                String line = null;
                int rowNumber = 0;
                while ((line = br.readLine()) != null) {
                    rowNumber++;
                    String[] columnValues = line.split("\t");
                    for (int j = 0; j < columnNames.length; j++) {
                        String columnValue = (j >= columnValues.length) ? "" : columnValues[j];
                        columnNameMaptoValue.put(columnNames[j], columnValue);
                    }
                    RowType row = (RowType) rowClass.newInstance();
                    row.setTable(this);
                    row.parse(rowNumber, columnNameMaptoValue);
                    int rowId = row.getId();
                    if (has(rowId)) {
                        Throwable cause = new Exception("Duplicate Row Id " + rowId);
                        throw new TableParseFailedException(this, file, cause);
                    }
                    idMaptoRow.put(rowId, row);
                }

                br.close();
                fileReader.close();
            }
        } catch (Exception e) {
            throw new TableParseFailedException(this, file, e);
        }
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("name", name);
        builder.append("rowClass", rowClass);
        builder.append("tableHeader", tableHeader);
        builder.append("size", size());
        return builder.toString();
    }

}
