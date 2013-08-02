package com.foxconn.cms.specialcolumn.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foxconn.cms.specialcolumn.pojo.SpecialColumn;
import com.foxconn.cms.specialcolumn.service.SpecialColumnServie;
import com.foxconn.cms.specialcolumn.dao.SpecialColumnDao;

@Service("specialColumnServiceImpl")
public class SpecialColumnServiceImpl implements SpecialColumnServie {
	@Autowired
	@Resource(name = "specialColumnDao")
	private SpecialColumnDao specialColumnDao;
	
	@Override
	public SpecialColumn  getSpecialColumnDetail(SpecialColumn specialColumn){
	   return specialColumnDao.getSpecialColumnDetail(specialColumn);
	}

	@Override
	public List<SpecialColumn> getSpecialColumnList(SpecialColumn specialColumn) {
		// TODO Auto-generated method stub
		return specialColumnDao.getSpecialColumnList(specialColumn);
	}

	@Override
	public void insertSpecialColumn(SpecialColumn specialColumn) {
		// TODO Auto-generated method stub
		specialColumnDao.insertSpecialColumn(specialColumn);
		
	}

	@Override
	public void updateSpecialColumn(SpecialColumn specialColumn) {
		// TODO Auto-generated method stub
		specialColumnDao.updateSpecialColumn(specialColumn);
		
	}

	@Override
	public void deleteSpecialColumn(SpecialColumn specialColumn) {
		// TODO Auto-generated method stub
		specialColumnDao.deleteSpecialColumn(specialColumn);
		
	}
	
}
