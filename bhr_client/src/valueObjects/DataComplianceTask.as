package valueObjects
{	
	[Bindable]
	[RemoteClass(alias="com.suntek.model.DataComplianceTask")]	
	public class DataComplianceTask
	{
		public var userId:String;
		public var date:Date;
		public var dateStr:String;
		public var epsOpened:int;
		public var billErrsFound:int;
		public var chartsReviewed:int;
		public var chartErrsFound:int;
		public var itemsEntered:int;
		
		public function DataComplianceTask()
		{
		}
				
	}
}