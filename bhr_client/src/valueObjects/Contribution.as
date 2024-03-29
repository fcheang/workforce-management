package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Contribution")]
	
	[Bindable]	
	public class Contribution
	{
		public var userId:String;
		public var date:Date;
		public var hrsWorked:Number;
		public var authsEntered:String;
		public var interpretersOrdered:String;
		public var collateralReceived:String;
		public var other:String;
		
		public function Contribution()
		{
		}
		
		public function get hrsWorkedStr():String
		{
			return hrsWorked.toString();	
		}
		
		public function set hrsWorkedStr(hrsWorkedStr:String):void
		{
			hrsWorked = Number(hrsWorkedStr);
		}
	}
}