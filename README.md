# ExchangeRate
Сервис принимает в качестве запроса код валюты, если её курс по отношению к базовой валюте (USD) вырос, возвращается рандомная гифка со страницы https://giphy.com/search/rich, иначе - https://giphy.com/search/broke. Если код валюты введён неверно, отображается страница со всеми доступными кодами валют. Сервис написан на Spring Boot 2.6.0. Для взаимодействия с внешними сервисами используется Feign.

# Инструкция по запуску

Необходимо установить docker - https://docs.docker.com/desktop/windows/install/. Далее воспользоваться командой в консоли:

docker run -d -p 8080:8080 -t exchangerate-0.0.1 

Сервис будет доступен на http://localhost:8080/

Дальше действуйте по инструкции на экране.
