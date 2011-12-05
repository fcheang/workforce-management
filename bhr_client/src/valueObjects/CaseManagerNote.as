package valueObjects
{
	[RemoteClass(alias="com.suntek.model.CaseManagerNote")]	
	
	[Bindable]	
	public class CaseManagerNote
	{		
		public var userId:String;
		public var dateOfWeek:Date;
		public var plan:String;
		public var action:String;
		public var assistanceNeeded:String;
		public var plansForNextWeek:String;		
		public var other:String;
		
		public function CaseManagerNote()
		{
		}		
	}
}