import java.util.EnumSet;


public enum CellType {
	BLANK,
	OBSTACLE,
	ROCK,
	PAD,
	ROCKONPAD,
	TELEPORTAL,
	ROCKONTELEPORTAL;
	
	public static EnumSet<CellType> getRockTypes(){
		return EnumSet.of(CellType.ROCK, CellType.ROCKONPAD, CellType.ROCKONTELEPORTAL);
	}
	public static EnumSet<CellType> getPadTypes(){
		return EnumSet.of(CellType.PAD, CellType.ROCKONPAD);
	}
}
