package com.example.tlyanactiviti;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

	@GetMapping(value="/home")
	public String home(){
		return "tl-yan-activiti up";
	}

	@GetMapping(value="/aaa")
	public String aaa(){
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
				.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/db_activiti?useUnicode=true&characterEncoding=utf8").setJdbcUsername("root").setJdbcPassword("123456")
				.setJdbcDriver("com.mysql.jdbc.Driver")
				.setJobExecutorActivate(true)
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine processEngine = cfg.buildProcessEngine();
		String pName = processEngine.getName();
		String ver = ProcessEngine.VERSION;
		System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 流程部署
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("MultiTask2.bpmn").name("流程测试").category("").deploy();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
		System.out.println("流程名称 ： [" + processDefinition.getName() + "]， 流程ID ： [" + processDefinition.getId() + "], 流程KEY : " + processDefinition.getKey());

		// 启动流程
		RuntimeService runtimeService = processEngine.getRuntimeService();
		List<String> assigneeList = new ArrayList<String>(); //分配任务的人员
		assigneeList.add("tom");
		assigneeList.add("jeck");
		assigneeList.add("mary");
		Map<String, Object> vars = new HashMap<String, Object>(); //参数
		vars.put("assigneeList", assigneeList);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", vars);
		System.out.println("流程实例ID = " + processInstance.getId());
		System.out.println("正在活动的流程节点ID = " + processInstance.getActivityId());
		System.out.println("流程定义ID = " + processInstance.getProcessDefinitionId());

		// 查询指定人的任务
		// ============ 会签任务开始 ===========
		TaskService taskService = processEngine.getTaskService();
		List<Task> taskList1 = taskService.createTaskQuery().taskAssignee("mary").orderByTaskCreateTime().desc().list();
		System.out.println("taskList1 = " + taskList1);

		List<Task> taskList2 = taskService.createTaskQuery().taskAssignee("jeck").orderByTaskCreateTime().desc().list();
		System.out.println("taskList2 = " + taskList2);

		List<Task> taskList3 = taskService.createTaskQuery().taskAssignee("aahhhhh").orderByTaskCreateTime().desc().list();
		System.out.println("taskList3 = " + taskList3);

		return "ok";
	}

}
