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
		
		public function Worksheet()
		{
		}
		
		public function calcProductivity():Number
		{
			if (hrsWorked > 0){
				return (countyFaceMin + cccFaceMin + hmoFaceMin + otherFaceMin + countySeen + cccSeen + hmoSeen + otherSeen) / (hrsWorked * 60);
			}else{
				return 0;
			}
		}
	}
}