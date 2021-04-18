class ClientRequest{
	public OpCode op_code;
	public int shift_value;
	public String message;
	
	public ClientRequest(String s) throws Exception{
		this.parse(s);
	}
	
	private void parse(String s) throws Exception{
		//do all the decoding of the string and initialize the three fields accordingly
		//do all the validation here and throw the error to the server. The server catches and notifies the user that they entered a bad request
		String[] arr;
		
		arr = s.split(" ", 2);
		
		if(arr[0].equals(OpCode.ENCRYPT.getValue())){
			op_code = OpCode.ENCRYPT;
			
			try{
				arr = arr[1].split(" ", 2);
			}
			catch(Exception e){
				throw new Exception(ResponseCode.BAD_REQUEST.toString());
			}
			
			try{
				shift_value = Integer.parseInt(arr[0]);
			}
			catch(Exception e){
			throw new Exception(ResponseCode.BAD_SHIFT_VALUE.toString());}
			
			try{
				if(isValidMessage(arr[1])){
					message = arr[1].substring(1, arr[1].length() - 1);
				}
				else
					throw new Exception(ResponseCode.BAD_REQUEST.toString());
			}
			catch(Exception e){
				throw new Exception(ResponseCode.BAD_REQUEST.toString());
			}
		}
		else if(arr[0].equals(OpCode.DECRYPT.getValue())){
			op_code = OpCode.DECRYPT;
			//find the message and stuff
			try{
				if(isValidMessage(arr[1])){
					message = arr[1].substring(1, arr[1].length() - 1);
				}
				else
					throw new Exception(ResponseCode.BAD_REQUEST.toString());
			}
			catch(Exception e){
				throw new Exception(ResponseCode.BAD_REQUEST.toString());
			}
		}
		else if(arr[0].equals(OpCode.HELP.getValue())){
			op_code = OpCode.HELP;
		}
		else if(arr[0].equals(OpCode.CLOSE.getValue())){
			throw new Exception(ResponseCode.BAD_REQUEST.toString());
		}
		else
			throw new Exception(ResponseCode.BAD_OP_CODE.toString());
	}
	
	private boolean isValidMessage(String s){
		return s.startsWith("[") && s.endsWith("]");
	}
}