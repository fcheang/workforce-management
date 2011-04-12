package events
{
	import flash.events.Event;
	
	import valueObjects.User;
	
	public class LoginEvent extends Event
	{
		public var user:User;
		
		public function LoginEvent(type:String, user:User)
		{
			super(type);
			this.user = user;
		}
		
		override public function clone():Event
		{
			return new LoginEvent(type, user);
		}
	}
}