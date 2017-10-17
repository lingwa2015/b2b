import java.util.*;

/**
 * Created by a. on 2017/8/3.
 */
public class companyDepartment {

    private static final String ERR_RETURN_INFO = "incorrect data";

    public static void main(String[] args) throws Exception{
        rank("1,A,0;2,B,1;3,C,2;4,D,2;5,E,3" +
                "" +
                "");
    }

    public static String rank(String infos) throws Exception {


        Map<String, Map<String, String>> departments = new TreeMap<String, Map<String, String>>();

        String[] info =  infos.split(";");
        if (info.length <= 3)
                return ERR_RETURN_INFO;
        for (int i = 0; i < info.length; i++) {
            String [] strInfo = info[i].split(",");
            if(strInfo.length != 3) {
                return ERR_RETURN_INFO;
            }

            Map<String, String> infoMap = new TreeMap<String, String>();
            infoMap.put(strInfo[0], strInfo[1]);
            if (i == 0) {
                departments.put(strInfo[2], infoMap);
            } else {
                if (departments.get(strInfo[2]) == null) {
                    departments.put(strInfo[2], infoMap);
                } else {
                    if (departments.get(strInfo[0]) == null) {
                        departments.put(strInfo[2], infoMap);
                    } else {
                        infoMap.remove(strInfo[0]);
                        infoMap.put(strInfo[0], strInfo[1]);
                        infoMap.putAll(departments.get(strInfo[2]));
                        departments.put(strInfo[2], infoMap);
                    }
                }
            }
        }
        String printStr = "";
        Iterator<Map.Entry<String, Map<String, String>>> it = departments.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, String>> entryW = it.next();
            Iterator<Map.Entry<String, String>> itn = entryW.getValue().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Map<String, String>> entry2 = it.next();
                printStr = printStr + entry2.getValue();
            }


//            printStr =
            for (int i = 0; i < departments.size(); i++) {
                Map.Entry<String, Map<String, String>> entrn = it.next();
//                printStr =

            }
            Map.Entry<String, Map<String, String>> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }



        return "";
    }

    public static String rank1(String infos) throws Exception {
        Map<String, String> departments = new TreeMap<String,String>();
        String[] info =  infos.split(";");
        if (info.length <= 3)
            return ERR_RETURN_INFO;
        for (int i = 0; i < info.length; i++) {
            String[] strInfo = info[i].split(",");
            if (strInfo.length != 3) {
                return ERR_RETURN_INFO;
            }
//            Map<String, String> infoMap = new TreeMap<String, String>();
//            infoMap.put(strInfo[0], strInfo[1]);

            if (departments.get(strInfo[2]+ "," + strInfo[1]) == null) {
                departments.put(strInfo[2]+strInfo[0], strInfo[1]);
            } else {
//                String
//                departments.get(strInfo[2]+strInfo[0], strInfo[1]);
            }
        }
        return "";
    }
}