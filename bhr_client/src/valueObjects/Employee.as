package valueObjects
{
	[RemoteClass(alias="com.suntek.model.Employee")]

	[Bindable]
	public class Employee
	{
		public var empId:int;
		public var title:String;
		public var lastName:String;
		public var middleName:String;
		public var firstName:String;
		public var isActive:Boolean;
		public var fullName:String;
		
		public function Employee()
		{
		}
		
	}
}