package external;

public class DefaultExceptionHandler {

	private String featurePackageName;
	private String featureName;
	private String programUnitName;
	private String eventName;
	private String exceptionName;

	public String getFeaturePackageName() {
		return featurePackageName;
	}

	public void setFeaturePackageName(String featurePackageName) {
		this.featurePackageName = featurePackageName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getProgramUnitName() {
		return programUnitName;
	}

	public void setProgramUnitName(String programUnitName) {
		this.programUnitName = programUnitName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public void printException() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n!!!Warnings\n\n");
		sb.append("There is Exception:");
		sb.append(this.exceptionName);
		sb.append(" come out from event");
		sb.append(this.eventName);
		sb.append(" in ");
		sb.append(this.featurePackageName);
		sb.append(".");
		sb.append(this.featureName);
		sb.append(".");
		sb.append(this.programUnitName);
		System.out.println(sb);
	}

}
