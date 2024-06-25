# Dockerfile
FROM openjdk:17   
WORKDIR /usr/src/app
COPY . .
RUN javac DemoApplication.java    # コンパイルが必要な場合（Main.javaは適切なファイル名に置き換えてください）
CMD ["java", "DemoApplication"]   # 実行するJavaプログラムのエントリーポイント（Mainは実行するクラス名に置き換えてください）