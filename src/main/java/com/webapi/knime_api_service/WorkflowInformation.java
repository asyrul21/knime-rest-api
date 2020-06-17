package com.webapi.knime_api_service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkflowInformation {
	private String fileType;
	private String columnFilter;
	private String rowFilter;
	private String outputFormat;
	
	
	@Override
	public String toString() {
		return "WorkflowInformation [fileType=" + fileType + ", columnFilter=" + columnFilter + ", rowFilter="
				+ rowFilter + ", outputFormat=" + outputFormat + "]";
	}
	// setters and getters
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getColumnFilter() {
		return columnFilter;
	}
	public void setColumnFilter(String columnFilter) {
		this.columnFilter = columnFilter;
	}
	public String getRowFilter() {
		return rowFilter;
	}
	public void setRowFilter(String rowFilter) {
		this.rowFilter = rowFilter;
	}
	public String getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	
	

}
