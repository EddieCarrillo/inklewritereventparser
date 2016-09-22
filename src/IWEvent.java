import java.util.List;

public class IWEvent {
	
	
	private int eventID;
	private boolean isNormal;
	private List<Integer> actionId;
	private List<Object> content;
	

	public boolean isNormal() {
		return isNormal;
	}

	public void setNormal(boolean isNormal) {
		this.isNormal = isNormal;
	}

	public List<Integer> getActionId() {
		return actionId;
	}

	public void setActionId(List<Integer> actionId) {
		this.actionId = actionId;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public List<Object> getContent() {
		return content;
	}

	public void setContent(List<Object> content) {
		this.content = content;
	}

}
