Для сборки сервера выполните

```
mvn install
```

Для запуска

```
mvn exec:java -Dexec.mainClass=ru.spbau.mit.hackathon.paywell.server.AppServer
```

Файл ```serverKeyStore.jks``` содержит секретный ключ сервера.

Если нужно сгенерировать новую пару открытый-закрытый ключ, выполните

```
./generate_keys.sh
```

Получатся файлы ```serverKeyStore.jks``` и ```PublicKeyHolder.java```.
Файл ```PublicKeyHolder.java``` содержит открытый ключ для клиента. Его нужно поместить в проект клиента вместо одноимённого файла.
