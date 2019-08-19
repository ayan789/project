package com.neo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@Data
public class AgentUserDTO implements Serializable {
	private static final long serialVersionUID = 4302428871091766745L;
	public static final String PRUPOSITION_SELLER = "SELLER";
	public static final String ADMIN_PRU_RULE = "UOB_ADMIN";
	public static final String UOB_CHANNEL = "024";
	public static final String SCB_CHANNEL = "014";
	public static final String UOB_ADMIN_ROLE = "UOB_BBM";
	public static final String SCB_ADMIN_ROLE = "SCB_SAM";

	// Ldap fields

	private String userId;
	private String channel;
	private String firstName;
	private String lastName;
	private String fullName;
	private String licenceNumber;
	private String licenceIssueDate;
	private String licenceExpiryDate;
	private String licenceSingleNumber;
	private String licenceSingleIssueDate;
	private String licenceSingleExpiryDate;
	private String branch;
	private String branchName;
	private String pruPosition;
	private String mobile;
	private String email;
	private String pruRole;
	private String referral;
	private String referralName;
	private String eposRole;
	private String ulLicenseIssueDate;
	private String ulLicenseExpiryDate;
	private String hubDistrictCode;
	private String regionCode;
	private String bancassuranceChannelCode;
	private String ulLicenseNumber;
	private String managerEmail;
	private String agentTitle;
	private String agentCode;
	private String token;

	private Boolean isFirstLogin;

	private String initials;
	private String o;

	// 国际化
	private Locale locale;

	private List<AgentUserDTO> agents;

	private List<AgentUserDTO> age;
	private boolean isManager;

	public boolean getIsManager() {
		return checkManager();
	}

	public void setIsManager(boolean manager) {
		isManager = manager;
	}

	/**
	 * 是否管理者
	 *
	 * @return
	 */
	public Boolean checkManager() {
		return (isUOB(this.channel) && UOB_ADMIN_ROLE.equalsIgnoreCase(this.eposRole)) ||
				(isSCB(this.channel) && SCB_ADMIN_ROLE.equalsIgnoreCase(this.eposRole));
	}

	public static boolean isUOB(String channel){
		return UOB_CHANNEL.equalsIgnoreCase(channel);
	}

	public static boolean isSCB(String channel){
		return SCB_CHANNEL.equalsIgnoreCase(channel);
	}

}