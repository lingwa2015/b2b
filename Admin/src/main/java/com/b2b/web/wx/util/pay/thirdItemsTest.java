package com.b2b.web.wx.util.pay;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.WarehouseSyncList;
import com.b2b.common.domain.WmsItem;
import com.b2b.common.domain.WmsResponse;
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
public class thirdItemsTest {


    public static void main(String[] args)
            throws IOException {

//        String requestXml = FileUtils.readFileToString(new File("/Users/a./Downloads/b2b/Admin/src/main/resources/spring/item.xml"));
        List<WmsItem> items = new ArrayList<WmsItem>();
        WmsItem item = new WmsItem();
        item.setItemCode("111test");
        item.setItemName("测试商品2");
        item.setBarCode("1111111");
        item.setBrandName("11111111");
        item.setItemType("ZS");
        items.add(item);
        WmsItem item2 = new WmsItem();
        item2.setItemCode("222test");
        item2.setItemName("测试商品2");
        item2.setItemName("测试商品2");
        item2.setBarCode("22222");
        item2.setBrandName("222222");
//        item2.setItemType("ZS");
        items.add(item2);

        WmsItem item3 = new WmsItem();
        item3.setItemCode("333test");
        item3.setItemName("测试商品3");
        item3.setItemName("测试商品3");
        item3.setBarCode("33333");
        item3.setBrandName("333333");
        items.add(item3);


        XStream xStream = new XStream();
        xStream.alias("item", WmsItem.class);
        xStream.alias("items", List.class);
        //调用toXML 将对象转成字符串
        String s = xStream.toXML(items);


        String requestXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request>\n" +
                "  <actionType>ADD</actionType>\n" +
                "  {body}\n" +
                "  <ownerCode>01</ownerCode>\n" +
                "  <warehouseCode>上海领蛙</warehouseCode>\n" +
                "</request>";

        requestXml = requestXml.replace("{body}", s);

        System.out.println(requestXml);


        String responseXml =  WmsUtils.getHttpResponse4Post("items.synchronize", requestXml);
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
