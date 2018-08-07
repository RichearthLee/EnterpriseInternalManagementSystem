
package com.mftcc.interior.pact.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mftcc.common.dao.BaseDao;
import com.mftcc.interior.pact.bean.Ipage;
import com.mftcc.interior.pact.bean.PayProgressInfo;
import com.mftcc.interior.pact.dao.IPayProgressDao;


@Repository
public class PayProgressDaoImpl extends BaseDao implements IPayProgressDao {

	@Override
	public List<PayProgressInfo> selectPayProgress(PayProgressInfo payprogress) {
		return getSqlMapClientTemplate().queryForList("selectPactPayprogress", payprogress);
	}

	@Override
	public void insertPayProgress(PayProgressInfo payprogress) {
		getSqlMapClientTemplate().insert("insertPactPayprogress", payprogress);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int updatePayProgress(PayProgressInfo payprogress) {
		return (int)getSqlMapClientTemplate().update("updatePactPayprogress", payprogress);
	}

	@Override
	public List<PayProgressInfo> getPayProgressList(String param) {
		return getSqlMapClientTemplate().queryForList("selectPayProgressByParam", param);
	}

	@Override
	public List<PayProgressInfo> getPayProgressPage(Ipage ipage) {
		
		return getSqlMapClientTemplate().queryForList("selectPayProgressPage", ipage);
	}

	@Override
	public String getPayProgressCount(Ipage ipage) {
		
		return (String)getSqlMapClientTemplate().queryForObject("selectPayProgressCount", ipage);
	}

}
