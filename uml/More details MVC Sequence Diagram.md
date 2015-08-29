![Alt text](http://g.gravizo.com/g?
@startuml;

title "MVC Sequence Diagram JClean";
hide footbox;

participant Main as Main %2399FF9;
participant ProcessControl as PC;
participant GUIControl as GC;
participant Interface as face;
participant Model as M;

Main->PC: Запуск основного контрола;
hnote over PC : Настройка;
PC->GC: Команда на отрисовку интерфейса;
GC->face: Отрисовка интерфейса;
face->face: Сбор событий от пользователя;
face->GC: Отправка информации о действиях пользователя;
GC->PC: Анализ действий пользователя;
PC->M: Обновление данных на основе действий пользователя;
M-->PC: Получение обновленных данных;
PC->GC: Команда на обновление интерфейса;
GC->face: Команда на отображение обновленных данных;
note right;
Дальше снова запустится сбор;
информации от пользователя.;
И весь процесс повторится снова,;
до тех пор пока пользователь не;
закончит работать с приложением;
end note;
@enduml
)
