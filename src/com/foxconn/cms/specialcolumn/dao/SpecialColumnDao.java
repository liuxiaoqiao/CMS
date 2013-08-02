package com.foxconn.cms.specialcolumn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Service;

import com.foxconn.cms.specialcolumn.pojo.SpecialColumn;


@Service("specialColumnDao")
public class SpecialColumnDao {
  @Resource(name="sqlMapClientTemplate")
  private SqlMapClientTemplate sqlMapClientTemplate;
  
  public List<SpecialColumn> getSpecialColumnList(SpecialColumn specialColumn){
	return this.sqlMapClientTemplate.queryForList("SpecialColumn.getSpecialColumnList",specialColumn);
  }
  
  public SpecialColumn  getSpecialColumnDetail(SpecialColumn specialColumn){
	  return (SpecialColumn)this.sqlMapClientTemplate.queryForObject("SpecialColumn.getSpecialColumnDetail", specialColumn);
  }
  
  public void insertSpecialColumn(SpecialColumn specialColumn){
	 	this.sqlMapClientTemplate.insert("SpecialColumn.insertSpecialColumn",specialColumn);
   }
  
  public void updateSpecialColumn(SpecialColumn specialColumn){
	 	this.sqlMapClientTemplate.update("SpecialColumn.updateSpecialColumn",specialColumn);
  }
  
  public void deleteSpecialColumn(SpecialColumn specialColumn){
	 	this.sqlMapClientTemplate.update("SpecialColumn.deleteSpecialColumn",specialColumn);
  }
  
  
}
