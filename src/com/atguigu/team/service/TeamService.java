package com.atguigu.team.service;

import java.security.Timestamp;

import com.atguigu.team.domain.Architect;
import com.atguigu.team.domain.Designer;
import com.atguigu.team.domain.Employee;
import com.atguigu.team.domain.Programmer;

/**
 * 关于开发团队成员的管理：添加、删除等。
 * @Description
 * @author Mendy   
 * @version
 * @date 2022年12月30日下午12:17:00
 */
public class TeamService {
	private static int counter = 1; //1、给memberId赋值
	private final int MAX_MEMBER = 5; //2、限制开发团队的人数
	private Programmer[] team = new Programmer[MAX_MEMBER];//3、保存开发团队成员
	private int total;//4、记录开发团队中实际的人数
	
	public TeamService() {
		super();
	}
	
	/**
	 * 获取开发团队中的所有成员
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日下午12:59:38
	 * @return
	 */
	public Programmer[] getTeams() {
		// 5、因为开发团对的人数不一定就是5人，也许2人，所以不能返回team，
		//   需要再造一个以实际人数长度为准的数组，返回
		Programmer[] team = new Programmer[total];
		for(int i=0; i<team.length; i++) {
			team[i] = this.team[i];
		}
		
		return team;
	}
	
	
	/**
	 * 将指定的员工添加到开发团队中
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日下午3:27:07
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException{
//		成员已满，无法添加
		if(total >= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		
//		该成员不是开发人员，无法添加
		if(!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		
//		该员工已在本开发团队中
		//6、这里需要遍历，需要写很多行代码，所以给它制作成一个方法
		if(isExit(e)) {
			throw new TeamException("该员工已在本开发团队中");
		};
		
//		该员工已是某团队成员 
//		该员正在休假，无法添加
		
		//能走到这，说明一定是Programmer了，因为如果有异常，及已经在前面被抛出了
		//所以可以大胆强转
		Programmer p = (Programmer) e;
		
//		if(p.getStatus().getNAME().equals("BUSY")) {
//			
//		}
		//上面的写法不如下面的写法好，因为下面的写法可以保证前面的内容一定不是空指针
		if("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {
			throw new TeamException("该员工已是某团队成员");
		}else if("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())){
			throw new TeamException("该员正在休假，无法添加");
		}
		
		
		
//		团队中至多只能有一名架构师
//		团队中至多只能有两名设计师
//		团队中至多只能有三名程序员
		
		
		//获取team中已有成员中 架构师、设计师、程序员的人数
		int numOfArch = 0, numOfDes = 0, numOfPro = 0;
		for(int i=0; i< total;i++) {
			if(team[i] instanceof Architect) {
				numOfArch++;
			}else if(team[i] instanceof Designer) {
				numOfDes++;
			}else if(team[i] instanceof Programmer) {
				numOfPro++;
			}
		}
		
		if(p instanceof Architect) {
			if(numOfArch >= 1) {
				throw new TeamException("团队中至多只能有一名架构师");
			}
		}else if (p instanceof Designer) {
			if(numOfDes >=2) {
				throw new TeamException("团队中至多只能有两名设计师");
			}
		}else if(p instanceof Programmer) {
			if(numOfPro >=3) {
				throw new TeamException("团队中至多只能有三名程序员");
			}
		}
		
		
		//凡是能走到这里的，那么以上的那些异常情况就都排除掉了
		//将p（或e）添加到现有的team中
		team[total++] = p;
		
		//p的属性赋值
		p.setStatus(Status.BUSY);
		p.setMemberId(counter++);
		
		
		

	}
	
	
	
	
	
	/**
	 * 判断指定的员工是否已经存在于现有的开发团队中
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日下午3:39:27
	 * @param e
	 * @return
	 */
	private boolean isExit(Employee e) {
		for(int i=0; i<total; i++) {
			return team[i].getId() == e.getId();
		}
		return false;
	}

	
	
	
	/**
	 * 从团队中删除成员
	 * @Description
	 * @author Mendy 
	 * @date 2022年12月30日下午5:09:57
	 * @param memberId
	 * @throws TeamException
	 */
	public void removeMmber(int memberId) throws TeamException{
		int i;
		for(i=0; i<total; i++) {
			if(team[i].getId() == memberId) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		
		if(i== total) {
			//说明for循环都结束了，也没有找到指定的id，也就是不存在
			throw new TeamException("知道不到指定memberId的员工，删除失败");
		}
		
		//删除一个元素后，后一个元素覆盖前一个元素
		for(int j = i; j<total-1; j++) {
			team[j] = team[j+1];
		}


		//，所有元素都向前挪动以后，将最后一个元素设置为空
//		team[total-1] = null;
//		total--
//      或简化写法
		team[--total] = null;
		
		
		
	}
	
	
	
	
	
	
	
	
}
