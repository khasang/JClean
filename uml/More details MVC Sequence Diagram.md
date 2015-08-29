<img src='http://g.gravizo.com/g?
@startuml;

title "MVC Sequence Diagram JClean";
hide footbox;

participant Main as Main %2399FF9;
participant ProcessControl as PC;
participant GUIControl as GC;
participant Interface as face;
participant Model as M;

Main->PC: ������ ��������� ��������;
hnote over PC : ���������;
PC->GC: ������� �� ��������� ����������;
GC->face: ��������� ����������;
face->face: ���� ������� �� ������������;
face->GC: �������� ���������� � ��������� ������������;
GC->PC: ������ �������� ������������;
PC->M: ���������� ������ �� ������ �������� ������������;
M-->PC: ��������� ����������� ������;
PC->GC: ������� �� ���������� ����������;
GC->face: ������� �� ����������� ����������� ������;
note right;
������ ����� ���������� ����;
���������� �� ������������.;
� ���� ������� ���������� �����,;
�� ��� ��� ���� ������������ ��;
�������� �������� � �����������;
end note;
@enduml
'>