package com.cpt.payments.service.impl.statushandler;

import org.springframework.stereotype.Service;

import com.cpt.payments.constant.TransactionStatusEnum;
import com.cpt.payments.dao.interfaces.TransactionDao;
import com.cpt.payments.dao.interfaces.TransactionLogDao;
import com.cpt.payments.dto.TransactionDTO;
import com.cpt.payments.dto.TransactionLog;
import com.cpt.payments.service.interfaces.TransactionStatusHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusHandler implements TransactionStatusHandler {

	private TransactionDao transactionDao;
	private TransactionLogDao transactionLogDao;
	
	public InitiatedStatusHandler(TransactionDao transactionDao,
			TransactionLogDao transactionLogDao) {
		this.transactionDao = transactionDao;
		this.transactionLogDao = transactionLogDao;
	}
	
	@Override
	public boolean processStatus(TransactionDTO transactionDTO) {
        log.info("Processing status for INITIATED||transaction:{}", transactionDTO);
        TransactionDTO txnBeforeUpdate = transactionDao.getTransaction(transactionDTO.getTxnReference());
        
		if (!canUpdateTransaction(
				txnBeforeUpdate.getTxnStatus(), transactionDTO.getTxnStatus())) {
			log.error("Cannot Update transaction||fromStatus:{}||toStatus:{}", 
					txnBeforeUpdate.getTxnStatus(), transactionDTO.getTxnStatus());
			return false;
		}
        
		boolean isTxnSaved = transactionDao.updateTransaction(transactionDTO);

		log.info("Transaction saved successfully||isTxnSaved:{}", isTxnSaved);
		
		TransactionLog transactionLog = TransactionLog.builder().transactionId(txnBeforeUpdate.getId())
				.txnFromStatus(txnBeforeUpdate.getTxnStatus())
				.txnToStatus(transactionDTO.getTxnStatus())
				.build();
		transactionLogDao.createTransactionLog(transactionLog);
		
		return isTxnSaved;
	}

}
