public class SheetData {
    public static final String FILE_NAME = "EventData.xls";
    public static final String JSON_FILE = "EnigmaStory.json"; 
    public static final int COLUMN_ID = 0;
    public static final int COLUMN_ACTION_1_ID = 1;
    public static final int COLUMN_ACTION_2_ID = 2;
    public static final int COLUMN_ACTION_3_ID = 3;
    public static final int COLUMN_ACTION_4_ID = 4;
    public static final int COLUMN_ACTION_1_BUTTON_TEXT = 5;
    public static final int COLUMN_ACTION_2_BUTTON_TEXT = 6;
    public static final int COLUMN_ACTION_3_BUTTON_TEXT = 7;
    public static final int COLUMN_ACTION_4_BUTTON_TEXT = 8;
    public static final int COLUMN_CURRENT_EVENT_TEXT = 9;
    public static final int COLUMN_LOCATION = 10;
    public static final int COLUMN_ENVIRON_TEXT_1 = 11;
    public static final int COLUMN_ENVIRON_TEXT_2 = 12;
    public static final int COLUMN_ENVIRON_TEXT_3 = 13;
    public static final int COLUMN_ENVIRON_TEXT_4 = 14;
    public static final int COLUMN_ENVIRON_INTERACTION_1 = 15;
    public static final int COLUMN_ENVIRON_INTERACTION_2 = 16;
    public static final int COLUMN_ENVIRON_INTERACTION_3 = 17;
    public static final int COLUMN_ENVIRON_INTERACTION_4 = 18;
    public static final int COLUMN_DISCRIMINATOR = 19;
    public static final int COLUMN_HAS_SPECIAL = 20;
    public static final int COLUMN_VIBRATION = 21;
    public static final int COLUMN_TOTAL_ROWS= 22;
    public static final int LAST_COL = COLUMN_TOTAL_ROWS;
    public static final String COLUMN_NAME_ID = "id";
	public static final String COLUMN_NAME_ACTION_1_ID = "action_id_1";
	public static final String COLUMN_NAME_ACTION_2_ID = "action_id_2";
	public static final String COLUMN_NAME_ACTION_3_ID = "action_id_3";
	public static final String COLUMN_NAME_ACTION_4_ID = "action_id_4";
	public static final String COLUMN_NAME_ACTION_1_BUTTON_TEXT = "button_1_text";
	public static final String COLUMN_NAME_ACTION_2_BUTTON_TEXT = "button_2_text";
	public static final String COLUMN_NAME_ACTION_3_BUTTON_TEXT = "button_3_text";
	public static final String COLUMN_NAME_ACTION_4_BUTTON_TEXT = "button_4_text";
	public static final String COLUMN_NAME_CURRENT_EVENT_TEXT = "current_event_text";
	public static final String COLUMN_NAME_LOCATION = "location";
	public static final String COLUMN_NAME_ENVIRON_TEXT_1 = "environment_text_1";
	public static final String COLUMN_NAME_ENVIRON_TEXT_2 = "environment_text_2";
	public static final String COLUMN_NAME_ENVIRON_TEXT_3 = "environment_text_3";
	public static final String COLUMN_NAME_ENVIRON_TEXT_4 = "environment_text_4";
	public static final String COLUMN_NAME_ENVIRON_INTERACTION_1 = "environ_interact_1";
	public static final String COLUMN_NAME_ENVIRON_INTERACTION_2 = "environ_interact_2";
	public static final String COLUMN_NAME_ENVIRON_INTERACTION_3 = "environ_interact_3";
	public static final String COLUMN_NAME_ENVIRON_INTERACTION_4 = "environ_interact_4";
	public static final String COLUMN_NAME_DISCRIMINATOR = "discriminator";
	public static final String COLUMN_NAME_HAS_SPECIAL = "has_special";
	public static final String COLUMN_NAME_VIBRATION = "vibration";
	public static final String COLUMN_NAME_TOTAL_ROWS = "total_rows";
    public static final String NO_STRING_DATA = "D.N.E.";
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final int NO_ID_DATA = -1;
    
    public static String getColumnName(int column){
    	switch(column){
    	case COLUMN_ID:
    		return COLUMN_NAME_ID;
    	case COLUMN_ACTION_1_ID:
    		return COLUMN_NAME_ACTION_1_ID;
    	case COLUMN_ACTION_2_ID:
    		return COLUMN_NAME_ACTION_2_ID;
    	case COLUMN_ACTION_3_ID:
    		return COLUMN_NAME_ACTION_3_ID;
    	case COLUMN_ACTION_4_ID:
    		return COLUMN_NAME_ACTION_4_ID;
    	case COLUMN_ACTION_1_BUTTON_TEXT:
    		return COLUMN_NAME_ACTION_1_BUTTON_TEXT;
    	case COLUMN_ACTION_2_BUTTON_TEXT:
    		return COLUMN_NAME_ACTION_2_BUTTON_TEXT;
    	case COLUMN_ACTION_3_BUTTON_TEXT:
    		return COLUMN_NAME_ACTION_3_BUTTON_TEXT;
    	case COLUMN_ACTION_4_BUTTON_TEXT:
    		return COLUMN_NAME_ACTION_4_BUTTON_TEXT;
    	case COLUMN_CURRENT_EVENT_TEXT:
    		return COLUMN_NAME_CURRENT_EVENT_TEXT;
    	case COLUMN_LOCATION:
    		return COLUMN_NAME_LOCATION;
    	case COLUMN_ENVIRON_TEXT_1:
    		return COLUMN_NAME_ENVIRON_TEXT_1;
    	case COLUMN_ENVIRON_TEXT_2:
    		return COLUMN_NAME_ENVIRON_TEXT_2;
    	case COLUMN_ENVIRON_TEXT_3:
    		return COLUMN_NAME_ENVIRON_TEXT_3;
    	case COLUMN_ENVIRON_TEXT_4:
    		return COLUMN_NAME_ENVIRON_TEXT_4;
    	case COLUMN_ENVIRON_INTERACTION_1:
    		return COLUMN_NAME_ENVIRON_INTERACTION_1;
    	case COLUMN_ENVIRON_INTERACTION_2:
    		return COLUMN_NAME_ENVIRON_INTERACTION_2;
    	case COLUMN_ENVIRON_INTERACTION_3:
    		return COLUMN_NAME_ENVIRON_INTERACTION_3;
    	case COLUMN_ENVIRON_INTERACTION_4:
    		return COLUMN_NAME_ENVIRON_INTERACTION_4;
    	case COLUMN_DISCRIMINATOR:
    		return COLUMN_NAME_DISCRIMINATOR;
    	case COLUMN_HAS_SPECIAL:
    		return COLUMN_NAME_HAS_SPECIAL;
    	case COLUMN_TOTAL_ROWS:
    		return COLUMN_NAME_TOTAL_ROWS;
    	}
    	return COLUMN_NAME_VIBRATION;
    	
    }
    
}