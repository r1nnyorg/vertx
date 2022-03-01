public class Server
{
    public static void main(final java.lang.String[] args)
    {
        final var vertx = io.vertx.core.Vertx.vertx();
        final var router = io.vertx.ext.web.Router.router(vertx);
        router.route().handler(io.vertx.ext.web.handler.CorsHandler.create()).handler(io.vertx.ext.web.handler.BodyHandler.create());
        final var database = io.vertx.pgclient.PgPool.pool(vertx, new io.vertx.pgclient.PgConnectOptions().setHost("postgrespostgres.postgres.database.azure.com").setDatabase("default").setUser("postgres").setPassword("pos1gres+").setSsl(true).setPemTrustOptions(new io.vertx.core.net.PemTrustOptions().addCertPath("DigiCertGlobalRootCA.crt.pem")), new io.vertx.sqlclient.PoolOptions());
        router.post("/ajax").respond(ctx -> database.query("select * from" + ctx.getBodyAsJson().stream().map($ -> java.lang.String.join(" ", $.getKey(), $.getValue().toString())).collect(java.util.stream.Collectors.joining(" "))).execute()    );  
        vertx.createHttpServer().requestHandler(router).listen(80);
    }
}
