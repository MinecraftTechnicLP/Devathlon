# MineMagikca

Das MineMagika Plugin implementiert ein Zauberspruchsystem, das an das System des Spiels Magicka angelehnt ist. 

## Verwendung:
Das MineMagicka Plugin kann auch auf einem Survival Server verwendet werden. Durch Springen und Sneaken gleichzeitig können Spieler den "SpellMode" aktivieren. Bis auf das Item, das sie in der Hand halten geleert. Dafür bekommen sie in die anderen Hotbar-Slots Farbstoffe, die jeweils eines der Elemente Wasser, Erde, Kälte, Feuer, Elektrizität, Heilung, Schaden und Schild repräsentieren. Leider hatte ich nicht die Zeit ein eigenes RessourcePack zu implementieren und statt den Farbstoffen entsprechend umtexturierte, beschädigte Items mit unbreakable Tag zu verwenden.

Wechselt der Spieler auf einen Anderen Slot, so fügt er damit das entsprechende Element zu seinem Zauberspruch hinzu. Ein Zauberspruch besteht aus 5 verscheidenen Elementen. Der aktuelle Zauberspruch wird in der sogenannten ActionBar über der Hotbar angezeigt. Leider hatte ich auch hier nicht die Zeit, für jedes Element ein passendes Symbol zu finden.

Mit einem Rechtsklick kann der Spieler den Zauberspruch ausführen, sofern alle Elemente belegt sind. Wenn der Spieler dabei sneakt, so wendet er den Zauberspruch auf sich selbst an,

##Zaubersprüche
Die Zaubersprüche setzen sich wie bereits erwähnt aus fünf Elementen zusammen. Hierbei hat jedes Element bestimmte Eigenschaften, Schild bildet Schilde (u don't say :O) mit entsprechenden elementaren Eigenschaften (Feuer Schilde zünden Spieler an, Kälte Schilde verlangsamen sie, etc) Schaden bildet Strahlen, die ebenfalls je nach Element unterschiedlichen Schaden verursachen.

Es sind bisher leider kaum Zaubersprüche implementiert, dafür ist es aber umso einfacher, für Entwickler, eigene Hinzuzufügen:

##API

Um einen eigenen Zauberspruch hinzuzufügen, muss zuerst eine Klasse, die SelfSpell oder TargetSpell erweitert erstellt werden. SelfSpell, wenn der Zauberspruch auf den Spieler selbst angewendet werden soll (er sneakt), TargetSpell wenn nicht.

```Java
public class MySpell extends SelfSpell {
```

Anschließend müssen zwei verscheidene Constructors hinzugefügt werden. Einer übergibt die Elemente mit denen der Zauberspruch ausgeführt wurde und die Elemente, die ein Spieler verwenden muss, damit dieser Zauberspruch ausgeführt werden kann, sollte es keinen höher priorisierten geben. Der andere Constructor übergibt nur das zweite Argument und sollte ausschließlich zum registrieren verwendet werdenö
```Java
	public MySpell(Element[] elements) {
		super(elements, new Element[] {Element.SHIELD});
	}
	
	/**
	 * Only Use this constructor to register the Spell
	 * @param required required Elements
	 */
	public MySpell() {
		super(new Element[] {Element.SHIELD});
	}
```

Als nächstes muss die Methode fire() überschrieben werden. Sie wird ausgeführt, wenn ein Spieler den Zauberspruch ausführt und übergibt ein eigenes Spieler Objekt, das die Daten des Plugin beinhaltet.

```Java
	@Override
	public void fire(MMPlayer p) {
	
	}
}
```

Um einen Bukkit Spieler aus dem MMPlayer Objekt zu machen, verwende
```Java
	Bukkit.getPlayer(p.getUUID());
```

Schließlich muss der Zauberspruch noch registriert werden, damit er beim Ausführen eines Zauberspruchs verwendet werden kann. Das geschieht üblicher Weise im onEnable() Teil

```Java
	SpellManager.registerSelfSpell(new MySpell(), SpellPriority.LOW);
```
