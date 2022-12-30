package com.atguigu.team.service;

/**
 * 
 * @Description  自定义异常类
 * @author Mendy   
 * @version
 * @date 2022年12月30日上午10:01:20
 */
public class TeamException extends Exception{
	
	static final long serialVersionUID = -7034897193246939L;
	
	public TeamException(){
		super();
	}
	
	public TeamException(String msg){
		super(msg);
	}

	
	
}
