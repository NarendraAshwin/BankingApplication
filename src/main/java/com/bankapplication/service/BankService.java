package com.bankapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankapplication.config.ResponseStructure;
import com.bankapplication.dao.BankDao;
import com.bankapplication.dao.BranchDao;
import com.bankapplication.dto.Bank;
import com.bankapplication.dto.Branch;


@Service
public class BankService 
{
	@Autowired
	BranchDao bdao;
	@Autowired
	BankDao dao;
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b)
	{
		ResponseStructure<Bank> str = new ResponseStructure<>();
		str.setData(dao.saveBank(b));
		str.setStatus(HttpStatus.CREATED.value());
		str.setMsg("Bank has been saved !");
		return new ResponseEntity<ResponseStructure<Bank>>(str,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id){
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if(dao.findBank(id)!=null) {
			str.setData(dao.findBank(id));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with the Id::" + id+ "is been Found");
			return new ResponseEntity<ResponseStructure<Bank>>(str,HttpStatus.FOUND);
		}
		return null; //Bank not found exception
	}
	public ResponseEntity<ResponseStructure<Bank>> updateBank(int id, Bank bank)
	{
		ResponseStructure<Bank> str = new ResponseStructure<>();
		if(dao.findBank(id)!=null)
		{
			str.setData(dao.updateBank(id, bank));
			str.setStatus(HttpStatus.CREATED.value());
			str.setMsg("Bank with the Id::" +id+ "is been Updated Successfully");
			return new ResponseEntity<ResponseStructure<Bank>>(str,HttpStatus.FOUND);
		}
		return null; // Bank not found exception
	}
	public ResponseEntity<ResponseStructure<Bank>> setBranchToBank(int branchId,int bankId)
	{
		ResponseStructure<Bank> res = new ResponseStructure<>();
		if(dao.findBank(bankId)!=null) 
		{	
			Bank bank = dao.findBank(bankId);
			if(bdao.findBranch(branchId)!=null) 
			{
				Branch branch = bdao.findBranch(branchId);
				List<Branch> branches = new ArrayList<>();
				branches.add(branch);
				bank.setBranch(branches);
				res.setData(dao.updateBank(bankId,bank));
				res.setMsg("Branch with the ID::" +branchId+ "is been added to bank successfully");
				res.setStatus(HttpStatus.CREATED.value());
				
				return new ResponseEntity<ResponseStructure<Bank>>(res,HttpStatus.CREATED);
			}
			else {
				return null;// no Branch found exception
			}
		}
		else 
		{
			return null; // no Bank Found exception
		}
	}
	
	
	
	
}
