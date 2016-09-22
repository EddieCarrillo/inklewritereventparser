import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataWriter {
	
	private static final int DIALOG_EVENT = 0;
	private static final int NORMAL_EVENT = 1;
	private static final int DEAD_EVENT = 2;

	
	public static void main(String[] args) throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File(SheetData.FILE_NAME));
		WritableSheet sheet = workbook.createSheet("FirstSheet", 0);
		writeOutColumnNames(sheet);

		String jsonString = new JsonReader().getJsonString(SheetData.JSON_FILE);
		// Create gson parser object
		Gson parser = new Gson();
		// Story object that maps data from JSON string
		Story story = parser.fromJson(jsonString, Story.class);
		// Grab the gamedata from the current story
		Data gameData = story.getData();
		// Take all of the event data from the game data
		Map<String, IWEvent> eventMap = gameData.getStiches();
		// Write out all of the events to the table.
		writeEventsToTable(eventMap, sheet);
		// Create links between events
		mapEventLinks(eventMap, sheet);
		// Write out all work into the workbook
		workbook.write();
		System.out.println("workbook written!");
		// Close resources
		workbook.close();
		System.out.println("workbook closed!");

	}

	private static void mapEventLinks(Map<String, IWEvent> eventMap, WritableSheet sheet)
			throws RowsExceededException, WriteException {
		// Start at 2nd row b/c first is occupied by column names
		int row = 1;
		// Go through all of the events in the map
		for (Map.Entry<String, IWEvent> entry : eventMap.entrySet()) {
			// Grab the content from the current event
			IWEvent currEvent = entry.getValue();
			List<Object> contents = currEvent.getContent();
			searchEventContents(eventMap, sheet, row,currEvent);
			// Next event(row)
			row++;
			System.out.println("Event mapped!");
		}

	}

	private static void searchEventContents(Map<String, IWEvent> eventMap, WritableSheet sheet, int row,IWEvent event) throws WriteException, RowsExceededException {
		List<Object> contents = event.getContent();
		// Start at the first button column
		int currActionCol = SheetData.COLUMN_ACTION_1_BUTTON_TEXT;
		int currActionIdCol = SheetData.COLUMN_ACTION_1_ID;
		/*
		 * Go through the contents of the current event and look for every
		 * link to another event
		 */
		for (int i = 0; i < contents.size(); i++) {
			Object content = contents.get(i);
			// Check and see if the current content is an unknown type
			if (content.getClass().equals(LinkedTreeMap.class)) {
				LinkedTreeMap unknown = (LinkedTreeMap) content;
				// If has the options field then that is a link and therefore normal event
				if (unknown.containsKey("option")) {
					String buttonText = (String) unknown.get("option");
					System.out.println("buttonText: "  + buttonText);
					// Add button text cell to the table
					Label buttonTextCell = new Label(currActionCol, row, buttonText);
					sheet.addCell(buttonTextCell);
					// Get the event link
					String actionEventName = (String) unknown.get("linkPath");
					IWEvent actionEvent = eventMap.get(actionEventName);
					// Extract the ID and put into the table
					int actionEventId = actionEvent.getEventID();
					Number actionEventIdCell = new Number(currActionIdCol, row, actionEventId);
					sheet.addCell(actionEventIdCell);
					// Next columns
					currActionCol++;
					currActionIdCol++;
				}//Dialog event
				if(unknown.containsKey("divert")){
					System.out.println("Dialog event being mapped");
					String nextEventName = (String)unknown.get("divert");
					IWEvent nextEvent = eventMap.get(nextEventName);
					int nextEventID = nextEvent.getEventID();
					Number nextEventIdCell = new Number(SheetData.COLUMN_ACTION_1_ID,row,nextEventID);
					sheet.addCell(nextEventIdCell);
					//There is only one "next event" possible
					return;
				}

			}
			
		}
	}

	private static void writeOutColumnNames(WritableSheet sheet) throws RowsExceededException, WriteException {
		int firstRow = 0;
		int currentColumn;
		for (currentColumn = 0; currentColumn <= SheetData.LAST_COL; currentColumn++) {
			String colName = SheetData.getColumnName(currentColumn);
			Label colNameCell = new Label(currentColumn, firstRow, colName);
			sheet.addCell(colNameCell);
		}
		System.out.println("Finished writing out columns!");
	}

	private static void writeEventsToTable(Map<String, IWEvent> eventMap, WritableSheet sheet) throws Exception {
		//Start at second row b/c first is used to name columns
		int row = 1;
		int numRows = 0;
		int numEvents = eventMap.size();
		List<IWEvent> events = new ArrayList<IWEvent>(eventMap.values());
		for (Map.Entry<String, IWEvent> entry : eventMap.entrySet()) {
			IWEvent currentEvent = entry.getValue();
			// Add one because the first row is occupied with the column names
			addRowEvent(currentEvent, sheet, row);
			numRows++;
			//Here to prevent jxl from stoping to early when reading data(android)
			Number stubCell = new Number(SheetData.COLUMN_TOTAL_ROWS,row,numRows);
			sheet.addCell(stubCell);
			System.out.println("Row written");
			row++;
		}
		
	}

	/*
	 * Safe version (but slow): This function goes through each of the contents
	 * of of an event to look for the pageLabel field. This is the safest but
	 * slowest version. This version makes sure that you go through the whole
	 * list of contents.
	 */
	private static boolean isNormalEventSafe(IWEvent currentEvent) throws ItsAllRomansFaultException {
		// Grab the contents of the event
		List<Object> contents = currentEvent.getContent();
		// Go through all of the contents
		for (int i = 0; i < contents.size(); i++) {
			Object content = contents.get(i);
			// Check if it is not the event text (unknown type)
			if (content.getClass().equals(LinkedTreeMap.class)) {
				// If it is then check if it is the object holding
				// the pageLabel field.
				LinkedTreeMap unknown = (LinkedTreeMap) content;
				if (unknown.get("pageLabel") != null) {
					// If it has the pageLabel field then check if Roman
					// correctly marked
					// the event
					String pageLabel = (String) unknown.get("pageLabel");
					char eventMarker = pageLabel.charAt(0);
					// If Roman ruined the program then throw
					// ItsAllRomansFaultException
					if (eventMarker != 'N' || eventMarker != 'D')
						throw new ItsAllRomansFaultException("Roman forgot to put a 'D' or 'N'");
					// If Roman did everything correct then check to see if it
					// is a normal event.
					if (eventMarker == 'N')
						return true;
					else // eventMarker == 'D'
						return false;
				}
			}
		}
		return false;
	}

	/*
	 * Fast (possibly) This version of the function asusmes that the JSON
	 * maintains its pattern where in the contents list, it stores the object
	 * with the pageLabel field in last position in the list.T
	 */
	private static boolean isNormalEvent(IWEvent currentEvent) throws ItsAllRomansFaultException {
		List<Object> contents = currentEvent.getContent();
		int contentListSize = contents.size();
		try {
			// Grab the json object that is supposed to store pageLabel field
			LinkedTreeMap pageLabelJsonStr = (LinkedTreeMap) contents.get(contentListSize - 1);
			// If the json object does not have pageLabel field then just use
			// safe version and look through all contents
			if (!pageLabelJsonStr.containsKey("pageLabel")) {
				System.out.println("Failed...Now using safe version");
				return isNormalEventSafe(currentEvent);
			}
			// If it does have the pageLabel field then extract the data from it
			String pageLabel = (String) pageLabelJsonStr.get("pageLabel");
			// Check and see if the event is a normal or a dialogue event...
			char eventMarker = pageLabel.charAt(0);
			if (eventMarker == 'D')
				return false;
			if (eventMarker == 'N')
				return true;

			throw new ItsAllRomansFaultException("Roman did not use the correct format...");
		} catch (ClassCastException cce) {
			Class<?> type = contents.get(contentListSize - 1).getClass();
			System.out.println("This is not an unkown type: " + type);
			return isNormalEventSafe(currentEvent); // Can throw Its all Romans
													// Fault Exception
		} /*
			 * catch (ItsAllRomansFaultException rfe) { System.out.
			 * println("Now will manually check all of the contents to test " +
			 * "for normal or dialog event...."); // TODO: Create a function
			 * that manually checks for "options" field // to see if normal of
			 * dialogue event. }
			 */

	}
	
	private static int isDialogEventSafe(IWEvent currEvent){
		List<Object> contents = currEvent.getContent();
		boolean containsDialogLink;
		for(int i = 1; i < contents.size(); i++){
			Object content = contents.get(i);
			LinkedTreeMap mapJsonStr = (LinkedTreeMap)content;
		
			if(mapJsonStr.containsKey("linkPath")){
				return false;
			}
			
			if (mapJsonStr.containsKey("divert")){
				return true;
			}
			
		}
	}

	private static void addRowEvent(IWEvent event, WritableSheet sheet, int row) throws Exception {
		boolean isNormalEvent = isNormalEvent(event);
		if (isNormalEvent) {
			createNormalEvent(event, sheet, row);
		} else {
			createDialogEvent(event, sheet, row);
		}

	}

	private static void createNormalEvent(IWEvent event, WritableSheet sheet, int row) throws Exception {
		Label discriminator = new Label(SheetData.COLUMN_DISCRIMINATOR, row, "N");
		sheet.addCell(discriminator);
		event.setNormal(true);
		List<Object> contents = event.getContent();
		for (int i = 0; i < contents.size(); i++) {
			Object content = contents.get(i);
			// Map the event text
			if (content.getClass().equals(String.class)) {
				String eventText = (String) content;
				Label label = new Label(SheetData.COLUMN_CURRENT_EVENT_TEXT, row, eventText);
				sheet.addCell(label);
			}

			// Map unknown data types in the content JSON array of the
			// InkleWriter Event
			if (content.getClass().equals(LinkedTreeMap.class)) {
				LinkedTreeMap unknown = (LinkedTreeMap) content;
				mapUnknownType(unknown, sheet, event, row);
			}

		}

	}

	private static void createDialogEvent(IWEvent event, WritableSheet sheet, int row) throws Exception {
		// This column is used to distinguish between normal and dialog event
		Label discriminator = new Label(SheetData.COLUMN_DISCRIMINATOR, row, "D");
		sheet.addCell(discriminator);
		event.setNormal(false);
		// Grab the contents of the event
		List<Object> contents = event.getContent();
		// Go through all the contents and map them to the table
		for (int i = 0; i < contents.size(); i++) {
			Object content = contents.get(i);
			// Map the event text
			if (content.getClass().equals(String.class)) {
				String eventText = (String) content;
				Label eventTextLabel = new Label(SheetData.COLUMN_CURRENT_EVENT_TEXT, row, eventText);
				sheet.addCell(eventTextLabel);
			}
			// Map unknown data types in the content JSON array of the
			// InkleWriter Event
			if (content.getClass().equals(LinkedTreeMap.class)) {
				LinkedTreeMap unknown = (LinkedTreeMap) content;
				mapUnknownType(unknown, sheet, event, row);
			}
		}

	}

	private static void mapUnknownType(LinkedTreeMap unknown, WritableSheet sheet, IWEvent event, int row)
			throws Exception {
		if (unknown.containsKey("pageLabel")) {
			String pageLabel = (String) unknown.get("pageLabel");
			mapPageLabel(pageLabel, sheet, row);
		}
		if (unknown.containsKey("pageNum")) {
			int pageNum = (((Double) unknown.get("pageNum"))).intValue();
			mapPageNum(pageNum, sheet, event, row);
		}
	}

	private static void mapPageNum(int pageNum, WritableSheet sheet, IWEvent event, int row)
			throws RowsExceededException, WriteException {
		// Page numbers start from 1 while column starts from 0 hence subtract
		// one to compensate
		System.out.println("pageNumber (position): " + (pageNum-1) + "row: " + row);
		
		Number eventId = new Number(SheetData.COLUMN_ID, row, pageNum - 1);
		sheet.addCell(eventId);
		//Index = position -1
		event.setEventID(pageNum-1);

	}

	private static void mapPageLabel(String pageLabel, WritableSheet sheet, int row) throws Exception {
		// Find the location of the colon
		int locStartIdx = pageLabel.indexOf(":");
		// If there is no colon then Roman messed up...
		if (locStartIdx == -1){
			throw new ItsAllRomansFaultException("Roman forgot to put a colon...");
		}
		// The location is the text after the colon
		String location = pageLabel.substring(locStartIdx + 1);
		// Add data to the table
		Label locationCell = new Label(SheetData.COLUMN_LOCATION, row, location);
		sheet.addCell(locationCell);
	}
	
	private static void mapPageLabelWithNoMark(String pageLabel, WritableSheet sheet, int row){
		
	}
	
	
	

}
