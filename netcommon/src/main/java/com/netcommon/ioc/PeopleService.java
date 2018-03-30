package com.netcommon.ioc;

public class PeopleService {

	private NameService nameService;
	private GenderService genderService;
	private JobsService jobsService;
	
	public NameService getNameService() {
		return nameService;
	}

	public void setNameService(NameService nameService) {
		this.nameService = nameService;
	}

	public GenderService getGenderService() {
		return genderService;
	}

	public void setGenderService(GenderService genderService) {
		this.genderService = genderService;
	}

	public JobsService getJobsService() {
		return jobsService;
	}

	public void setJobsService(JobsService jobsService) {
		this.jobsService = jobsService;
	}

	public void introduce() {
		System.out.println("开始介绍 ：");
		nameService.name();
		genderService.gender();
		jobsService.job();
	}
}
