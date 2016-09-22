import java.time.LocalDate;


public abstract class AccountOperation 
{
	private final float value;
	private final LocalDate date;//== LocalDate.now();
	private final long accountNumber;
	
	public AccountOperation (long accountNumber, float value)
	{
		this.accountNumber = accountNumber;
		this.value = value;
		this.date = LocalDate.now();
	}
	
	public float getValue()
	{
		return this.value;
	}
	
	public LocalDate getDate()
	{
		return this.date;
	}
	
	public long getAccountNumber()
	{
		return this.accountNumber;
	}
}
