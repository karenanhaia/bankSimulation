import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AccountDatabase 
{
	private Map<Long, Account> accountMap;
	private Map<Long, List<AccountOperation>> accountOperationMap;
	
	public AccountDatabase()
	{
		this.accountMap = new HashMap<>();
		this.accountOperationMap = new HashMap<>(); 
	}
	
	public Account getAccount(long accountNumber)
	{
		return this.accountMap.get(accountNumber);
	}

	public void putAccount(Account account)
	{
		this.accountMap.put(account.getNumber(), account);
		this.accountOperationMap.put(account.getNumber(), new LinkedList<AccountOperation>());
	}
	
	public void record(AccountOperation operation)
	{
		List<AccountOperation> lOp = this.accountOperationMap.get(operation.getAccountNumber());
		if (lOp == null)
			throw new RuntimeException("Account < " + operation.getAccountNumber() +" > was not found ");
		lOp.add(operation);
	}
	
	public int size()
	{
		return this.accountMap.size();
	}
}
