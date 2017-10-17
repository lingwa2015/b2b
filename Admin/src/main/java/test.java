import com.b2b.common.util.DateUtil;
import com.b2b.web.controller.TestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by a. on 2017/8/3.
 */
public class test {

    private static final String DATE_FORMAT_YMDNOT = "yyyyMMdd";
    private static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    public static void main(String[] args) throws Exception{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String a1 = df.format(new Date());




        String date1 = DateUtil.formatDate(new Date(), "yyyy-MM-07");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = "2017-06";
        Date sourceDate = null;
        try {
            sourceDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);

        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat dateEndStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = dateEndStr.parse(dateEndStr.format(calendar.getTime()));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(sourceDate);
        calendar2.add(Calendar.MONTH, 1);
        calendar2.set(Calendar.DAY_OF_MONTH, 7);
        SimpleDateFormat dateStarStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date dateEnd = dateStarStr.parse(dateStarStr.format(calendar2.getTime()));



        cal.add(Calendar.YEAR,0);
        cal.add(Calendar.MONTH, -1);

        SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM");
        String beferDate = returnSdf.format(cal.getTime());



        System.out.print(TestController.number2CNMontrayUnit(BigDecimal.valueOf(672423.347)));

        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });



        map.put("1", "f");
        map.put("3", "s");
        map.put("4", "a");
        map.put("2", "b");
        map.put("6", "z");
        map.put("5", "c");


        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        }
        );

        List<String> list1 = new ArrayList<String>();

        list1.add("5");
        list1.add("4");
        list1.add("2");
        list1.add("7");
        list1.add("1");
        Collections.sort(list1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);

        }});


//        String maxPurchaseId = null;
//
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        date = calendar.getTime();
//        String time = DateUtil.formatDate(date, DATE_FORMAT_YMDNOT);
//        if(StringUtils.isNotBlank(maxPurchaseId)) {
//            if (time.equals(maxPurchaseId.substring(0, 8))) {
//                Integer count = Integer.valueOf(maxPurchaseId.substring(8, 10)) + 1;
//                String countStr = (count + "").length() == 1 ? "0" + count : count + "";
//                maxPurchaseId = time + countStr;
//            } else {
//                maxPurchaseId = time + "01";
//            }
//        } else {
//            maxPurchaseId = time + "01";
//        }
//        Date_start_end("2016-01", "2017-07");
    }

    public static void Date_start_end(String start_date, String end_date) throws Exception {
        Date startDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM).parse(start_date);
        Date endDate = new SimpleDateFormat(DATE_FORMAT_YYYY_MM).parse(end_date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate)) {

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM);
            String str = sdf.format(calendar.getTime());
            Date date = sdf.parse(sdf.format(calendar.getTime()));
            System.out.println(date);//输出日期结果

            calendar.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
    }
}