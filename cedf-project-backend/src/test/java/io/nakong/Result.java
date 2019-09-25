package io.nakong;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */

public class Result {
    private String itemId;// 监控位置
    private Object value;// 监控值

    public Result(String itemId, Object value) {
        this.itemId = itemId;
        this.value = value;
    }

    public String getItemId() {
        return itemId;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[itemId=" + itemId + ", value=" + value + "]";
    }
}