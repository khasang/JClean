
![Alt text](http://g.gravizo.com/g?
@startuml
class FileHash {
    String getFastHash(File, String);
}
enum State {
    MAIN_MENU,
    SEARCH,
    REPORT
}
class Main {
    void main(String...);
}
class ProcessControl {
    State state;
}
ProcessControl *-- State
ProcessControl *-- JContainer
ProcessControl *-- UIControl
ProcessControl .> GUIControl
GUIControl *-- MainWindow
GUIControl *-- ResultWindow
UIControl <. GUIControl 
Main .> ProcessControl
@enduml
)
