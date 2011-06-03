package valueObjects
{
	[RemoteClass(alias="com.suntek.model.ContributionItem")]	
	
	[Bindable]	
	public class ContributionItem
	{		
		public var userId:String;
		public var date:Date;
		public var type:String;
		public var privatePay:int;
		public var hmo:int;		
		public var ac:int;
		public var acChild:int;
		public var ccc:int;
		public var cccChild:int;
		public var sf:int;
		public var other:int;		
		
		public function ContributionItem()
		{
		}
		
		public function calcTotal():int
		{
			var total:int;
			total = privatePay + hmo + ac + acChild + ccc + cccChild + sf + other;
			return total;
		}
	}
}