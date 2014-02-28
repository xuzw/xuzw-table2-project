package xuzw.table2.implement.table;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import xuzw.table2.exception.TableHeaderParseFailedException;
import xuzw.table2.interfaces.table.TableHeader;

public class _TableHeader implements TableHeader {

    private String idColumnName;
    private Map<String, String> columnNameMaptoType = new HashMap<String, String>();
    private Map<String, String> columnNameMaptoDesc = new HashMap<String, String>();

    @Override
    public String getIdColumnName() {
        return idColumnName;
    }

    @Override
    public Set<String> getAllColumnNames() {
        return columnNameMaptoType.keySet();
    }

    @Override
    public Map<String, String> getColumnNameMaptoType() {
        return columnNameMaptoType;
    }

    @Override
    public Map<String, String> getColumnNameMaptoDesc() {
        return columnNameMaptoDesc;
    }

    @Override
    public int getColumnSize() {
        return columnNameMaptoType.size();
    }

    @Override
    public void parse(File file) throws TableHeaderParseFailedException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charset.forName("utf-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String[] columnDescs = bufferedReader.readLine().split("\t"); // 第二行是列描述
            String[] columnNames = bufferedReader.readLine().split("\t"); // 第二行是列名
            String[] columnTypes = bufferedReader.readLine().split("\t"); // 第三行是列类型

            idColumnName = columnNames[0];
            for (int i = 0; i < columnNames.length; i++) {
                columnNameMaptoDesc.put(columnNames[i], columnDescs[i]);
                columnNameMaptoType.put(columnNames[i], columnTypes[i]);
            }

            bufferedReader.close();
            inputStreamReader.close();
        } catch (Exception e) {
            throw new TableHeaderParseFailedException(this, file, e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TableHeader)) {
            return false;
        } else {
            TableHeader another = (TableHeader) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(another.getIdColumnName(), idColumnName);
            builder.append(another.getColumnNameMaptoType(), columnNameMaptoType);
            return builder.isEquals();
        }
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("idColumnName", idColumnName);
        builder.append("columnNameMaptoType", columnNameMaptoType);
        builder.append("columnNameMaptoDesc", columnNameMaptoDesc);
        return builder.toString();
    }

}
