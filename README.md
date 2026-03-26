# 🕵️‍♂️ Shadows of Funchal: The Bloom Threat (CLI Game)

![Language](https://img.shields.io/badge/Language-Java-orange)
![Interface](https://img.shields.io/badge/Interface-Command_Line_(CLI)-lightgrey)
![Architecture](https://img.shields.io/badge/Architecture-OOP_%26_SOLID-blue)

## 🎮 About the Game
Welcome to the underworld of Madeira Island. **Shadows of Funchal** is a text-based (CLI) adventure and mystery game developed purely in **Java**. You step into the shoes of the legendary detective Sherlock Holmes, sent to Funchal to investigate the rapid spread of a deadly new synthetic drug known as *"Bloom"*.

Beyond the narrative, this project was strictly engineered as a sandbox for applying **Advanced Object-Oriented Programming (OOP)** principles, **SOLID** methodologies, and standard **Software Engineering Design Patterns**.

---

## 📖 The Story & Objectives
Night has fallen heavily over Funchal. From the narrow, dangerous streets of *Zona Velha* (Old Town) to the luxurious *Quinta Vigia*, a deep network of corruption is silencing the city. 

* **Your Goal:** Collect critical clues, interrogate suspicious characters, manage your resources (inventory and currency), and piece together the truth to arrest the masterminds.
* **Progression:** The game challenges your deduction skills across 4 distinct levels, scaling in complexity and danger, ending at the Presidential Cabinet

---

## 🏗️ Software Architecture & Design Patterns
The true core of this game lies in its codebase. It was architected to be highly scalable, maintainable, and decoupled, avoiding the common pitfalls of monolithic "Spaghetti Code" in text adventures.

* **Command Pattern (`comandos` package):** User inputs (e.g., *go*, *talk*, *take*) are not parsed through massive `switch` statements. Instead, every action is encapsulated into its own independent class executing a unified interface. Adding a new game command requires zero changes to the core engine.
* **Strategy Pattern via Lambdas (`ItemUtilizavel`):** Dynamic behaviors are injected into items at runtime using Java functional interfaces. This allows items to have unique effects (e.g., healing, unlocking) without creating deep, rigid inheritance trees.
* **Manager / Delegation Pattern (`GestorDeTrocas`):** NPC entities (`NPC`) are strictly data containers. All business logic regarding economy and item trading is handled by an isolated Trade Manager, adhering to the **Single Responsibility Principle**.
* **Robust Exception Hierarchy:** The game utilizes a custom Checked Exception tree rooted at `JogoException`. Logic faults (e.g., trying to enter a locked room, or insufficient funds) are caught and handled gracefully, providing contextual CLI feedback to the user without abruptly terminating the JVM.

---

## 🗂️ Project Structure
* `src/principal/` : The core game loop, state management, and CLI parser.
* `src/comandos/` : Command pattern implementations (`ComandoIr`, `ComandoFalar`, etc.).
* `src/nucleo/` : Core entities representing the game world (`Jogador`, `NPC`, `Sala`, `Item`).
* `src/entidades/` : Specialized handlers for decoupled business logic (e.g., `GestorDeTrocas`).
* `src/excecoes/` : Custom exception classes for game-specific error handling and flow control.

---

## 🚀 How to Run

1. **Prerequisites:** Ensure you have the **Java Development Kit (JDK 11+)** installed on your machine.
2. **Clone the Repository:**
   ```bash
   git clone [https://github.com/Lucas-Silva16/Sherlock-Holmes-Terminal-Game-Java.git](https://github.com/Lucas-Silva16/Sherlock-Holmes-Terminal-Game-Java.git)
