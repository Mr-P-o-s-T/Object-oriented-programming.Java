Win -> Docker Desktop
Win -> ���������� ������ -> ������������� -> ������ �� ����� �������������� -> ��

��� ���� ������ ��� ������ ������� �������: {
	Run:
	cd C:\Users\���������\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\docker
	docker-compose up -d
	� Microsoft Edge ��� ������ ��������:
	localhost:8180
}

��� ���� ������, ���� ������� ��� �������: {
	Rebuild:
	cd C:\Users\���������\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\microservice-kafka
	mvnw.cmd clean package
	cd C:\Users\���������\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\docker
	docker-compose build
}

��� ���� ������ ��� ������ ����� �������: {
	docker-compose down
}