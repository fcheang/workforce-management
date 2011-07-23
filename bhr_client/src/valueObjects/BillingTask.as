package valueObjects
{
	[Bindable]
	[RemoteClass(alias="com.suntek.model.BillingTask")]		
	public class BillingTask
	{
		public var userId:String;
		public var date:Date;
		public var dateStr:String;
		
		public var claimsDroppedC:int;
		public var claimsDroppedP:int;
		public var errors:int;
		public var denials:int;
		public var followUps:int;
		public var appeals:int;
		public var hmoAmt:Number;
		public var acAmt:Number;
		public var cccAmt:Number;
		public var privateAmt:Number;
		public var otherAmt:Number;
		public var otherTask1:String;
		public var otherTask2:String;
		public var otherTask3:String;				
		
		public function BillingTask()
		{
		}
	}
}