import java.util.Map;




//Represents following json object....
	/*
	 * data: {
	 *     editorData:{
	 *        //some fields 
	 *     
	 *     }
	 *     
	 *     optionMirroring: Boolean
	 *     allowCheckPoints: Boolean
	 *     stitches: {
	 *     //Some key value pairs
	 *     
	 *     
	 *     
	 *     }
	 *     
	 *     
	 *     
	 * 
	 * 
	 * }
	 * 
	 * */
public class Data {
	private boolean optionMirroring;
	private String initialKey;
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
		return initialKey;
	}
	public void setInitial(String initial) {
		this.initialKey = initial;
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
