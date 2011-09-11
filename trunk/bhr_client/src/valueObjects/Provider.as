package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Provider")]
	
	[Bindable]
	public class Provider
	{
		public var providerId:int;
		public var name:String;
		public var title:String;
		
		public function Provider()
		{
		}
		
	}
}