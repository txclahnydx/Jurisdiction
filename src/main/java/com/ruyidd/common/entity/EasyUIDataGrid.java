package com.ruyidd.common.entity;

import java.util.List;
/**
 * EasyUI DataGrid 数据返回
 * @author tianxc
 *
 * @param <T>
 */
public class EasyUIDataGrid<T> {
	
	private Integer total;
	private List<T> rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
