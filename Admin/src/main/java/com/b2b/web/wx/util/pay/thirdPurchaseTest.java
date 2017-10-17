package com.b2b.web.wx.util.pay;

import com.b2b.common.domain.*;
import com.b2b.common.util.WmsUtils;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.*;

/**
 * Created by a. on 2017/10/10.
 */
public class thirdPurchaseTest {


    public static void main(String[] args)
            throws IOException {

        List<WmsEntryorder> orders = new ArrayList<WmsEntryorder>();
        WmsEntryorder order1 = new WmsEntryorder();
        order1.setOwnerCode("01");
        order1.setItemCode("3333");
        order1.setItemName("3333");
        order1.setPlanQty("20");
        orders.add(order1);
        WmsEntryorder order2 = new WmsEntryorder();
        order2.setOwnerCode("01");
        order2.setItemCode("12345");
        order2.setItemName("22222");
        order2.setPlanQty("20");
        orders.add(order2);


        XStream xStream = new XStream();
        xStream.alias("orderLine", WmsEntryorder.class);
        xStream.alias("orderLines", List.class);
        //调用toXML 将对象转成字符串
        String s = xStream.toXML(orders);


        String requestXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request> \n" +
                "  <entryOrder> \n" +
                "    <entryOrderCode>111111test1234</entryOrderCode>  \n" +
                "    <ownerCode>01</ownerCode> \n" +
                "    <purchaseOrderCode>111111test1234</purchaseOrderCode>\n" +
                "    <warehouseCode>上海领蛙</warehouseCode> \n" +
                "    <orderType>CGRK</orderType>  \n" +
                "  </entryOrder>  \n"+
                "  {body}\n" +
                "</request>";

        requestXml = requestXml.replace("{body}", s);

        System.out.println(requestXml);


        String responseXml =  WmsUtils.getHttpResponse4Post("entryorder.create", requestXml);
        WmsResponse wmsResponse = new WmsResponse();

        try {
            Map<String, Object> responseMap = Dom2Map(DocumentHelper.parseText(responseXml));
            if (responseMap.isEmpty()) {
                //空报文返回
            } else {
                wmsResponse.setFlag(responseMap.get("flag").toString());
                wmsResponse.setCode(responseMap.get("code").toString());
                wmsResponse.setMessage(responseMap.get("message").toString());
                if(responseMap.get("flag").equals("failure")) {
                    Map<String, Object> itemsM = (HashMap<String, Object>)responseMap.get("items");
                    List list = (ArrayList) itemsM.get("item");

                    List<Item> itemList = new ArrayList<Item>();
                    for (Object l : list) {
                        HashMap<String, Object> l2 = (HashMap<String, Object>) l;
                        WarehouseSyncList warehouseSyncList = new WarehouseSyncList();
                        warehouseSyncList.setOrder(l2.get("itemCode").toString());
                        warehouseSyncList.setRemake(l2.get("message").toString());
                    }

                } else {

                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    private static Map<String, Object> Dom2Map(Document doc){
        Map<String, Object> map = new HashMap<String, Object>();
        if(doc == null)
            return map;
        Element root = doc.getRootElement();
        for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
            Element e = (Element) iterator.next();
            //System.out.println(e.getName());
            List list = e.elements();
            if(list.size() > 0){
                map.put(e.getName(), Dom2Map(e));
            }else
                map.put(e.getName(), e.getText());
        }
        return map;
    }


    private static Map Dom2Map(Element e){
        Map map = new HashMap();
        List list = e.elements();
        if(list.size() > 0){
            for (int i = 0;i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();

                if(iter.elements().size() > 0){
                    Map m = Dom2Map(iter);
                    if(map.get(iter.getName()) != null){
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    }else
                        map.put(iter.getName(), m);
                }
                else{
                    if(map.get(iter.getName()) != null){
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")){
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    }else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

}
