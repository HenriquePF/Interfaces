package model.services;

public class PayPalService implements OnlinePaymentServices{

	public static final double TAXA_PERCENTUAL = 0.02;
	public static final double JUROS_MENSAL = 0.01;
	
	@Override
	public double paymentFee(double amount) {
		return amount * 0.02;
	}

	@Override
	public double interest(double amount, int months) {
		return amount * 0.01 * months;
	}
}
