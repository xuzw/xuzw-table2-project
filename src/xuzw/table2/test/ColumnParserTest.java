package xuzw.table2.test;

import xuzw.table2.exception.ColumnParseFailedException;
import xuzw.table2.utils.ColumnParser;

public class ColumnParserTest {

    public static void main(String[] args) throws ColumnParseFailedException {

        System.out.println(ColumnParser.parseString(null));
        System.out.println(ColumnParser.parseString(""));
        System.out.println(ColumnParser.parseString(" "));
        System.out.println(ColumnParser.parseString("1"));
        System.out.println(ColumnParser.parseString("1#注释"));
        System.out.println(ColumnParser.parseString("####"));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseInt(null));
        System.out.println(ColumnParser.parseInt(""));
        System.out.println(ColumnParser.parseInt(" "));
        System.out.println(ColumnParser.parseInt("1"));
        System.out.println(ColumnParser.parseInt("1#注释"));
        System.out.println(ColumnParser.parseInt("####"));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseBool(null));
        System.out.println(ColumnParser.parseBool(""));
        System.out.println(ColumnParser.parseBool(" "));
        System.out.println(ColumnParser.parseBool("1"));
        System.out.println(ColumnParser.parseBool("1#注释"));
        System.out.println(ColumnParser.parseBool("####"));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseSimpleColumn(null, (String) null, Integer.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "", Integer.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "-1", Integer.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "0", Integer.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1", Integer.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1017", Integer.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseSimpleColumn(null, (String) null, Boolean.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "", Boolean.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "-1", Boolean.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "0", Boolean.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1", Boolean.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1017", Boolean.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseSimpleColumn(null, (String) null, String.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "", String.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1", String.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "1017", String.class));
        System.out.println(ColumnParser.parseSimpleColumn(null, "string", String.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseListColumn(null, (String) null, Integer.class));
        System.out.println(ColumnParser.parseListColumn(null, "", Integer.class));
        System.out.println(ColumnParser.parseListColumn(null, ",", Integer.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017", Integer.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,", Integer.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,1017", Integer.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseListColumn(null, (String) null, Boolean.class));
        System.out.println(ColumnParser.parseListColumn(null, "", Boolean.class));
        System.out.println(ColumnParser.parseListColumn(null, ",", Boolean.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017", Boolean.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,", Boolean.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,1017", Boolean.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseListColumn(null, (String) null, String.class));
        System.out.println(ColumnParser.parseListColumn(null, "", String.class));
        System.out.println(ColumnParser.parseListColumn(null, ",", String.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017", String.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,", String.class));
        System.out.println(ColumnParser.parseListColumn(null, "1017,1017", String.class));
        System.out.println(ColumnParser.parseListColumn(null, "string,string", String.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleColumn(null, (String) null, Integer.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "", Integer.class));
        System.out.println(ColumnParser.parseTupleColumn(null, ":", Integer.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017", Integer.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:", Integer.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:1017", Integer.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleColumn(null, (String) null, Boolean.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "", Boolean.class));
        System.out.println(ColumnParser.parseTupleColumn(null, ":", Boolean.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017", Boolean.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:", Boolean.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:1017", Boolean.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleColumn(null, (String) null, String.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "", String.class));
        System.out.println(ColumnParser.parseTupleColumn(null, ":", String.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017", String.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:", String.class));
        System.out.println(ColumnParser.parseTupleColumn(null, "1017:1017", String.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleListColumn(null, (String) null, Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, ",", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:", Integer.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:1", Integer.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleListColumn(null, (String) null, Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, ",", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:", Boolean.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:1", Boolean.class));
        System.out.println("=========================");

        System.out.println(ColumnParser.parseTupleListColumn(null, (String) null, String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, ",", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:", String.class));
        System.out.println(ColumnParser.parseTupleListColumn(null, "1017:1,1017:1", String.class));
        System.out.println("=========================");
    }
}
