package com.mftcc.interior.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.sys.bean.SysRole;
import com.mftcc.interior.sys.bean.SysUser;
import com.mftcc.interior.sys.dao.ISysUserDao;
import com.mftcc.method.bean.Ipage;

/**
 * @author shensh
 * @date 2018年3月17日  
 * @version 1.0
 */


@SuppressWarnings(value = { "deprecation", "unchecked" })
@Repository
public class SysUserDaoImpl extends BaseDao implements ISysUserDao {

	/* 登录
	 *  
	 */
	@Override
	public List<SysUser> getSysUser(SysUser paramBean) {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForList("selectSysUser",paramBean);
	}
	@Override
	public boolean updateSysUser(SysUser sysUser) {
		 try {    //  修改正常返回 true
            int c =  getSqlMapClientTemplate().update("updateSysUser", sysUser);
            if (c > 0) {  
                return true;  
	          }  
	            return false;  
	        } catch (Exception e) {  
	            return false;  
	        }  
		 
	}

	@Override
	public List<SysUser> getSysUserByParam(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertSysUser(SysUser sysuser) {
		// //  增加正常返回 true
		 try {    
            Object o = getSqlMapClientTemplate().insert("insertSysUser", sysuser);

	            if (!o.equals(null)) {  
	                return true;  
		          }  
		            return false;  
	       } catch (Exception e) {  
	            return false;  
	        }  
	}

	
	@Override
	public List<SysUser> getSysUserListPage(Ipage ipage)  {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("selectSysUserPage", ipage);
	}
	
	@Override
	public String getSysUserListCount(Ipage ipage)  {
		// TODO Auto-generated method stub
		return (String)getSqlMapClientTemplate().queryForObject("selectSysUserCount", ipage);

	}



}
