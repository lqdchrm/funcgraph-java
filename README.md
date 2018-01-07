# Funcgraph-Demo
This repo demonstrates a simple FunctionGraph-Framework.

# Feature-Matrix

Feature                                                | Status | Umgesetzt durch
------------------------------------------------------:|:------:|:-------------------------------------------------------------------
Entkopplung DomainObjekte / Framework                  | N      |
Automatisches Management der Abhängigkeiten            | Y      | Scope ist Block-Registry
Minimale (Neu)-Berechnung von Objekten                 | Y      | Blocke speichern Results und tracken Change durch Dirty-Flag
Sichtbarkeit der Abhängigkeiten zur Laufzeit/Debugging | Y      | Blocke speichern Abhängigkeiten
Minimaler Code-Change bei Änderung von Abhängigkeiten  | Y      | Blöcke sind self-contained
Level an "exotischem" Code                             | 2      | Konstruktor definiert Abhängigkeiten