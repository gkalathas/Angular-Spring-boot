package com.ots.trainingapi.global.dto;

import java.util.List;

/**
 * Wrapper για τη λειτουργία εξαγωγής σε excel με πολλά φύλλα
 */
public class ExportDataDtoWrapper {
	
	private List<ExportDataDto> exportParamsList;
	
	public ExportDataDtoWrapper() {
    }
	
	public List<ExportDataDto> getExportParamsList() {
		return exportParamsList;
	}
	
	public void setExportParamsList(List<ExportDataDto> exportParamsList) {
		this.exportParamsList = exportParamsList;
	}
}
