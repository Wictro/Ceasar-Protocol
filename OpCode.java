/*
* Types of OP codes we can recieve
*/
enum OpCode{
	ENCRYPT("ENCRYPT"), DECRYPT("DECRYPT"), CLOSE("CLOSE"), HELP("HELP");
	
	private String value;
	
	private OpCode(String s){
		value = s;
	}
	
	public String getValue(){
		return value;
	}
}