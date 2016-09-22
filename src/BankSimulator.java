import java.util.Vector;


public class BankSimulator 
{
	private AccountDatabase accountDatabase = null;
	private Vector<AccountOperator> vOperator = null;
	
	
	public BankSimulator(int accountDatabaseSize, int operatorCount)
	{
		accountDatabase = new AccountDatabase();
		for(int i=0; i<accountDatabaseSize; i++)
			accountDatabase.putAccount(new CheckingAccount(1000));//initial balance is 1000
		
		vOperator = new Vector<>();
		for(int i=0; i<operatorCount; i++) //the threads
			vOperator.add(new AccountOperator(accountDatabase, 10)); //all operators will work with a same value for credit and debt
	}

	public void runSimulation()
	{
		Vector<Thread> vThread = new Vector<>();
		for(int i=0; i<vOperator.size(); i++) //the threads
		{
			Thread t = new Thread (vOperator.get(i));
			vThread.add(t);
			t.start();
		}
		
		for(int i=0; i<vThread.size(); i++)
		{
			try 
			{
				vThread.get(i).join();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		for(int number=1; number<=accountDatabase.size(); number++)
		{
			Account account = accountDatabase.getAccount(number);
			System.out.printf("\naccount: %5d have a balance of: %10.2f", number, account.getBalance());
		}
	}
	
	public static void main(String[] args)
	{
		BankSimulator bs = new BankSimulator(10,20);
		bs.runSimulation();
		System.out.println("\nsimulation was finished");
	}
}
