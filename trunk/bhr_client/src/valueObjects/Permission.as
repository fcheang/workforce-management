package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Permission")]
	
	[Bindable]
	public class Permission
	{
		public var id:int;
		public var object:String;
		public var userId:String;
		
		public function Permission()
		{
		}
		
	}
	
}