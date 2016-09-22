import java.util.Map;

public class Data {
	private boolean optionMirroring;
	private String initial;
	private EditorData editorData;
	private Map<String,IWEvent> stitches;
	private boolean allowCheckPoints;
	
	
	public boolean isOptionMirroring() {
		return optionMirroring;
	}
	public void setOptionMirroring(boolean optionMirroring) {
		this.optionMirroring = optionMirroring;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public EditorData getEditorData() {
		return editorData;
	}
	public void setEditorData(EditorData editorData) {
		this.editorData = editorData;
	}
	public Map<String, IWEvent> getStiches() {
		return stitches;
	}
	public void setStiches(Map<String, IWEvent> stiches) {
		this.stitches = stiches;
	}
	public boolean isAllowCheckPoints() {
		return allowCheckPoints;
	}
	public void setAllowCheckPoints(boolean allowCheckPoints) {
		this.allowCheckPoints = allowCheckPoints;
	}
	
	
}
