package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentServices onlinePaymentServices;

	public ContractService(OnlinePaymentServices onlinePaymentServices) {
		this.onlinePaymentServices = onlinePaymentServices;
	}

	public void processContract(Contract contract, int months) {
		double basicQuota = contract.getTotalValue() / months;
		for (int i = 1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updateQuota = basicQuota + onlinePaymentServices.interest(basicQuota, i);
			double fullQuota = updateQuota + onlinePaymentServices.paymentFee(updateQuota);
			contract.addInstallments(new Installment(date, fullQuota));
		}
	}

	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

}
