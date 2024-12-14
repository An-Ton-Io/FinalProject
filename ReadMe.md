# Task Manager

## Problem Description
This Task Manager application allows users to manage their tasks. Pending tasks are stored in an ArrayList, and completed tasks are stored in an array. The tasks are saved to and loaded from files to maintain persistence.

## Implementation Details
- **Arrays and ArrayLists**: Used to store completed and pending tasks, respectively.
- **Recursive Methods**: Used for error checking inputs.
- **Exception Handling**: Implemented using try-catch blocks to handle invalid inputs and file I/O exceptions.
- **File Persistence**: Tasks are saved to files when the program exits and loaded from files when the program starts.

## How to Run
1. Clone the repository.
2. Open the project in GitHub Codespaces.
3. Run the `TaskManager` class.

```bash
javac TaskManager.java
java TaskManager