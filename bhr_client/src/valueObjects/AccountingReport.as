package valueObjects
{
	[Bindable]
	[RemoteClass(alias="com.suntek.model.AccountingReport")]		
	public class AccountingReport
	{
		public var dateOfWeek:Date;
		public var accountId:int;
		public var accountName:String;
		public var numBills:int;
		public var amtPaid:Number;
		public var numChecks:int;
		public var amtChecks:Number;
		public var amtCashCollected:Number;
		public var numCheckDeposited:int;
		public var amtDeposited:Number;
		
		public function AccountingReport()
		{
		}
	}
}
