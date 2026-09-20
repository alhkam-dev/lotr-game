# Lord of the Rings: Battle Simulator ⚔️ Ring-War

A desktop-based graphical application that simulates an epic confrontation between the forces of Good (Heroes) and Evil (Beasts) in Middle-earth. Built in **Pure Java using Swing**, this project was developed to strengthen and demonstrate solid foundations in **Object-Oriented Programming (OOP)**, GUI design patterns, and event-driven logic.

## 🚀 Key Features

- **Graphical User Interface (GUI):** A fully interactive desktop experience built with Java Swing, featuring dynamic view updates during combat.
- **Dynamic Army Generation:** Creates and customizes balanced armies of Heroes (Humans, Elves, Dwarves) and Beasts (Orcs, Trolls) via graphical inputs.
- **Turn-Based Combat Engine:** Simulates 1v1 duels between front-line fighters until one army is completely defeated, displaying real-time battle logs in the interface.
- **Unique Character Classes:** Each character type possesses unique attributes (Health, Armor, Strength) and specialized combat modifiers or passives.

## 🛠️ Architecture & Technical Highlights

This project intentionally avoids external web frameworks to highlight core software engineering and desktop application practices:

- **Model-View-Controller (MVC) Pattern:** Strict decoupling of the combat engine (Model) from the Swing windows, buttons, and text areas (View/Controller) to ensure code maintainability.
- **Event-Driven Programming:** Leverages Swing's event listeners (`ActionListener`) to handle user interactions and trigger simulation steps smoothly.
- **Inheritance & Encapsulation:** Clean class hierarchy starting from an abstract base class (`Character`), extended by `Hero` and `Beast`, ensuring proper data encapsulation.
- **Polymorphism:** Utilizes method overriding for custom combat mechanics (e.g., Elves getting a precision bonus against specific enemies, or Trolls reducing opponent armor).
- **Java Collections Framework:** Utilizes optimal data structures (`List`, `ArrayList`) to manage active and fallen fighters dynamically during simulation.

## ⚙️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com
   ```
2. Navigate to the project directory:
   ```bash
   cd lotr-game
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## 📬 Contact

Sergio Aparicio - [LinkedIn]([https://linkedin.com](https://www.linkedin.com/in/sergio-aparicio/)) - sergioaparicio1995@gmail.com
