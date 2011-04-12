package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Employee")]
	
	public class Employee
	{
		public var id:int;
		public var title:String;
		public var lastName:String;
		public var middleName:String;
		public var firstName:String;
		
		public function Employee()
		{
		}
	}
}