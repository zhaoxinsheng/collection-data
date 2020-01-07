package da;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 入库表
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-09-29 16:37:21
 */
public class WmsPoDocFromErp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String t;

    private WmsPoEntity head;



	public WmsPoEntity getHead() {
		return head;
	}

	public void setHead(WmsPoEntity head) {
		this.head = head;
	}


}
