package tk.mctechniclp.devathlon.spells;

public enum SpellPriority {
	
	MAGICK((byte) 5),
	HIGHEST((byte) 4),
	HIGH((byte) 3),
	NORMAL((byte) 2),
	LOW((byte) 1),
	LOWEST((byte) 0);
	
	private byte p;
	
	SpellPriority(byte p) {
		this.p = p;
	}
	
	public byte getNumericRepresentation() {
		return p;
	}
	
	public boolean higherThan(SpellPriority sp) {
		return sp.getNumericRepresentation() > p;
	}
	
}
