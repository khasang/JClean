![Alt text](http://g.gravizo.com/g?
@startuml
title "MVC Sequence Diagram JClean"
actor user #blue
participant View as V #99FF9
participant Control as C
participant Model as M

user->V: Событие
V->C: Отправка сообщения о событии
C->M: Запрос на обновление данных
C->M: Запрос на получение обновленных данных
M-->C: Получение данных
C->V: Отправка обновленных данных на отрисовку
V->V: Обновление отображения
@enduml
)