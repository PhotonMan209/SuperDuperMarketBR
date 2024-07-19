# Bewerberaufgabe SUPERDUPERMARKT

## Einleitung

In diesem Projekt befindet sich meine Bewerberaufgabe **SUPERDUPERMARKT**. Diese Anwendung soll einen groben Eindruck meiner Erfahrung und meines Coding-Stils vermitteln.

## Verzichtete Aspekte

Bei der Erstellung der Anwendung wurden bewusst folgende Aspekte vernachlässigt:

- **Error Handling**: Das meiste Error Handling würde wahrscheinlich beim Input von neuen Daten stattfinden. Da dies in diesem Projekt nicht vorkommt, wurde auf umfangreiches Error Handling verzichtet. In einer realen Anwendung wäre robustes Error Handling beim Einlesen und Auslesen von Daten notwendig.

- **Checken jeder Null pointer Exception**: Dies ist zwar unter der Kategorie Error Handling, jedoch ist dies hervor zuheben. EÍn einem vollständigen Projekt, steht selten ein ".findFist().get()" ohne vorherige Verifikation oder Error handling

- **Effizienz bei der Datenbankabfrage**: Es werden keine gepoolten Datenbankabfragen durchgeführt. Dies wäre in komplexeren Anwendungen vorteilhaft, wurde jedoch in diesem Projekt nicht berücksichtigt.

- **Automatische tägliche Updates zur Qualität von Produkten**: Da niemand die Applikation für mehrere Tage auf seinem Rechner laufen lassen wird, wurde diese Funktion nicht implementiert.

## Aufbau und Design

### Objektorientierung und Design Pattern

- **MVC Prinzip**: Die Anwendung nutzt das klassische MVC-Prinzip (Model-View-Controller). Diese Struktur bietet eine solide Basis für die meisten Anwendungen. Durch die Trennung der Bausteine lässt sich die Anwendung intuitiv in Klassen und Packages sowie Unterpackages aufteilen.

- **Package Struktur**: Die Struktur der Packages ermöglicht eine klare Kategorisierung. Diese ist meiner Meinung auch einfach mit weiteren Modulen und Klassen zu erweiter, würden diese benötigt werden.

- **Generische Methoden**: Mehrere Methoden wurden so generisch wie möglich gehalten, um Modularität zu gewährleisten. zB. Die EntityDisplay Klasse sollte nicht nur Produkte, sondern auch in der Lage sein, andere Tabellen der Datenbank leserlich in der Konsole ausgeben zu können

- **Konstanten**: Ich nutze Konstanten lieber mit Klassen anstatt mit Enums, da diese in Konfigurationsthemen besser anzuwenden sind. Jedoch habe ich diese nicht überall verwendet. Ggf. könnte man zB auch in Tests Konstanten verwenden, wenn Strings mehrmals vorkommen.

- **Memory Datenbank**: Mit der Nutzung einer Memory Datenbank habe ich den Bereich einer SQL-Anbindung abgebildet. Die H2 Datenbank kam mir als erstes in den Sinn und sie wird für den Scope dieses Projektes ausreichend sein.

- **CSV Dateien**: Das Einlesen von `.csv`-Dateien ist mit der Nutzung der Memory Datenbank verknüpft. Das ziehen der Daten wurde "gehardcoded". Dies könnte man natürlich modularer machen, jedoch der Aufgabenstellung entsprechend, wird dieses nicht berücksichtigt.

## Kommentare zu Unklarheiten in der Aufgabenstellung

- **Erstellen eines Moduls für einen neuen Produkttypen**: Ich bin mir nicht sicher, ob ich mit dem Hinzufügen von weiteren Produkten und Kategorien diesen Bereich abgedeckt habe. Die Variation in Produkten erlaubt mir, mehrere Standardregeln anzuwenden. Jedoch bin ich mit dem Handhaben meiner Regelausnahmen nicht vollends zufrieden. Diese werden noch im `QualityDegradationService` gehandhabt. Man könnte dies bestimmt dezentraler implementieren. Aus Aufwandsgründen wurde dies jedoch sehr simpel gehalten.

- **Modularität im laufenden Betrieb**: Diese Anforderung knüpft an die vorherige Unklarheit an. Ich denke, dass dieser Umstand durch das Einlesen von CSV-Dateien und möglichen Anbindungen an die Datenbank, z.B. über eine REST-Schnittstelle, gut abgebildet werden kann. 100% sicher bin ich mir jedoch nicht, dass hier exakt den Anforderungen entsprochen wurde.

## Nutzung

- **Die Nutzung der Anwendung erfolgt über die Konsolen eingabe.

- **Über die "help" Funktion, können die paar vorhandenen Befehle dem Nutzer bekannt gemacht werden.

