package valueObjects
{
	[Bindable]
	[RemoteClass(alias="com.suntek.model.CaseManagerReport")]		
	public class CaseManagerReport
	{
		public var userId:String;
		public var date:Date;
		public var dateStr:String;
		
		public var numConsumer:int;
		public var numVisits:int;
		public var numL2Ref:int;
		public var numL3Ref:int;
		public var numL2Seen:int;
		public var numL3Seen:int;
		public var numPCPReachedOut:int;
		public var numPCPAppts:int;
		public var numCM:int;		
		public var numEpisodeOpened:int;
		public var numEpisodeClosed:int;
		public var numHPOnCaseloadDueToExpire:int;
		public var numOutsideMeeting:int;
		public var numVisitNextWeek:int;
		public var numNonCompliantChart:int;
				
		public function CaseManagerReport()
		{
		}
	}
}