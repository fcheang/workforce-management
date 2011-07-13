package valueObjects
{	
	[Bindable]
	[RemoteClass(alias="com.suntek.model.URPersonnelTask")]	
	public class URPersonnelTask
	{
		public var userId:String;
		public var date:Date;
		public var dateStr:String;
		
		public var chartsReviewed:int;
		public var dischargeDone:int;
		public var billSvcProvided:int;
		public var epsOpened:int;
		public var chartsTransfered:int;
		public var ruCompleted:int;
		public var mdChartsAudited:int;
		public var mhsChartsAudited:int;
		public var txTeamMtgs:int;
		
		public function URPersonnelTask()
		{
		}
		
	}
}