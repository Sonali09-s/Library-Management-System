package LibrarayManagemnt.modalDto;

import jakarta.validation.constraints.NotNull;

public class MembershipRequestDto {
	@NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Plan name is required")
    private String planName; // e.g., "Monthly", "Quarterly"

    

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
