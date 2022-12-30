package com.atguigu.team.view;

import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;
import com.atguigu.team.service.NameListService;
import com.atguigu.team.service.TeamException;
import com.atguigu.team.service.TeamService;


public class TeamView {
	
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();
	
	public TeamView() {
		
	}
	
	public void enterMainMenu() {
		
		boolean loopFlag = true;
		char menu=0;
		
		while(loopFlag) {
			
			if(menu != '1') {
				listAllEmployees();
			}
			
			System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
			menu = TSUtility.readMenuSelection();
			switch (menu) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;			
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.println("确认是否退出（Y/N）:");
				char isExit = TSUtility.readConfirmSelection();
				if(isExit == 'Y') {
					loopFlag = false;
				}
				break;			
			}
		}
		
	}
	
	
	/**
	 * 显示所有的员工信息
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日下午7:04:34
	 */
	private void listAllEmployees() {
//		System.out.println("显示公司所有的员工的信息");
		
		System.out.println("-------------------------------开发团队调度软件--------------------------------\n");
		Employee[] employees = listSvc.getAllEmployees();
		//1、严密的写法，不要只写employees.length==0，如果是null，那么employees.length就是空指针了
		//所以要这样写↓：
		if(employees == null || employees.length ==0) {
			System.out.println("公司中没有任何员工信息！");
		}else {
			System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
			for(int i=0; i<employees.length;i++) {
				System.out.println(employees[i].toString());
			}
		}
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	
	
	private void getTeam() {
//		System.out.println("查看开发团队情况");
		System.out.println("--------------------团队成员列表---------------------");
		Programmer[] team = teamSvc.getTeams();
		if(team == null || team.length == 0) {
			System.out.println("开发团队目前没有成员！");
		}else {
			System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
			for(int i=0; i<team.length;i++) {
				System.out.println(team[i].getDetailsForTeam());
			}
		}
	
		System.out.println("-----------------------------------------------------");

	}
	
	
	
	
	private void addMember() {
//		System.out.println("添加团队成员");
		System.out.println("---------------------添加成员---------------------");
		System.out.print("请输入要添加的员工ID：");
		int id = TSUtility.readInt();
		
		try {
			Employee emp = listSvc.getEmployee(id);//在这个方法中抛出过异常
			teamSvc.addMember(emp);//在这个方法中也抛出过异常
			System.out.println("添加成功");
			
		} catch (TeamException e) {
			System.out.println("添加失败，原因：" + e.getMessage());
		}
		
		//按回车键继续
		TSUtility.readReturn();
	}
	
	
	
	
	private void deleteMember() {
//		System.out.println("删除团队成员");
		System.out.println("---------------------删除成员---------------------");
		System.out.print("请输入要删除员工的TID：");
		int memberId = TSUtility.readInt();
		
		System.out.println("确认是否删除(Y/N)：");
		char isDelete = TSUtility.readConfirmSelection();
		if(isDelete == 'N') {
			return;
		}
		
		try {
			teamSvc.removeMmber(memberId);
			System.out.println("删除成功");
		} catch (TeamException e) {
			System.out.println("添加失败，原因：" + e.getMessage());
		}
		
		//按回车键继续
		TSUtility.readReturn();
		
		
	}
	
	public static void main(String[] args) {
		TeamView view = new TeamView();
		view.enterMainMenu();
	}
	
	

}
