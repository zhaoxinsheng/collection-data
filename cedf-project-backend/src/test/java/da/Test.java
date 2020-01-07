package da;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nakong.common.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String[] args) {

//        {"data":[{"head":{"erpDocNo":"","documentType":"009","dDate":"2020-01-06"},
//            "detail":[{"itemCode":"M1460001000101","itemDesc":"1460-001-0001X01  AL6061 本色阳极","itemName":null,
//                    "itemNum":"10.00","price":"0","amount":"0","cwhcode":"10","cdepcode":"","prcode":"PR200106001"}]}]}
//

        WmsPoDocFromErp data = new WmsPoDocFromErp();
        WmsPoEntity  head = new WmsPoEntity();
        head.setDocTypeCode("009");
        data.setHead(head);
//        data.setCreateTime(new Date());
//        data.setDocTypeName("ERP 订单");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        System.out.println(gson.toJson(data));
//        List<WmsPoDocFromErp> wmsPoDocFromErpList =gson.fromJson(gsonson.toJson(data),WmsPoDocFromErp.class);

        System.out.println(DateUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//        System.out.println(gson.toJson(wmsPoDocFromErpList));
    }
}
