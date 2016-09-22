
public class AccountOperator implements Runnable
{
	private AccountDatabase database = null;
	private float value;
	
	public AccountOperator(AccountDatabase database, float value)
	{
		this.database = database;
		this.value = value;
	}
	
	
	public void run()
	{
		for(int i=0; i<100000; i++)
		{
			long accountNumber = Math.round(Math.random()*this.database.size());
			if (accountNumber==0)
				accountNumber=1;
			Account account = this.database.getAccount(accountNumber);
			
			
			
			//credit
			{				
				AccountOperation operation = new AccountOperationCredit(accountNumber, value);
				this.database.record(operation);
				account.credit(value);
			}
			//debt
			{
				if(account.getBalance() >= value) //the balance is sufficient
				{
					AccountOperation operation = new AccountOperationDebt(accountNumber, value);
					this.database.record(operation);
					account.debt(value);
				}	
			}			
		}
	}

}
