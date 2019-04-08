package io.nakong.common.excel;

import java.util.ArrayList;
import java.util.List;

import org.jxls.reader.XLSReadMessage;

public class XLSReadStatus extends org.jxls.reader.XLSReadStatus {
	List readMessages = new ArrayList();

	boolean statusOK = true;

	public XLSReadStatus() {
	}

	void mergeReadStatus(XLSReadStatus status) {
		if (status == null) {
			return;
		}
		if (!status.isStatusOK()) {
			statusOK = false;
		}
		addMessages(status.getReadMessages());
	}

	void addMessage(XLSReadMessage errorMessage) {
		if (errorMessage != null) {
			readMessages.add(errorMessage);
		}
	}

	void addMessages(List list) {
		if (list != null) {
			readMessages.addAll(list);
		}
	}

	void clear() {
		readMessages.clear();
	}

	public boolean isStatusOK() {
		return statusOK;
	}

	public void setStatusOK(boolean statusOK) {
		this.statusOK = statusOK;
	}

	public List getReadMessages() {
		return readMessages;
	}
}
