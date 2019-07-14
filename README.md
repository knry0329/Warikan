
WARIKAN
==========

世界で一番シンプルな旅費精算Webツールです。  
herokuページは[こちら](https://tatekae-simple.herokuapp.com/)


目次
-----------------
ｖ
  * [機能一覧](#機能一覧)
  * [使用している技術](#使用している技術)


機能一覧
------------

  * 旅費入力
  * 旅費立替金額表示
  * ログイン・ログアウト
  * 一時保存

環境
------------

  * 本番環境  
  本番環境はherokuにデプロイしています。  
herokuページは[こちら](https://tatekae-simple.herokuapp.com/)

  * 開発環境  
  Docker-composeを使って、開発環境を構築することができます。  
  git clone後、プロジェクトのルートフォルダで、下記コマンドを実行してください。  
  `docker-compose up -d —-build`  
  その後、下記URLからアクセスしてください。   
  `http://localhost:8190`


使用している技術
-----

  * JDK 1.8
  * Spring Boot
  * Spring Data JPA
  * Spring Security
  * Thymeleaf
  * jQuery
  * PostgreSQL
  * Docker-compose


License
-------

Copyright &copy; 2019, knry0329
