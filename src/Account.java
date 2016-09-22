import java.time.LocalDate;


public abstract class Account 
{
	private static long accountNumber=0;
	private static final Object lockAccountNumber = new Object();
	
	private final long number;
	private volatile float balance         = 0.0f;
	private final LocalDate since = LocalDate.now();
	//other attributes
	
	public Account (float balance)
	{
		this.balance = balance;
		synchronized(lockAccountNumber)
		{
			number = ++accountNumber;
		}
	}
	
	public float getBalance()
	{
		return this.balance;
	}
	
	public void credit(float value)
	{
		this.balance += value;
	}
	
	public void debt(float value)
	{
		this.balance -= value;
	}
	
	public final LocalDate openDate()
	{
		return this.since;
	}
	
	public final long getNumber()
	{
		return this.number;
	}
}
