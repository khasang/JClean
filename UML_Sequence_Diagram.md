![Alt text](http://g.gravizo.com/g?
@startuml
title "Comments - Sequence Diagram"
actor User
boundary "GUI" as GUI
control "MainController" as MC
entity SearchThread
boundary SearchWindow
database Widgets
boundary ResultWindow
boundary ConfirmWindow
boundary DeleteProcessWindow
entity DeleteThread
boundary SuccessResultWindow
User -> GUI : Нажал сканировать
GUI -> MC : Запустить сканирование
MC -> SearchWindow: отобразить
MC -> SearchThread : Начать поиск
SearchThread --> Widgets : Перебор файлов
SearchThread -> MC : Дубликаты найдены
MC -> ResultWindow : Показать результаты
User -> ResultWindow : Удалить выделенные файлы
ResultWindow -> MC : Удалить файлы
MC -> ConfirmWindow : Показать окно подтверждения
User -> ConfirmWindow : Подтвердить
ConfirmWindow -> MC : Окончательно удалить файлы
MC -> DeleteProcessWindow : Показать процесс удаления
MC --> DeleteThread : Фоновое удаление
DeleteThread --> MC : Удаление завершено
MC -> SuccessResultWindow: Показать окно с результатами
@enduml
)