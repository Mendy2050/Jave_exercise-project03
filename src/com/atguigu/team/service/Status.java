package com.atguigu.team.service;

/**
 * 
 * @Description 表示员工的状态
 * @author Mendy   
 * @version
 * @date 2022年12月28日下午8:03:31
 */
public class Status {
	
	private final String NAME;
	
	private Status(String name) {
		this.NAME = name;
	}


	public static final Status FREE = new Status("FREE");
	public static final Status BUSY = new Status("BUSY");
	public static final Status VOCATION = new Status("VOCATION"); //这里其实类似代理模式了，其实是枚举

	public String getNAME() {
		return NAME;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return NAME;
	}

	
	
	
	
}
