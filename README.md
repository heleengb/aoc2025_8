# Reto de Análisis de Circuitos - 1.0

**Arquitectura, estilo MVC (Model-View-Controller) con Patrón Command:**
El proyecto separa la estructura de datos topológica de los algoritmos de análisis. En el paquete **model**, `NetworkTopology` implementa la estructura de datos "Union-Find" (Conjuntos Disjuntos) para gestionar la conectividad de la red, mientras que `Point3D` y `TransmissionLink` modelan los nodos y las conexiones. En el paquete **view**, `ConsoleResultPrinter` se encarga de la salida. En el **controller**, `CircuitController` coordina el flujo, transformando datos en puntos 3D y delegando el análisis complejo a los comandos.

**Principios aplicados:**
* **Responsabilidad Única (SRP):** Cada clase tiene un rol acotado. `FileInputReader` maneja I/O, `NetworkTopology` gestiona puramente la lógica de unión de conjuntos (path compression, union by size), y los comandos (`PowerConsumptionCommand`, `CriticalPathCommand`) encapsulan los algoritmos de grafos (como Kruskal).
* **Inversión de Dependencias (DIP):** El controlador y el main dependen de interfaces (`InputReader`, `ResultPrinter`, `AnalysisCommand`), permitiendo cambiar la fuente de datos o el algoritmo sin alterar el flujo principal.
* **Abierto-Cerrado (OCP):** El sistema es extensible. Si se requiere un nuevo análisis (ej. "Detectar ciclos"), basta con implementar una nueva clase `AnalysisCommand` sin modificar la topología ni el controlador.

**Extras:**
* **Algoritmo Union-Find (DSU):** Implementación eficiente en `NetworkTopology` para operaciones de conectividad casi constantes.
* **Paquete Command:** Aísla la lógica de la "Parte 1" (Agrupamiento/Clustering) y la "Parte 2" (Árbol de Expansión Mínima).
* **Java Records:** Uso de `Point3D` y `TransmissionLink` para datos inmutables.
* **Streams y Generación de Grafos:** Uso de `IntStream` y `flatMap` para generar todas las posibles conexiones ($N^2$) de manera declarativa y elegante.
