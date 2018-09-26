package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 申请绑定养护方
* @author: jiafr 
* @since: 2018-09-26 17:53:01
* @history:
*/
public class ApplyBindMaintain extends ABaseDO {

	private static final long serialVersionUID = 1L;

	// 编号
	private String code;

	// 产权方编号
	private String ownerId;

	// 养护方编号
	private String maintainId;

	// 状态（0未绑定/1审核/2审核不通过/3已绑定）
	private String status;

	// 更新人
	private String updater;

	// 更新时间
	private String updateDatetime;

	// 备注
	private String remark;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setMaintainId(String maintainId) {
		this.maintainId = maintainId;
	}

	public String getMaintainId() {
		return maintainId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}