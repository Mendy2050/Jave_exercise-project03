package com.atguigu.team.service;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Equipment;
import com.atguigu.team.domain.NoteBook;
import com.atguigu.team.domain.PC;
import com.atguigu.team.domain.Printer;
import com.atguigu.team.domain.Programmer;

import static com.atguigu.team.service.Data.*;

/**
 * 
 * @Description 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
 * @author Mendy   
 * @version
 * @date 2022年12月28日下午8:17:27
 */
public class NameListService {
	
	private Employee[] employees;

	/**
	 * 给employees数组、及数组元素进行初始化 
	 */
	public NameListService() {
//		1、根据项目提供的Data类构建相应大小的employees数组
//		2、再根据Data类中的数据构建不同的对象，
//		包括：Employee、Programmer、Designer和Architect对象、以及相关联的Equipment子类的对象
//		3、将对象存于数组中
		employees = new Employee[EMPLOYEES.length];
		
		for(int i=0; i<employees.length;i++) {
			//4、获取员工的类型
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			
			//5、获取员工的4个基本信息
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			Equipment equipment;//8、因为下面的PROGRAMMER和DESIGNER都可能用到它
			double bonus;
			int stock;
			
			switch(type) {
			case EMPLOYEE:
				          employees[i] = new Employee(id, name, age, salary);
				          break;
				          
			case PROGRAMMER:
				          //6、此处因为一行代码搞不定，所以在下面用一个方法来写
				          equipment = createEquipment(i);
				          employees[i] = new Programmer(id, name, age, salary, equipment);
		                  break;
		                  
			case DESIGNER:
				          equipment = createEquipment(i);
				          bonus = Double.parseDouble(EMPLOYEES[i][5]);
				          employees[i] = new Designer(id, name, age, salary, equipment, bonus);
		                  break;
		                  
			case ARCHITECT:
				          equipment = createEquipment(i);
		                  bonus = Double.parseDouble(EMPLOYEES[i][5]);
		                  stock = Integer.parseInt(EMPLOYEES[i][6]);
				          employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
		                  break;		                  
			}
		}

		
	}

	/**
	 * 
	 * @Description  获取指定index上的员工的设备
	 * @author Mendy 
	 * @date 2022年12月30日上午9:10:46
	 * @param i
	 * @return
	 */
	private Equipment createEquipment(int index) {
		int equipmentType = Integer.parseInt(EQUIPMENTS[index][0]);
		String modelOrName = EQUIPMENTS[index][1];//7、因为下面会重复使用
		
		
		switch(equipmentType) {
		case PC:
			          String display = EQUIPMENTS[index][2];
			          return new PC(modelOrName, display);
			          
		case NOTEBOOK:
                      double price = Double.parseDouble(EQUIPMENTS[index][2]);
                      return new NoteBook(modelOrName, price);
	                  
		case PRINTER:
			          String printerType = EQUIPMENTS[index][2];
	                  return new Printer(modelOrName, printerType);                
		}
		
		return null;
	}



	/**
	 * 获取当前所有员工
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日上午9:44:56
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	
	
	/**
	 * 获取指定ID的员工对象
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日上午9:45:55
	 * @param id
	 * @return
	 * @throws TeamException
	 */
	public Employee getEmployee(int id) throws TeamException{
		for(int i=0; i<employees.length;i++) {
			if(employees[i].getId() == id) {
				//9、这里涉及到Integer的内部类IntegerCache的-127 - 128的缓存了，所以用==是没问题的，
				//但正常情况下，凡是涉及到对象内的元素的对比了，这里其实就应该用equals()进行比对
				return employees[i];
			}
		}
		
		throw new TeamException("找不到指定的员工");
	}
	
	
	
	

}
