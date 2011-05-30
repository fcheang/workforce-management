package valueObjects
{	
	[RemoteClass(alias="com.suntek.model.User")]
	
	public class User
	{
		public var username:String;
		public var password:String;
		public var firstname:String;
		public var lastname:String;
		
		public function User()
		{
		}
		
		public function getDisplayName():String
		{
			if (firstname != null && firstname != "" && lastname != null && lastname != ""){
				return firstname + " " + lastname;
			}else{
				return username;
			}
		}
	}
}