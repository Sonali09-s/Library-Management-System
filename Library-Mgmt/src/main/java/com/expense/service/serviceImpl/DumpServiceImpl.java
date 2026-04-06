package com.expense.service.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.dao.entity.DumpTable;
import com.expense.dao.repo.DumpTableRepo;
import com.expense.service.DumpService;

@Service
public class DumpServiceImpl implements DumpService {
	
	@Autowired
	DumpTableRepo dumpRepo;

	@Override
	public Long dumpService(Long userId, String apiReqRes, Integer type,Long dumpId) {
	
	try {
		
		DumpTable dumptable = new DumpTable();
		
		if(type==2 && dumpId==null) {
			return 0L;
		}
		if(type==2 && dumpId!=null) {
			dumptable=dumpRepo.findById(dumpId).get();
				}
		
		if(type==1) {
			dumptable.setApiRequest(apiReqRes);
			dumptable.setUserId(userId);
			dumptable.setCreatedAt(LocalDateTime.now());
		}
		
		else {
			dumptable.setApiResponse(apiReqRes);
			dumptable.setUpdatedAt(LocalDateTime.now());
			
		}
		DumpTable dumpModel = dumpRepo.save(dumptable);
		return dumpModel.getId();
		
	}
	catch (Exception e) {
		e.printStackTrace();
		return null;
	}
		
	}
	

}
