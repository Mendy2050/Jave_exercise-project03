package com.atguigu.team.junit;

import org.junit.Test;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;

/**
 * 对NameListService类的测试
 * @Description
 * @author Mendy   
 * @version
 * @date 2022年12月30日上午10:07:14
 */
public class NameListServiceTest {

	@Test
	public void testGetAllEmployees() {
		//造对象的时候，就相当于把构造器里的逻辑都执行了，执行后，数组及其数组元素就有相应的数据了
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for(int i=0; i<employees.length;i++) {
			System.out.println(employees[i].toString());
		}
	}
	
	
	@Test 
	public void testGetEmployee(){
		NameListService service = new NameListService();
		int id=19;
		
		try {
			Employee employee = service.getEmployee(id);
			System.out.println(employee);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
