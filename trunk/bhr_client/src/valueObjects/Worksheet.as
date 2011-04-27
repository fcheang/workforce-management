package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Worksheet")]
	
	[Bindable]
	public class Worksheet
	{		
		public var empId:int;
		public var empName:String;
		public var date:Date;
		public var clinic:String;
 		public var hrsWorked:Number;
		public var countySeen:int;
		public var cccSeen:int;
		public var hmoSeen:int;
		public var otherSeen:int;
		public var countyFaceMin:int;
		public var countyOtherMin:int;
		public var cccFaceMin:int;
		public var cccOtherMin:int;
		public var hmoFaceMin:int;
		public var otherFaceMin:int;	
		public var numScheduled:int;
		public var numNoShow:int;
		public var numCancel:int;
		public var numNew:int;
		public var numDropin:int;	
		public var dailySalary:Number; 

		//calculated fields
		public var totalSeen:int;
		public var totalFaceHours:Number;
		public var totalOtherHours:Number;
		public var productivity:Number;
		public var countyFaceRevenue:Number;
		public var countyOtherRevenue:Number;
		public var cccFaceRevenue:Number;
		public var totalRevenue:Number;
		public var balance:Number;
		
		public function Worksheet()
		{
		}		
	}
}