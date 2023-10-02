Auf der linken Seites des Flußes befinden sich ein Bauer, eine Ziege, ein Kohlkopf und ein Wolf.
Der Bauer möchte alle auf die linke Seite des Flußes bringen, zu welchem Zwecke ihm ein Boot
zur Verfügung steht, da jedoch nur ihn und einen weiteren Gegenstand tragen kann.

Zu keiner zeit darf sich der Wolf mit der Ziege oder die Ziege mit dem Kohlkopf allein auf
der selben Seite des Flußes befinden, anderfalls geht der Bauer der Ziege -- beziehungsweise
-- des Kohlkopfes verlustig.

Das Spiel beginnt mit der statischen Methode "start" in der Klasse "AlleLinks".
Jede Bewegung -- also das Übersetzen des Bauers allein oder mit einem Gegenstand
auf die andere Flußseite -- wird durch Aufruf einer Methode geführt. Der Typ des
Methoden-Ergebnisses gibt den neuen Zustand an. Endzustände sind die drei Interfaces
die keine Methoden besitzen: AlleRechts, GefahrLinkesUfer und GefahrRechtesUfer.

In der IDE läßt sich vor jedem Methden-Aufruf ermitteln, welcher Zustand herrscht
(über den Klassennamen) und welche Bewegungen möglich sind (über die Methoden des
aktuellen Interfaces).

Ein Beispiel für die Anwendung findet sich in der Klasse Spiel. 
