package da;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@cowain.com.cn
 * @date 2019-12-04 13:17:33
 */

public class WmsPoEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private String t;

    public String getT() {
        return t;
    }
	public void setT(String t){
        this.t = t;
	}
		/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String bizPerson;
	/**
	 * 单据类型编码
	 */
	private String docTypeCode;
	/**
	 * 单据类型名称
	 */
	private String docTypeName;
	/**
	 * 日期
	 */
	private Date createTime;
	/**
	 * 单号
	 */
	private String poCode;
	/**
	 * 供应商编码
	 */
	private String supplyId;
	/**
	 * 供应商名称
	 */
	private String supplyName;
	/**
	 * 关闭人
	 */
	private String closePerson;
	/**
	 * 关闭时间
	 */
	private Date closeTime;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：名称
	 */
	public void setBizPerson(String bizPerson) {
		this.bizPerson = bizPerson;
	}
	/**
	 * 获取：名称
	 */
	public String getBizPerson() {
		return bizPerson;
	}
	/**
	 * 设置：单据类型编码
	 */
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	/**
	 * 获取：单据类型编码
	 */
	public String getDocTypeCode() {
		return docTypeCode;
	}
	/**
	 * 设置：单据类型名称
	 */
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	/**
	 * 获取：单据类型名称
	 */
	public String getDocTypeName() {
		return docTypeName;
	}
	/**
	 * 设置：日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：单号
	 */
	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}
	/**
	 * 获取：单号
	 */
	public String getPoCode() {
		return poCode;
	}
	/**
	 * 设置：供应商编码
	 */
	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}
	/**
	 * 获取：供应商编码
	 */
	public String getSupplyId() {
		return supplyId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupplyName() {
		return supplyName;
	}

	public String getClosePerson() {
		return closePerson;
	}

	public void setClosePerson(String closePerson) {
		this.closePerson = closePerson;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
}
