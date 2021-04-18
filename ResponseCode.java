enum ResponseCode{
	GOOD_REQUEST("", 0), BAD_OP_CODE("BAD OP CODE", 1), BAD_SHIFT_VALUE("SHIFT VALUE NOT AN INTEGER OR TOO LARGE!", 2), BAD_REQUEST("BAD REQUEST FORMAT!", 3),
	START_GREET("SERVER AT YOUR SERVICE!", 100 ), END_GREET("GOOD BYE!", 101);
	
	private String value;
	private int number;
	
	private ResponseCode(String s, int i){
		value = s;
		number = i;
	}
	
	public String getValue(){
		return value;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String toString(){
		return "Response Code " + number + ": " + value;
	}
}