<h1 align="center">Tinee Client/Server Application</h1>
<p align="center">
  <img src="https://img.shields.io/badge/documentation-yes-brightgreen" />
  <img src="https://img.shields.io/maintenance/yes/2021" />
</p>

## 📜 Overview
This project is a command-line client to manage tickets. It has two states, Main and Drafting, where at Main State, it is possible to read tickets and manage tickets. At Drafting State, it is possible to add lines to a ticket and push the lines added into the server.

## 📌 Prerequisites
* Java 8 SDK
* NetBeans 8.2

## 🚀 Usage
The arguments required to run a client are:
1. username
2. hostname
3. port number

To provide the above arguments to NetBeans:
1. Click "default config"
2. Customize
3. Into the new window, under "Categories", select the "Run" option.
4. In the field "Arguments", you provide the arguments above in the following format `String String int` e.g. username localhost 8888

Run the following command at the root of your project:
```sh
java -cp build/classes sep.tinee.server.Server 8888
```

To compile and run this client using NetBeans:
1. Right-click main file
2. Select "Run File"

## 📝 Coursework: Programming and Design
Debug, refactor and redesign the client to address the issues raised in code review. Refactor the client based on the Command Pattern. Restructure the client to modularise its main components: the core data structures, the user interface, and the application logic.
### 📎 Extensions:
* Undo of appropriate commands in the Drafting state.
* Support for internationalisation.

## 📝 Coursework: Software Engineering Practices
* Version control: GitHub repository with branches to separate tasks.
* Testing: Acceptance tests, Unit tests and Code coverage.
* Static code analysis: FindBugs and EasyPmd.
* Documentation: Javadoc.

## Authors
👤 **Manuel Gomes Rosmaninho**
- Github: [@mgrosmaninho](https://github.com/mgrosmaninho)
- LinkedIn: [@manuel-gomes-rosmaninho](https://www.linkedin.com/in/manuel-gomes-rosmaninho/)

👤 **Raymond Hu**
- GitHub: [@rhu1](https://github.com/rhu1)

👤 **Joseph Williams**
- GitHub: [@joe-williams-cccu](https://github.com/joe-williams-cccu)

## 🤝 Contributing
Contributions, issues and feature requests are welcome!

## 📝 License
This project is [UH](https://www.herts.ac.uk/) licensed.

## 📖 Helpful Links
* [Java](https://en.wikipedia.org/wiki/Java_(programming_language))
* [NetBeans](https://en.wikipedia.org/wiki/NetBeans)
* [Version Control](https://en.wikipedia.org/wiki/Version_control)
* [Javadoc](https://en.wikipedia.org/wiki/Javadoc)
