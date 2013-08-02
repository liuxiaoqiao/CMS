package com.foxconn.cms.specialcolumn.service;

import java.util.List;
import com.foxconn.cms.specialcolumn.pojo.SpecialColumn;

public interface SpecialColumnServie {
	SpecialColumn  getSpecialColumnDetail(SpecialColumn specialColumn);
	List<SpecialColumn> getSpecialColumnList(SpecialColumn specialColumn);
	void insertSpecialColumn(SpecialColumn specialColumn);
	void updateSpecialColumn(SpecialColumn specialColumn);
	void deleteSpecialColumn(SpecialColumn specialColumn);
}
