package codexstester.bdd.stepsdef;

import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LambdaSteps implements En {

    public Map<String, Map<String, String>> mapInMap(DataTable table) {

        Map<String, Map<String, String>> tableMapMap = new HashMap<>();

        String[] rows = table.toString().split("\\|\n");
        String[] cols = rows[0].split("\\|");

        String key = cols[cols.length-2].trim();
        String val = cols[cols.length-1].trim();

        for (int i = 1; i < rows.length; i++) {
            Map<String, String> tableMap = new HashMap<>();
            String[] columns = rows[i].replaceFirst("\\|", "").split("\\|");
            tableMap.put(key, columns[1].trim());
            tableMap.put(val, columns[2].trim());
            tableMapMap.put(columns[0].trim(), tableMap);
        }

        return tableMapMap;
    }

    public Map<String, List<String>> mapInList(DataTable table) {

        Map<String, List<String>> tableMapList = new HashMap<>();

        String[] rows = table.toString().split("\\|\n");

        for (String row : rows) {

            String[] list = row.replaceFirst("\\|", "").trim().split("\\|");
            String key = list[0].trim();

            List<String> listStr = new ArrayList<>();

            for (int j = 1; j < list.length; j++) {
                listStr.add(list[j].trim());
            }

            tableMapList.put(key, listStr);
        }

        return tableMapList;
    }

    public LambdaSteps() {

        Given("list test", (DataTable table) -> {
            System.out.println("=========+++> LIST");
            for (String string : table.asList()) {
                System.out.println(string);
            }
        });

        And("list list test", (DataTable table) -> {
            System.out.println("=========+++> LIST LIST");
            for (List<String> list : table.asLists()) {
                System.out.println(list);
            }
        });

        And("list map test", (DataTable table) -> {
            System.out.println("=========+++> LIST MAP");
            for (Map<String, String> map : table.asMaps()) {
                System.out.println(map);
            }
        });

        And("map test", (DataTable table) -> {
            System.out.println("=========+++> MAP");
            table.asMap(String.class, String.class).forEach((k, v)->{
                System.out.println("{"+k+"="+v+"}");
            });
        });

        And("map list test", (DataTable table) -> {
            System.out.println("=========+++> MAP LIST");
            Map<String, List<String>> mapList = mapInList(table);

            mapList.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("map map test", (DataTable table) -> {
            System.out.println("=========+++> MAP MAP");
            Map<String, Map<String, String>> mapMap = mapInMap(table);

            mapMap.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("with the following template string definition", (String test) -> {
            System.out.println("====> " + test);
        });
    }
}
