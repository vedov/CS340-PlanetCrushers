package logic;


public class Settings {
	public static int row_size,column_size;
	public static Boolean difficulty;
	 /**
	   * SET MODE ( NORMAL OR HARD )
	   * @param difficulty
	   */
	  public static void setMode(Boolean difficulty) {
		  Settings.difficulty = difficulty;
	  }
	  
	  /**
	   * SET DEFAULT MODE (NORMAL)
	   * @param difficulty (FALSE)
	   */
	  public static void setNormalMode(Boolean difficulty) {
		  Settings.difficulty = difficulty;
		  Settings.row_size = 6;
		  Settings.column_size = 10;
	  }
	  
	  /**
	   * SET ROW SIZE DEPENDING ON DIFFICULTY
	   * @param row_size
	   */
	  public static void setRowSize(int row_size) {
		  Settings.row_size = row_size;
	  }
	  
	  /**
	   * SET COLUMN SIZE BASED ON DIFFICULTY
	   * @param column_size
	   */
	  public static void setColumnSize(int column_size) {
		  Settings.column_size = column_size;
	  }
	  
	  /**
	   * GET DIFFICULTY
	   * @return
	   */
	  public static Boolean getMode() {
		  return difficulty;
	  }
	  
	  /**
	   * GET ROW SIZE
	   * @return
	   */
	  public static int getRowSize() {
		  return row_size;
	  }
	  
	  /**
	   * GET COLUMN SIZE
	   * @return
	   */
	  public static int getColumnSize() {
		  return column_size;
	  }
	  
	public Settings() {
		// TODO Auto-generated constructor stub
	}

}
