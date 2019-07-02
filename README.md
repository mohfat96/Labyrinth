## Labyrinth

In dieser Aufgabe geht es darum Irrgärten zu kartographieren. Ihr Programm wird in einem Irrgarten "ausgesetzt"
und soll selbstständig durch den Irrgarten finden und dabei einen vollständigen Plan anfertigen.

## Der Irrgarten

Als Modell des Irrgartens steht Ihnen die Klasse Maze zur Verfügung. Ihrem Programm wird ein Maze-Objekt
übergeben, auf dem Sie folgene Operationen ausführen können:

```
int width(): gibt die Gesamtbreite des Irrgartens zurück.
int height(): gibt die Gesamthöhe des Irrgartens zurück.
void walk(Direction direction): läuft in die angegebene Richtung.
boolean isWall(Direction direction): Testet ob sich in der angegebenen Richtung eine Wand
befindet.
void mark(Direction direction): Setzt eine Markierung in die angegebene Richtung. Jedes Feld kann
beliebig viele Markierungen in beliebige Richtungen enthalten. Eine Markierung auf einem Feld in beliebiger
Richtung hat keinen Einfluss auf die Markierungen der Nachbarfelder.
int marks(Direction direction): Gibt aus, wie viele Markierungen in der angegebenen Richtung auf
dem aktuellen Feld sind.
```
Richtungen werden durch die Klasse Maze.Direction umgesetzt. Es stehen folgende Möglichkeiten zur
Verfügung:

```
LEFT
AHEAD
RIGHT
BACK
```
Die Richtung wird immer aus dem Blickwinkel des Agenten angegeben, der sich gerade in dem Irrgarten befindet.

Ein kurzes Beispiel zur Anwendung der Klasse Maze:

public void goBackIfNoLeftWay(Maze maze) {
if (!maze.isWall(Maze.Direction.LEFT)) {
maze.walk(Maze.Direction.LEFT);
} else {
maze.walk(Maze.Direction.BACK);
}
}

Die gezeigte Methode prüft, ob sich links ein Gang befindet und biegt, falls ein Solcher existiert, links ab.
Ansonsten kehrt das Programm um.

## Der Algorithmus

Um durch den Irrgarten zu kommen, ohne sich dabei zu verlaufen, wenden wir die Methode von Trémaux
(https://de.wikipedia.org/wiki/Tr%C3%A9maux%E2%80%99_Methode) an. Dieser Algorithmus ermöglicht es,
einen Irrgarten vollständig zu durchlaufen und dabei jeden Weg maximal zwei mal zu begehen.


Wichtig ist es zwischen Wegen und Plätzen zu unterscheiden. Ein Weg bietet nur eine Möglichkeit weiterzugehen.
Wenn sich beispielsweise vor Ihnen eine Wand befindet und links genauso, rechts aber nicht, bleibt Ihnen keine
andere Möglichkeit als rechts abzubiegen. Ein Platz hingegen stellt eine Kreuzung von Wegen dar, bietet also
prinzipiell mehrere Möglichkeiten weiterzulaufen. Sackgassen stellen genauso Wege dar, da die einzige
Möglichkeit darin besteht umzudrehen. Sie dürfen im Folgenden davon ausgehen dass ein Irrgarten immer
mindestens einen Platz hat.

Markieren Sie, wenn sie einen Platz betreten, immer sowohl die Richtung, aus der Sie kamen, als auch diejenige,
in die Sie weitergehen. (Selbst wenn das die selben Richtungen sind!)
Erreichen Sie einen Platz gibt es folgende Möglichkeiten:

```
1. Sie waren noch nie hier (es gibt keine Markierungen): Gehen sie in eine beliebige Richtung weiter (nicht
zurück!).
2. Sie waren schon mal hier (es gibt Markierungen) aber die Richtung, aus der Sie kamen, ist nicht markiert:
Drehen Sie um.
3. Alle verfügbaren Abzweigungen haben mehr als eine Markierung: Sie sind fertig.
4. Ansonsten nehmen Sie den Weg mit den wenigsten Markierungen.
```
## Die Klasse Explorer

Erstellen Sie im Packet de.uniwue.gdp.labyrinth die Klasse Explorer.

Erstellen Sie in der Klasse Explorer eine Methode public String exploreMaze(Maze z). Sie soll eine Karte
des übergebenen Irrgartens erstellen und diese als String zurückgeben. Sie dürfen davon ausgehen, dass Sie in
der linken oberen Ecke des Irrgartens starten und nach unten schauen. Dabei sollen betretbare Felder durch
Leerzeichen und Wände durch das Zeichen '#' dargestellt werden.

Der zurückgegebene String könnte dabei zum Beispiel folgendermaßen aussehen.

Sie starten links oben mit Blick nach unten. Der Aufruf Maze.isWall(Maze.Direction.LEFT) würde also false
zurückgeben. Daraufhin können Sie sich mit Hilfe von Trémaux' Methode durch den Irrgarten bewegen und dabei
festhalten, wo sich Wege befinden.

## Testen


PABS 3.7 - University of Würzburg - Impressum (https://www.uni-wuerzburg.de/sonstiges/impressum/) -
Datenschutz (https://www.uni-wuerzburg.de/sonstiges/datenschutz/)

Um Ihr Programm vor Abgabe zu testen, steht Ihnen eine Bibliothek mit zwei Irrgärten als Beispiel zur Verfügung.
Sie können diese durch die Methoden Examples.example01() und Examples.example02() aufrufen. Sie geben
jeweis Objekte vom Typ Maze zurück, die Sie an Ihre exploreMaze-Methode übergeben können.

example01 liefert den oben gezeigten Irrgarten.

Der durch example02 erzeugte Irrgarten ist folgendermaßen aufgebaut:

Sollten die Beispielklassen nicht verfügbar sein, finden Sie diese unter lib/examples.jar im Projektordner. Mit
Rechtsklick und "Add as Library" können Sie sie in IntelliJ manuell dem Projekt hinzufügen.

# Viel Spaß!
