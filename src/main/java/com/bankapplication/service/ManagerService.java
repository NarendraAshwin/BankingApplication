package com.bankapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dao.ManagerDao;
import com.bankapplication.dto.Branch;
import com.bankapplication.dto.Manager;

@Service
public class ManagerService 
{
	@Autowired
	ManagerDao dao;
	@Autowired
	BranchDao bDao;
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager,int id)
	{
		Branch branch = bDao.findBranch(id);
		Manager m = dao.saveManager(manager);
		branch.setManager(m);
		m.setBranch(branch);
		dao.updateManager(m.getManagerId(), m);
		ResponseStructure<Manager> res = new ResponseStructure<>();
		res.setData(m);
		res.setMsg("Manager has been saved successfully");
		res.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Manager>>(res,HttpStatus.CREATED);
		
		
	}
	public ResponseEntity<ResponseStructure<Manager>> findManager(int id){
		ResponseStructure<Manager> res= new ResponseStructure<>();
		if(dao.findManager(id)!=null) {
			res.setData(dao.findManager(id));
			res.setMsg("Manager Found successfully");
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(res,HttpStatus.CREATED);
		}
		else {
			return null; //no manager found exception
		}
	}
	
	
	
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int id)
	{
		ResponseStructure<Manager> res= new ResponseStructure<>();
		if(dao.findManager(id)!=null) 
		{
			Manager exMan = dao.findManager(id);
			Branch branch = exMan.getBranch();
			
			exMan.setBranch(null);
			branch.setManager(null);
			dao.updateManager(id, exMan);
			
			res.setData(dao.deleteManager(id));
			res.setMsg("Manager with the ID" +id+"is Deleted successfully");
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(res,HttpStatus.CREATED);
		}
		else
		{
			return null; //no manager found exception
		}
	}
	
	
	public ResponseEntity<ResponseStructure<Manager>> updateManager(int id,Manager m){
		ResponseStructure<Manager> res= new ResponseStructure<>();
		if(dao.findManager(id)!=null) {
			Manager manager = dao.findManager(id);
			m.setManagerId(manager.getManagerId());
			res.setData(dao.saveManager(manager));
			res.setMsg("Manager has been updaated successfully");
			res.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Manager>>(res,HttpStatus.CREATED);
		}
		else 
		{
			return null; //no manager found exception
		}
	}
	
	
	
	
}
