package io.nakong.opc;

import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;

public class UtgardTutorial1 {

    public static void main(String[] args) throws Exception {
        // 连接信息
        final ConnectionInformation ci = new ConnectionInformation();
        // 192.168.0.193
        ci.setHost("127.0.0.1");         // 电脑IP
        ci.setDomain("");                  // 域，为空就行
        ci.setUser("codyy");             // 电脑上自己建好的用户名
        ci.setPassword("123456");          // 用户名的密码
//        ci.setProgId("Kepware.KEPServerEX.V5");
        ci.setClsid("B3AF0BF6-4C0C-4804-A122-6F3B160F4397"); // KEPServer的注册表ID，可以在“组件服务”里看到
        final String itemId = "Channel_0_User_Defined.Sine.Sine1";    // 项的名字按实际，没有实际PLC，用的模拟器：simulator
        // 启动服务
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());

        try {
            // 连接到服务
            server.connect();
            // add sync access, poll every 500 ms，启动一个同步的access用来读取地址上的值，线程池每500ms读值一次
            // 这个是用来循环读值的，只读一次值不用这样
            final AccessBase access = new SyncAccess(server, 500);
            access.addItem(itemId, new DataCallback() {
                @Override
                public void changed(Item item, ItemState itemState) {
                    int type = 0;
                    try {
                        type = itemState.getValue().getType();
                        if(type == JIVariant.VT_R4) {  // float 数据
                            float value = 0;
                            try {
                                value = itemState.getValue().getObjectAsFloat();
                            } catch (JIException e) {
                                e.printStackTrace();
                            }
                            // 写数据库
                            System.out.println("-----float 类型值： " + value);
                        }
                    } catch (JIException e) {
                        e.printStackTrace();
                    }
                }
            });
            access.bind();
            Thread.sleep(1 * 1000);
            access.unbind();
        } catch (final JIException e) {
            System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
        }
    }
}