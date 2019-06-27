Win -> Docker Desktop
Win -> Коммандная строка -> Дополнительно -> Запуск от имени администратора -> Да

Это надо делать при каждом запуске проекта: {
	Run:
	cd C:\Users\Александр\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\docker
	docker-compose up -d
	В Microsoft Edge или другом браузере:
	localhost:8180
}

Это надо делать, если меняешь код проекта: {
	Rebuild:
	cd C:\Users\Александр\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\microservice-kafka
	mvnw.cmd clean package
	cd C:\Users\Александр\Documents\Dgava_Projects\OOP.Java\Lab3\microservice-kafka\docker
	docker-compose build
}

Это надо делать при каждом стопе проекта: {
	docker-compose down
}